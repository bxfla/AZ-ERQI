package az.com.newazhong.utilsclass.model.modelimpl;

import android.content.Context;

import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MySubscriberbean;
import az.com.newazhong.utilsclass.base.RetrofitService;
import az.com.newazhong.utilsclass.model.PaoMaModel;
import az.com.newazhong.utilsclass.utils.HttpUtilsNew;

/**
 * Created by dell on 2018/3/24.
 */

public class PaoMaModelimpl implements PaoMaModel {

    @Override
    public void getNoticeModel(String httpTag, final Context context, final BaseModeBackLisenter baseModeBackLisenter) {
        HttpUtilsNew.initlist(HttpUtilsNew.getService(RetrofitService.class).getAllNoticetData()
                ,new MySubscriberbean(httpTag,context, Constant.GETDATA, new BaseRequestBackLisenter<Notice>() {
                    @Override
                    public void success(Notice notice) {
                            baseModeBackLisenter.success(notice);
                    }

                    @Override
                    public void fail(String message) {
                        baseModeBackLisenter.error(message);
                    }
                }));
    }
}
