package az.com.newazhong.workguide.utils;

import az.com.newazhong.propagandwindow.bean.ShowPropagandWindow;

/**
 * Created by dell on 2018/3/26.
 */

public class MessageEventBean {
    private ShowPropagandWindow showPropagandWindow;

    public MessageEventBean(ShowPropagandWindow showPropagandWindow) {
        this.showPropagandWindow = showPropagandWindow;
    }

    public ShowPropagandWindow getMessage() {
        return showPropagandWindow;
    }

    public void ShowPropagandWindow(ShowPropagandWindow showPropagandWindow) {
        this.showPropagandWindow = showPropagandWindow;
    }
}