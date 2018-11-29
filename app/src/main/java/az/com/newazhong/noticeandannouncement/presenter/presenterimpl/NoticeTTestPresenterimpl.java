package az.com.newazhong.noticeandannouncement.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.noticeandannouncement.mdoel.NoticeModelTest;
import az.com.newazhong.noticeandannouncement.mdoel.modelimpl.NoticeTestModelimpl;
import az.com.newazhong.noticeandannouncement.presenter.NoticeTestPresenter;
import az.com.newazhong.noticeandannouncement.view.NoticeViewTest;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/24.
 */

public class NoticeTTestPresenterimpl implements NoticeTestPresenter {
    private NoticeModelTest model;
    private Context context;
    private NoticeViewTest view;

    //构造方法
    public NoticeTTestPresenterimpl(NoticeViewTest View, Context context){
        this.context=context;
        this.view=View;
        model=new NoticeTestModelimpl();
    }


    @Override
    public void getNoticePresenter(String type, String pageNo, String pageSize) {
        model.getNoticeModel(Constant.GETDATA,type,pageNo,pageSize,context, new BaseModeBackLisenter() {
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
