package az.com.newazhong.noticeandannouncement.mdoel;

import android.content.Context;

import az.com.newazhong.utilsclass.base.BaseModeBackLisenter;

/**
 * Created by dell on 2018/3/24.
 */

public interface NoticeModelTest {
    void getNoticeModel(String httpTag, String type, String pageNo, String pageSize, Context context, BaseModeBackLisenter baseModeBackLisenter);
}
