package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Banner;
import az.com.newazhong.utilsclass.model.BannerModel;
import az.com.newazhong.utilsclass.model.modelimpl.BannerModelimpl;
import az.com.newazhong.utilsclass.presenter.BannerPresenter;
import az.com.newazhong.utilsclass.view.BannerView;

/**
 * Created by dell on 2018/3/22.
 */

public class BannerPresenterimpl implements BannerPresenter {
    private BannerModel model;
    private Context context;
    private BannerView view;

    //构造方法
    public BannerPresenterimpl(BannerView View, Context context){
        this.context=context;
        this.view=View;
        model=new BannerModelimpl();
    }

    @Override
    public void getBannerImagePresenter() {
        model.getBannerImageModel(Constant.GETDATA,context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getBannerImage((Banner) o);
            }

            @Override
            public void error(String errorMessage) {
                Looper.prepare();
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
    }
}
