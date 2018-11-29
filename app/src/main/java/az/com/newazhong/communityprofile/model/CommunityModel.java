package az.com.newazhong.communityprofile.model;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/23.
 */

public interface CommunityModel {
    /**
     * 获取内部机构数据
     * @param httpTag
     * @param context
     * @param baseModeBackLisenter
     */
    void getMDeptDataModel(String httpTag, Context context, BaseModeBackLisenter baseModeBackLisenter);
}
