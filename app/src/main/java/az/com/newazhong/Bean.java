package az.com.newazhong;

import java.util.List;

/**
 * Created by dell on 2018/9/6.
 */

public class Bean {

    /**
     * code : 0
     * msg : 登录成功
     * resultBody : [{"id":"20","username":"18963088252","passwd":"e10adc3949ba59abbe56e057f20f883e","sell_id":"0","time":"1536154051"}]
     */

    private String code;
    private String msg;
    private List<ResultBodyBean> resultBody;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBodyBean> getResultBody() {
        return resultBody;
    }

    public void setResultBody(List<ResultBodyBean> resultBody) {
        this.resultBody = resultBody;
    }

    public static class ResultBodyBean {
        /**
         * id : 20
         * username : 18963088252
         * passwd : e10adc3949ba59abbe56e057f20f883e
         * sell_id : 0
         * time : 1536154051
         */

        private String id;
        private String username;
        private String passwd;
        private String sell_id;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public String getSell_id() {
            return sell_id;
        }

        public void setSell_id(String sell_id) {
            this.sell_id = sell_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
