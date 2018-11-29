package az.com.newazhong.utilsclass.bean;

import java.util.List;

public class Cosy {

    /**
     * Success : 成功
     * Data : [{"CH":1,"SSD":"\u201d舒适\u201d"},{"CH":2,"SSD":"\u201d舒适\u201d"}]
     */

    private String Success;
    private List<DataBean> Data;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * CH : 1
         * SSD : ”舒适”
         */

        private int CH;
        private String SSD;

        public int getCH() {
            return CH;
        }

        public void setCH(int CH) {
            this.CH = CH;
        }

        public String getSSD() {
            return SSD;
        }

        public void setSSD(String SSD) {
            this.SSD = SSD;
        }
    }
}
