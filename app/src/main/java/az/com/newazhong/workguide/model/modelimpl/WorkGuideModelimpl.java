package az.com.newazhong.workguide.model.modelimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;
import az.com.newazhong.workguide.bean.WorkGuide;
import az.com.newazhong.workguide.model.WorkGuideModel;

/**
 * Created by dell on 2018/3/24.
 */

public class WorkGuideModelimpl implements WorkGuideModel {
    @Override
    public void getWorkGuideModel(String httpTag, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getWorkGuideData()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<WorkGuide>() {
                    @Override
                    public void success(WorkGuide workGuide) {
                            baseModeBackLisenter.success(workGuide);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
