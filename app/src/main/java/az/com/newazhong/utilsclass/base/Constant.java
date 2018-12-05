package az.com.newazhong.utilsclass.base;

import az.com.newazhong.SharedPreferencesHelper;

/**
 * Created by dell on 2017/4/20.
 */

public class Constant {

    public static final String SESSION = new SharedPreferencesHelper(MyApplication.getContextObject(),"login").getData(MyApplication.getContextObject(),"session","");
    public static final int TAG_ONE = 1;
    public static final int TAG_TWO = 2;
    public static final int TAG_THREE = 3;
    public static final int TAG_FOUR = 4;
    public static final int TAG_FIVE = 5;
    public static final int TAG_SIX = 6;
    //http://113.128.69.196:9090/
    //http://113.128.69.196:9091
    public static final String BASE_URL="http://113.128.69.248:9000/";
    //public static final String BASE_URL="http://112.230.201.22:9000/";
    public static final String BASE_URL_NEW="http://113.128.68.106:9000/";
    public static final String BASE_URL_NEW_Image="http://124.133.227.214:62";
    public static final String GETDATA="获取数据中";
    public static final String UPDATE="提交数据中";
    public static final String REGISTER="注册中...";
    public static final String LOGIN="登陆中...";
    public static final String CHECK="修改中";

}
