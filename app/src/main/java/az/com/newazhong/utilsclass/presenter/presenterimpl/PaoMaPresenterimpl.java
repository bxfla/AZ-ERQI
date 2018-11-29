package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.model.PaoMaModel;
import az.com.newazhong.utilsclass.model.modelimpl.PaoMaModelimpl;
import az.com.newazhong.utilsclass.presenter.PaoMaPresenter;
import az.com.newazhong.utilsclass.view.PaoMaView;

/**
 * Created by dell on 2018/3/24.
 */

public class PaoMaPresenterimpl implements PaoMaPresenter {
    private PaoMaModel model;
    private Context context;
    private PaoMaView view;

    //构造方法
    public PaoMaPresenterimpl(PaoMaView View, Context context){
        this.context=context;
        this.view=View;
        model=new PaoMaModelimpl();
    }

    @Override
    public void getNoticePresenter() {
        model.getNoticeModel(Constant.GETDATA,context, new BaseModeBackLisenter() {
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
