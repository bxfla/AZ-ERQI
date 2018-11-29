package az.com.newazhong.utilsclass.model;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/22.
 */

public interface AddFindModel {
    void getAddFindModel(String httpTag, String title, String content,
                         Context context, BaseModeBackLisenter baseModeBackLisenter);
}
