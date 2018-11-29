package az.com.newazhong.utilsclass.base;

import rx.Subscription;

/**
 * 维护RXjava订阅池接口
 */

public interface RxActionManager<T> {
    /**
     * 添加请求tag
     * @param tag
     * @param subscription
     */
    void add(T tag, Subscription subscription);
    void remove(T tag);

    /**
     * 根据取消请求
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消所有的请求
     */
    void cancelAll();
}
