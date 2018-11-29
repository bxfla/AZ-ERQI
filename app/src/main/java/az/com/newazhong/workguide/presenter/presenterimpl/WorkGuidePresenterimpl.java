package az.com.newazhong.workguide.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.workguide.bean.WorkGuide;
import az.com.newazhong.workguide.model.WorkGuideModel;
import az.com.newazhong.workguide.model.modelimpl.WorkGuideModelimpl;
import az.com.newazhong.workguide.presenter.WorkGuidePresenter;
import az.com.newazhong.workguide.view.WorkGuideView;

/**
 * Created by dell on 2018/3/24.
 */

public class WorkGuidePresenterimpl implements WorkGuidePresenter {
    private WorkGuideModel model;
    private Context context;
    private WorkGuideView view;

    //构造方法
    public WorkGuidePresenterimpl(WorkGuideView View, Context context){
        this.context=context;
        this.view=View;
        model=new WorkGuideModelimpl();
    }

    @Override
    public void getWorkGuidePresenter() {
        model.getWorkGuideModel(Constant.GETDATA, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getWorkGuideViewData((WorkGuide) o);
            }

            @Override
            public void error(String errorMessage) {
            }
        });
    }
}
