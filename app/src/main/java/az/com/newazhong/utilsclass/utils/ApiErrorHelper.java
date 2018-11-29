package az.com.newazhong.utilsclass.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by dell on 2017/5/8.
 */

//辅助处理异常
public class ApiErrorHelper {

    public static void handleCommonError(Context context, Throwable e) {
        if (e instanceof HttpException) {
            Toast.makeText(context, "服务暂不可用", Toast.LENGTH_SHORT).show();
        } else if (e instanceof IOException) {
            Toast.makeText(context, "服务器连接失败", Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
        }
    }

}
