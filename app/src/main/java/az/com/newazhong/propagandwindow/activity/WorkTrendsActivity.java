package az.com.newazhong.propagandwindow.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.adapter.PropagandWindowAdapter;
import az.com.newazhong.propagandwindow.bean.PropagandWindow;
import az.com.newazhong.propagandwindow.bean.ShowPropagandWindow;
import az.com.newazhong.propagandwindow.presenter.PropagandWindowPresenter;
import az.com.newazhong.propagandwindow.presenter.presenterimpl.PropagandWindowPresenterimpl;
import az.com.newazhong.propagandwindow.view.PropagandWindowView;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkTrendsActivity extends BaseActivity implements PropagandWindowView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    PropagandWindowAdapter adapter;
    List<ShowPropagandWindow> beanList = new ArrayList<>();
    PropagandWindowPresenter propagandWindowPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        propagandWindowPresenter = new PropagandWindowPresenterimpl(this, this);
        propagandWindowPresenter.getPropagandWindowPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_worktrends;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void getPropagandWindow(PropagandWindow propagandWindow) {
        if (propagandWindow.getData().getContent().size() == 0) {
            llNoContent.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < propagandWindow.getData().getContent().size(); i++) {
                ShowPropagandWindow showPropagandWindow = new ShowPropagandWindow();
                if (propagandWindow.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED")){
                    showPropagandWindow.title = propagandWindow.getData().getContent().get(i).getArticle().getTitle();
                    showPropagandWindow.content = propagandWindow.getData().getContent().get(i).getArticle().getContent();
                    showPropagandWindow.name = propagandWindow.getData().getContent().get(i).getDepartmentName();
                    showPropagandWindow.time = propagandWindow.getData().getContent().get(i).getCreateTime();
                    beanList.add(showPropagandWindow);
                }
            }
            LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
            recyclerView.setLayoutManager(manager);
            adapter = new PropagandWindowAdapter(beanList);
            recyclerView.setAdapter(adapter);
        }
    }
}
