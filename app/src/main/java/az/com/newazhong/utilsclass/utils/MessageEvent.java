package az.com.newazhong.utilsclass.utils;

/**
 * Created by dell on 2018/3/26.
 */

public class MessageEvent{
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}