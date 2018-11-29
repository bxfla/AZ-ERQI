package az.com.newazhong.street12345.activity.model.streetmodelimpl;

import android.content.Context;

import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.street12345.activity.model.StreetModel;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/24.
 */

public class StreetModelimpl implements StreetModel {

    @Override
    public void getStreetModel(String httpTag, Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getStreet()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<Street>() {
                    @Override
                    public void success(Street street) {
                        baseModeBackLisenter.success(street);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
