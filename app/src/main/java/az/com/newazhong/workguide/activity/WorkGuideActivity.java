package az.com.newazhong.workguide.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.workguide.adapter.WorkGuideFreemanAdapter;
import az.com.newazhong.workguide.bean.WorkGuide;
import az.com.newazhong.workguide.presenter.WorkGuidePresenter;
import az.com.newazhong.workguide.presenter.presenterimpl.WorkGuidePresenterimpl;
import az.com.newazhong.workguide.view.WorkGuideView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2018/3/24.
 */

public class WorkGuideActivity extends BaseActivity implements WorkGuideView {
    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerViewFreeman)
    RecyclerView recyclerViewFreeman;

    List<WorkGuide.DataBean.ContentBean> beanInfoList = new ArrayList<>();
    WorkGuidePresenter workGuidePresenter;
    WorkGuideFreemanAdapter freemanAdapter;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerViewFreeman.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        workGuidePresenter = new WorkGuidePresenterimpl(this, this);
        //数据请求
        workGuidePresenter.getWorkGuidePresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_workguide;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void getWorkGuideViewData(WorkGuide workGuide) {
        //得到类型集合
        if (workGuide.getData().getContent().size() == 0) {
            imageView.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
            llNoContent.setVisibility(View.VISIBLE);
        }else {
            for (int i = 0; i < workGuide.getData().getContent().size(); i++) {
                WorkGuide.DataBean.ContentBean bean = new WorkGuide.DataBean.ContentBean();
                bean.id = workGuide.getData().getContent().get(i).getId();
                bean.name = workGuide.getData().getContent().get(i).getName();
                beanInfoList.add(bean);
            }
            //绑定adapter
            Message message = new Message();
            message.what = Constant.TAG_ONE;
            handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            freemanAdapter = new WorkGuideFreemanAdapter(beanInfoList);
            recyclerViewFreeman.setAdapter(freemanAdapter);
        }
    };
}
