package az.com.newazhong.utilsclass.model.modelimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.bean.AddFind;
import az.com.newazhong.utilsclass.model.AddFindModel;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

import static az.com.newazhong.utilsclass.base.Constant.LOGIN;

/**
 * Created by dell on 2018/3/24.
 */

public class AddFindModelimpl implements AddFindModel {

    @Override
    public void getAddFindModel(String httpTag, String title, String content, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).addFind(title,content)
                ,new MySubscriberbean(httpTag,context, LOGIN, new BaseRequestBackLisenter<AddFind>() {
                    @Override
                    public void success(AddFind addFind) {
                            baseModeBackLisenter.success(addFind);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
