package az.com.newazhong.noticeandannouncement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.noticeandannouncement.adapter.NoticeAdapter;
import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.noticeandannouncement.bean.ShowNotice;
import az.com.newazhong.noticeandannouncement.presenter.NoticePresenter;
import az.com.newazhong.noticeandannouncement.presenter.presenterimpl.NoticePresenterimpl;
import az.com.newazhong.noticeandannouncement.view.NoticeView;
import az.com.newazhong.utilsclass.base.MyApplication;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2017/8/1.
 */

public class FragmentCtalog extends Fragment implements NoticeView {
    @Bind(R.id.pullLoadMoreRecyclerView)
    RecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    List<ShowNotice> beanNoticeList = new ArrayList<>();
    NoticePresenter noticePresenter;
    NoticeAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_notice, container, false);
        ButterKnife.bind(this, view);
        noticePresenter = new NoticePresenterimpl(this, getActivity());
        noticePresenter.getNoticePresenter("NOTICE");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beanNoticeList.clear();
                noticePresenter.getNoticePresenter("NOTICE");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void getNoticeView(Notice notice) {
        if (notice.getData().getContent().size() == 0) {
        llNoContent.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    } else {
        for (int i = 0; i < notice.getData().getContent().size(); i++) {
            ShowNotice showNotice = new ShowNotice();
            if (notice.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED")){
                showNotice.title = notice.getData().getContent().get(i).getArticle().getTitle();
                showNotice.content = notice.getData().getContent().get(i).getArticle().getContent();
                showNotice.name = notice.getData().getContent().get(i).getDepartmentName();
                showNotice.time = notice.getData().getContent().get(i).getCreateTime();
                beanNoticeList.add(showNotice);
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        pullLoadMoreRecyclerView.setLayoutManager(manager);
        adapter = new NoticeAdapter(beanNoticeList);
        pullLoadMoreRecyclerView.setAdapter(adapter);
    }
    swipeRefreshLayout.setRefreshing(false);
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

}
