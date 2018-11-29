package az.com.newazhong.street12345.activity.model;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/30.
 */

public interface StreetModel {
    void getStreetModel(String httpTag, Context context, BaseModeBackLisenter baseModeBackLisenter);
}