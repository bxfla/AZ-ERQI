package az.com.newazhong.utilsclass.model.modelimpl;

import android.content.Context;
import android.widget.Toast;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.bean.Register;
import az.com.newazhong.utilsclass.model.RegisterModel;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/24.
 */

public class RegisterModelimpl implements RegisterModel {
    @Override
    public void getRegisterModel(String httpTag, String phone, String loginName, String pwd, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getRegisterData(phone,loginName,pwd)
                ,new MySubscriberbean(httpTag,context, Constant.REGISTER, new BaseRequestBackLisenter<Register>() {
                    @Override
                    public void success(Register register) {
                        if (String.valueOf(register.isSuccess()).equals("false")){
                            Toast.makeText(context, register.getMsg(), Toast.LENGTH_SHORT).show();
                        }else {
                            baseModeBackLisenter.success(register);
                        }
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
