package az.com.newazhong.utilsclass.model;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/22.
 */

public interface VersionModel {
    void getVersionModel(String httpTag, String title, Context context, BaseModeBackLisenter baseModeBackLisenter);
}
