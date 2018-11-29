package az.com.newazhong.utilsclass.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/30.
 */

public class CurrentDate {
    /**
     * 获取当前日期
     * @return
     */
    public String getCurrentData(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String currentDate = formatter.format(curDate);
        return currentDate;
    }


    /**
     * 获取当前时间
     * @return
     */
    public String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String currentTime = formatter.format(curDate);
        return currentTime;
    }
}
