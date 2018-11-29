package az.com.newazhong.utilsclass.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * 获取手机相关信息
 */
public class PhoneUtils {
   public static  String getDeviceId(Context context){
       TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
       return tm.getDeviceId();
   }

    public static  String getType(Context context){
        return android.os.Build.MODEL;
    }

    public static  String getOx(Context context){
        return android.os.Build.VERSION.RELEASE;
    }
    public static  String getAppVersion(Context context){
        PackageManager pm = context.getPackageManager();
        String versionName = "1.0";
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return versionName;

    }


}
