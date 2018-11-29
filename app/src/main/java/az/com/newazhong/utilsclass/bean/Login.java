package az.com.newazhong.utilsclass.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/24.
 */

public class Login implements Serializable {

    /**
     * success : true
     * errorCode : -1
     * msg : 登陆成功,请到个人中心完善资料！
     * body : {"userInfo":{"id":"09724cb2adae4472a2151f723a0d68d8","isNewRecord":false,"createDate":"2018-03-28 14:57:04","updateDate":"2018-03-28 14:57:04","phone":"15269179758","loginName":"hu","pwd":"23c9dfba5d73f38a82a35c092ca91bb01c843faae230141e01555d79"}}
     */

    private boolean success;
    private String errorCode;
    private String msg;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * userInfo : {"id":"09724cb2adae4472a2151f723a0d68d8","isNewRecord":false,"createDate":"2018-03-28 14:57:04","updateDate":"2018-03-28 14:57:04","phone":"15269179758","loginName":"hu","pwd":"23c9dfba5d73f38a82a35c092ca91bb01c843faae230141e01555d79"}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * id : 09724cb2adae4472a2151f723a0d68d8
             * isNewRecord : false
             * createDate : 2018-03-28 14:57:04
             * updateDate : 2018-03-28 14:57:04
             * phone : 15269179758
             * loginName : hu
             * pwd : 23c9dfba5d73f38a82a35c092ca91bb01c843faae230141e01555d79
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private String phone;
            private String loginName;
            private String pwd;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }
        }
    }
}