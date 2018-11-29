package az.com.newazhong.utilsclass.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/24.
 */

public class Register implements Serializable {

    /**
     * success : true
     * errorCode : -1
     * msg : 注册成功！
     */

    private boolean success;
    private String errorCode;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
