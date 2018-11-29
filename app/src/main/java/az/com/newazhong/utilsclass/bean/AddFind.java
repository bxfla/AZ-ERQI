package az.com.newazhong.utilsclass.bean;

/**
 * Created by dell on 2018/8/27.
 */

public class AddFind {

    /**
     * result : false
     * message : 当前办事指南不存在。
     */

    private boolean result;
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
