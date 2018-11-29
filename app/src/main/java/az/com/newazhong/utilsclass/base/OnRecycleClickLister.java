package az.com.newazhong.utilsclass.base;

/**
 * recyleView 点击监听事件
 */

public interface OnRecycleClickLister<T> {
    void click(int positon, T t);
}
