package az.com.newazhong.propagandwindow.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.propagandwindow.bean.ActivityPhoto;
import az.com.newazhong.propagandwindow.model.ActivityPhotoModel;
import az.com.newazhong.propagandwindow.model.modelimpl.ActivityPhotoModelimpl;
import az.com.newazhong.propagandwindow.presenter.ActivityPhotoPresenter;
import az.com.newazhong.propagandwindow.view.ActivityPhotoView;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/24.
 */

public class ActivityPhotoPresenterimpl implements ActivityPhotoPresenter {
    private ActivityPhotoModel model;
    private Context context;
    private ActivityPhotoView view;

    //构造方法
    public ActivityPhotoPresenterimpl(ActivityPhotoView View, Context context){
        this.context=context;
        this.view=View;
        model=new ActivityPhotoModelimpl();
    }


    @Override
    public void getActivityPhotoPresenter() {
        model.getActivityPhotoModel(Constant.GETDATA, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getActivityPhotoView((ActivityPhoto) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
