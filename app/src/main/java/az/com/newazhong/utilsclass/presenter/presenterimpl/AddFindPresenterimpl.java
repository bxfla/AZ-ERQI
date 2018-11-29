package az.com.newazhong.utilsclass.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.AddFind;
import az.com.newazhong.utilsclass.model.AddFindModel;
import az.com.newazhong.utilsclass.model.modelimpl.AddFindModelimpl;
import az.com.newazhong.utilsclass.presenter.AddFindPresenter;
import az.com.newazhong.utilsclass.view.AddFindView;

/**
 * Created by dell on 2018/3/22.
 */

public class AddFindPresenterimpl implements AddFindPresenter {
    private AddFindModel model;
    private Context context;
    private AddFindView view;

    //构造方法
    public AddFindPresenterimpl(AddFindView View, Context context){
        this.context=context;
        this.view=View;
        model=new AddFindModelimpl();
    }


    @Override
    public void getAddFindPresenter(String title, String content) {
        model.getAddFindModel(Constant.GETDATA,title,content,context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getAddFindView((AddFind) o);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
