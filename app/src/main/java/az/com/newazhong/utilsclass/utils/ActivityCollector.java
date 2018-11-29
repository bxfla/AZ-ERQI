package az.com.newazhong.utilsclass.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dell on 2018/5/23.
 */

public class ActivityCollector extends Activity {
    private static List<Activity> activities=new ArrayList<Activity>() ;


    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    /**
     * 关闭指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activities != null && activity != null && activities.contains(activity)) {
            //使用迭代器安全删除
            for (Iterator<Activity> it = activities.iterator(); it.hasNext(); ) {
                Activity temp = it.next();
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove();
                    continue;
                }
                if (temp == activity) {
                    it.remove();
                }
            }
            activity.finish();
        }
    }
    /**
     * 关闭指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activities != null) {
            // 使用迭代器安全删除
            for (Iterator<Activity> it = activities.iterator(); it.hasNext(); ) {
                Activity activity = it.next();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    it.remove();
                    activity.finish();
                }
            }
        }
    }
    public static void finidhAll() {
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}