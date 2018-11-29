package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Version;
import az.com.newazhong.utilsclass.model.VersionModel;
import az.com.newazhong.utilsclass.model.modelimpl.VersionModelimpl;
import az.com.newazhong.utilsclass.presenter.VersionPresenter;
import az.com.newazhong.utilsclass.view.VersionView;

/**
 * Created by dell on 2018/3/22.
 */

public class VersionPresenterimpl implements VersionPresenter {
    private VersionModel model;
    private Context context;
    private VersionView view;

    //构造方法
    public VersionPresenterimpl(VersionView View, Context context){
        this.context=context;
        this.view=View;
        model=new VersionModelimpl();
    }

    @Override
    public void getVersionPresenter(String title) {
        model.getVersionModel(Constant.GETDATA,title,context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getVersionView((Version) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
