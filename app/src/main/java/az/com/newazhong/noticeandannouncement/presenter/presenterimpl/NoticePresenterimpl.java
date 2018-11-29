package az.com.newazhong.noticeandannouncement.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.noticeandannouncement.mdoel.NoticeModel;
import az.com.newazhong.noticeandannouncement.mdoel.modelimpl.NoticeModelimpl;
import az.com.newazhong.noticeandannouncement.presenter.NoticePresenter;
import az.com.newazhong.noticeandannouncement.view.NoticeView;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/24.
 */

public class NoticePresenterimpl implements NoticePresenter {
    private NoticeModel model;
    private Context context;
    private NoticeView view;

    //构造方法
    public NoticePresenterimpl(NoticeView View, Context context){
        this.context=context;
        this.view=View;
        model=new NoticeModelimpl();
    }

    @Override
    public void getNoticePresenter(String type) {
        model.getNoticeModel(Constant.GETDATA,type,context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getNoticeView((Notice) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
