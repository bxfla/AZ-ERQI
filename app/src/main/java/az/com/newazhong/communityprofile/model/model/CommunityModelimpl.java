package az.com.newazhong.communityprofile.model.model;

import android.content.Context;

import az.com.newazhong.communityprofile.bean.Community;
import az.com.newazhong.communityprofile.model.CommunityModel;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/23.
 */

public class CommunityModelimpl implements CommunityModel {
    @Override
    public void getMDeptDataModel(String httpTag,  final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getMDeptData()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<az.com.newazhong.communityprofile.bean.Community>() {
                    @Override
                    public void success(Community mDept) {
//                        if (String.valueOf(mDept.isSuccess()).equals("false")){
//                            Toast.makeText(context, mDept.getMsg(), Toast.LENGTH_SHORT).show();
//                        }else {
                            baseModeBackLisenter.success(mDept);
//                        }
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
