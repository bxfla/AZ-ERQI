package az.com.newazhong.utilsclass.utils;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import az.com.newazhong.utilsclass.base.BaseFastJSONRequestBackLisenter;
import okhttp3.Call;

/**
 * Created by dell on 2018/8/27.
 */

public class FastUtils {


    public static void getFastData(String url, final BaseFastJSONRequestBackLisenter baseRequestBackLisenter){
        OkHttpUtils.get().url(url).build().execute(new StringCallback(){
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("XXX","首页请求失败=="+e.getMessage());
                baseRequestBackLisenter.fail(e.getMessage());
            }
            @Override
            public void onResponse(String response, int id) {
                Log.e("XXX","首页请求成功=="+response);
                baseRequestBackLisenter.success(response);
            }
        });
    }

    public static void getFastHData(String url,String session, final BaseFastJSONRequestBackLisenter baseRequestBackLisenter){
        OkHttpUtils.get().url(url).addHeader("Cookie",session).build().execute(new StringCallback(){
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("XXX","首页请求失败=="+e.getMessage());
                baseRequestBackLisenter.fail(e.getMessage());
            }
            @Override
            public void onResponse(String response, int id) {
                Log.e("XXX","首页请求成功=="+response);
                baseRequestBackLisenter.success(response);
            }
        });
    }

    public static void postFastData(String url, final BaseFastJSONRequestBackLisenter baseRequestBackLisenter){
        OkHttpUtils.post().url(url).addParams("mobile","15269179758").addParams("booker","胡晓峰").addParams("mienid","393")
               .addHeader("Cookie","E645A97538D6C84471629A93AD30D0E2").build() .execute(new StringCallback(){
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("XXXX","首页请求失败=="+e.getMessage()+"XXX"+call.request().toString());
                baseRequestBackLisenter.fail(e.getMessage());
            }
            @Override
            public void onResponse(String response, int id) {
                Log.e("XXX","首页请求成功=="+response);
                baseRequestBackLisenter.success(response);
            }
        });
    }
}
