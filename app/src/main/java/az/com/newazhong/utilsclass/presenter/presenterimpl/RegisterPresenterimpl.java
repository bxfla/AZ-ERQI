package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Register;
import az.com.newazhong.utilsclass.model.RegisterModel;
import az.com.newazhong.utilsclass.model.modelimpl.RegisterModelimpl;
import az.com.newazhong.utilsclass.presenter.RegisterPresenter;
import az.com.newazhong.utilsclass.view.RegisterView;

/**
 * Created by dell on 2018/3/22.
 */

public class RegisterPresenterimpl implements RegisterPresenter {
    private RegisterModel model;
    private Context context;
    private RegisterView view;

    //构造方法
    public RegisterPresenterimpl(RegisterView View, Context context){
        this.context=context;
        this.view=View;
        model=new RegisterModelimpl();
    }

    @Override
    public void getRegisterPresenter(String phone, String loginName, String pwd) {
        model.getRegisterModel(Constant.GETDATA,phone,loginName,pwd, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getRegisterImage((Register) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
