package az.com.newazhong.utilsclass.model.modelimpl;

import android.content.Context;
import android.widget.Toast;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.bean.Login;
import az.com.newazhong.utilsclass.model.LoginModel;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

import static az.com.newazhong.utilsclass.base.Constant.LOGIN;

/**
 * Created by dell on 2018/3/24.
 */

public class LoginModelimpl implements LoginModel {

    @Override
    public void getLoginModel(String httpTag, String phone, String pwd, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getLoginData(phone,pwd)
                ,new MySubscriberbean(httpTag,context, LOGIN, new BaseRequestBackLisenter<Login>() {
                    @Override
                    public void success(Login login) {
                        if (String.valueOf(login.isSuccess()).equals("false")){
                            Toast.makeText(context, login.getMsg(), Toast.LENGTH_SHORT).show();
                        }else {
                            baseModeBackLisenter.success(login);
                        }
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
