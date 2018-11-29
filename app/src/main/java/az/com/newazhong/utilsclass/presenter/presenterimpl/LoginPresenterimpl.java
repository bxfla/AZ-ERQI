package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Login;
import az.com.newazhong.utilsclass.model.LoginModel;
import az.com.newazhong.utilsclass.model.modelimpl.LoginModelimpl;
import az.com.newazhong.utilsclass.presenter.LoginPresenter;
import az.com.newazhong.utilsclass.view.LoginView;

/**
 * Created by dell on 2018/3/22.
 */

public class LoginPresenterimpl implements LoginPresenter {
    private LoginModel model;
    private Context context;
    private LoginView view;

    //构造方法
    public LoginPresenterimpl(LoginView View, Context context){
        this.context=context;
        this.view=View;
        model=new LoginModelimpl();
    }

    @Override
    public void getLoginPresenter(String phone, String pwd) {
        model.getLoginModel(Constant.GETDATA,phone,pwd, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getLoginData((Login) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
