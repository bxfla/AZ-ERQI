package az.com.newazhong.noticeandannouncement.view;

import android.os.Bundle;
import android.view.View;

import az.com.newazhong.noticeandannouncement.bean.Notice;

/**
 * Created by dell on 2018/3/27.
 */

public interface NoticeViewTest {
    void onActivityCreated(View view, Bundle savedInstanceState);

   void getNoticeView(Notice notice);
}
