package az.com.newazhong.utilsclass.base;

/**
 * Created by dell on 2017/4/20.
 */

public interface BasePresent {
    /**
     * 销毁方法
     */
    void onDestroy();

    /**
     * 开始方法
     */
    void onStart();

    /**
     * 取消相应的网络请求
     */
    void cancelHttp();
}
