package az.com.newazhong.propagandwindow.model.modelimpl;

import android.content.Context;

import az.com.newazhong.propagandwindow.bean.ActivityPhoto;
import az.com.newazhong.propagandwindow.model.ActivityPhotoModel;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/24.
 */

public class ActivityPhotoModelimpl implements ActivityPhotoModel {

    @Override
    public void getActivityPhotoModel(String httpTag,  final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getActivityPhoto()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<ActivityPhoto>() {
                    @Override
                    public void success(ActivityPhoto activityPhoto) {
                            baseModeBackLisenter.success(activityPhoto);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
