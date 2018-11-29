package az.com.newazhong.utilsclass.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/28.
 */

public class Version implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"id":1,"version":1,"url":"http://112.230.201.22:9000/app/zzbsc.apk","type":"ANDROID","createTime":"2018-11-27 10:09:05","new":false}
     */

    private boolean result;
    private Object message;
    private DataBean data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * version : 1
         * url : http://112.230.201.22:9000/app/zzbsc.apk
         * type : ANDROID
         * createTime : 2018-11-27 10:09:05
         * new : false
         */

        private int id;
        private int version;
        private String url;
        private String type;
        private String createTime;
        @SerializedName("new")
        private boolean newX;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
        }
    }
}
