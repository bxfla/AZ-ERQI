package az.com.newazhong.propagandwindow.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.propagandwindow.bean.PropagandWindow;
import az.com.newazhong.propagandwindow.model.PropagandWindowModel;
import az.com.newazhong.propagandwindow.model.modelimpl.PropagandWindowModelimpl;
import az.com.newazhong.propagandwindow.presenter.PropagandWindowPresenter;
import az.com.newazhong.propagandwindow.view.PropagandWindowView;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/24.
 */

public class PropagandWindowPresenterimpl implements PropagandWindowPresenter {
    private PropagandWindowModel model;
    private Context context;
    private PropagandWindowView view;

    //构造方法
    public PropagandWindowPresenterimpl(PropagandWindowView View, Context context){
        this.context=context;
        this.view=View;
        model=new PropagandWindowModelimpl();
    }

    @Override
    public void getPropagandWindowPresenter() {
        model.getPropagandWindowModel(Constant.GETDATA,context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getPropagandWindow((PropagandWindow) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
