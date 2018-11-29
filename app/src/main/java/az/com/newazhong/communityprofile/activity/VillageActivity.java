package az.com.newazhong.communityprofile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.communityprofile.adapter.CommunityProfileAdapter;
import az.com.newazhong.communityprofile.bean.Community;
import az.com.newazhong.communityprofile.bean.ShowMDept;
import az.com.newazhong.communityprofile.presenter.CommunityPresenter;
import az.com.newazhong.communityprofile.presenter.presenterimpl.CommunityPresenterimpl;
import az.com.newazhong.communityprofile.view.CommunityView;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;

public class VillageActivity extends BaseActivity implements CommunityView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    List<ShowMDept> beanList = new ArrayList<>();
    CommunityPresenter mDeptPresenter;
    CommunityProfileAdapter adapter;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String headerText = intent.getStringExtra("header");
        header.setTvTitle(headerText);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        recyclerView.setLayoutManager(manager);
        mDeptPresenter = new CommunityPresenterimpl(this, this);
        mDeptPresenter.getMDeptPresenterData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                mDeptPresenter.getMDeptPresenterData();
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_communit;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void getMDeptData(Community mDept) {
        for (int i = 0; i < mDept.getData().getContent().size(); i++) {
            ShowMDept shouMDeptBean = new ShowMDept();
            if (mDept.getData().getContent().get(i).getOpen().equals("true") &&
                    mDept.getData().getContent().get(i).getTag().get(0).equals("村委会")) {
                shouMDeptBean.listTitle = mDept.getData().getContent().get(i).getName();
                shouMDeptBean.listContext = mDept.getData().getContent().get(i).getDescribes();
                beanList.add(shouMDeptBean);
            }
        }
        Message message = new Message();
        message.what = Constant.TAG_ONE;
        handler.sendMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    swipeRefreshLayout.setRefreshing(false);
                    if (beanList.size() == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setVisibility(View.GONE);
                    } else {
                        adapter = new CommunityProfileAdapter(beanList);
                        recyclerView.setAdapter(adapter);
                    }
            }
        }
    };
}
