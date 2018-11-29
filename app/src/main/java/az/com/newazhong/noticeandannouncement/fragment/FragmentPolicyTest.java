package az.com.newazhong.noticeandannouncement.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.noticeandannouncement.adapter.NoticeTestAdapter;
import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.noticeandannouncement.bean.ShowNotice;
import az.com.newazhong.noticeandannouncement.presenter.NoticeTestPresenter;
import az.com.newazhong.noticeandannouncement.presenter.presenterimpl.NoticeTTestPresenterimpl;
import az.com.newazhong.noticeandannouncement.view.NoticeViewTest;
import butterknife.Bind;
import butterknife.ButterKnife;

import static az.com.newazhong.R.id.pullLoadMoreRecyclerView;

/**
 * Created by dell on.
 */

public class FragmentPolicyTest extends Fragment implements NoticeViewTest, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    List<ShowNotice> beanNoticeList = new ArrayList<>();
    NoticeTestPresenter noticePresenter;
    NoticeTestAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    int PAGE_NO = 1;
    int PAGE_SIZE = 1;
    int BASE_SIZE = 0;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_notice_test, container, false);
        ButterKnife.bind(this, view);
        noticePresenter = new NoticeTTestPresenterimpl(this, getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(pullLoadMoreRecyclerView);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        //mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mPullLoadMoreRecyclerView.setLinearLayout();

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new NoticeTestAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        noticePresenter.getNoticePresenter("0", String.valueOf(PAGE_NO), String.valueOf(PAGE_SIZE));
//        if (PAGE_NO>BASE_SIZE){
//            mPullLoadMoreRecyclerView.setFooterViewText("暂无更多数据");
//            mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
//        }else {
//            //设置上拉刷新文字
//            mPullLoadMoreRecyclerView.setFooterViewText("loading");
//        }
    }

    @Override
    public void onActivityCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void getNoticeView(Notice notice) {
//        if (notice.getBody() == null) {
//            llNoContent.setVisibility(View.VISIBLE);
//            //swipeRefreshLayout.setVisibility(View.GONE);
//        } else {
//            for (int i = 0; i < notice.getBody().getInfo().size(); i++) {
//                ShowNotice showNotice = new ShowNotice();
//                BASE_SIZE = notice.getBody().getCount();
//                if (PAGE_NO>BASE_SIZE){
//                    mPullLoadMoreRecyclerView.setFooterViewText("暂无更多数据");
//                } else {
//                    showNotice.title = notice.getBody().getInfo().get(i).getTitle();
//                    showNotice.content = notice.getBody().getInfo().get(i).getContent();
//                    showNotice.name = notice.getBody().getInfo().get(i).getOffice().getName();
//                    showNotice.time = notice.getBody().getInfo().get(i).getCreateDate();
//                    beanNoticeList.add(showNotice);
//                }
//            }
//
//            mRecyclerViewAdapter.addAllData(beanNoticeList);
//            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beanNoticeList.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        beanNoticeList.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        Log.e("wxl", "onRefresh");
        mRecyclerViewAdapter.clearData();
        beanNoticeList.clear();
        PAGE_NO = 1;
        noticePresenter.getNoticePresenter("0", String.valueOf(PAGE_NO), String.valueOf(PAGE_SIZE));
    }

    @Override
    public void onLoadMore() {
        Log.e("wxl", "onLoadMore");
        //设置上拉刷新文字
        beanNoticeList.clear();
        PAGE_NO = PAGE_NO + 1;
        noticePresenter.getNoticePresenter("0", String.valueOf(PAGE_NO), String.valueOf(PAGE_SIZE));
    }
}