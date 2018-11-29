package az.com.newazhong.propagandwindow.model.modelimpl;

import android.content.Context;

import az.com.newazhong.propagandwindow.bean.PropagandWindow;
import az.com.newazhong.propagandwindow.model.PropagandWindowModel;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/24.
 */

public class PropagandWindowModelimpl implements PropagandWindowModel {

    @Override
    public void getPropagandWindowModel(String httpTag, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getPropagandWindowData()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<PropagandWindow>() {
                    @Override
                    public void success(PropagandWindow propagandWindow) {
//                        if (String.valueOf(propagandWindow.isSuccess()).equals("false")){
//                            Toast.makeText(context, propagandWindow.getMsg(), Toast.LENGTH_SHORT).show();
//                        }else {
                            baseModeBackLisenter.success(propagandWindow);
//                        }
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
