package az.com.newazhong.utilsclass.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dell on 2017/5/8.
 */

public class MyApplication extends Application {
    public  static   boolean IS_DEBUG=false;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();
        IS_DEBUG=true;
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
    }
    //返回
    public static Context getContextObject(){
        return context;
    }
}
