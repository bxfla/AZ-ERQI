package az.com.newazhong.street12345.activity.presenter.streetpresenterimpl;

import android.content.Context;

import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.street12345.activity.model.StreetModel;
import az.com.newazhong.street12345.activity.model.streetmodelimpl.StreetModelimpl;
import az.com.newazhong.street12345.activity.presenter.StreetPresenter;
import az.com.newazhong.street12345.activity.view.StreetView;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/24.
 */

public class StreetPresenterimpl implements StreetPresenter {
    private StreetModel model;
    private Context context;
    private StreetView view;

    //构造方法
    public StreetPresenterimpl(StreetView View, Context context){
        this.context=context;
        this.view=View;
        model=new StreetModelimpl();
    }


    @Override
    public void getStreetPresenter() {
        model.getStreetModel(Constant.GETDATA, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getStreetView((Street) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
