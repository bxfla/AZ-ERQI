package az.com.newazhong.utilsclass.model.modelimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.bean.Version;
import az.com.newazhong.utilsclass.model.VersionModel;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

import static az.com.newazhong.utilsclass.base.Constant.LOGIN;

/**
 * Created by dell on 2018/3/24.
 */

public class VersionModelimpl implements VersionModel {

    @Override
    public void getVersionModel(String httpTag, String type, Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getVersion(type)
                ,new MySubscriberbean(httpTag,context, LOGIN, new BaseRequestBackLisenter<Version>() {
                    @Override
                    public void success(Version version) {
                        baseModeBackLisenter.success(version);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
