package az.com.newazhong.utilsclass.model;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/22.
 */

public interface BannerModel {
    /**
     * 获取fragment banner图片
     * @param httpTag
     * @param context
     * @param baseModeBackLisenter
     */
    void getBannerImageModel(String httpTag, Context context, BaseModeBackLisenter baseModeBackLisenter);
}
