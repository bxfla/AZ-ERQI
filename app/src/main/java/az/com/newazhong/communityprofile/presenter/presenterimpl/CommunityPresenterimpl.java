package az.com.newazhong.communityprofile.presenter.presenterimpl;

import android.content.Context;

import az.com.newazhong.communityprofile.bean.Community;
import az.com.newazhong.communityprofile.model.CommunityModel;
import az.com.newazhong.communityprofile.model.model.CommunityModelimpl;
import az.com.newazhong.communityprofile.presenter.CommunityPresenter;
import az.com.newazhong.communityprofile.view.CommunityView;
import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by dell on 2018/3/22.
 */

public class CommunityPresenterimpl implements CommunityPresenter {
    private CommunityModel model;
    private Context context;
    private CommunityView view;

    //构造方法
    public CommunityPresenterimpl(CommunityView View, Context context){
        this.context=context;
        this.view=View;
        model=new CommunityModelimpl();
    }

    @Override
    public void getMDeptPresenterData() {
        model.getMDeptDataModel(Constant.GETDATA, context, new BaseModeBackLisenter() {
            @Override
            public void success(Object o) {
                view.getMDeptData((Community) o);
            }

            @Override
            public void error(String errorMessage) {
//                Looper.prepare();
//                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show();
//                Looper.loop();
            }
        });
    }
}
