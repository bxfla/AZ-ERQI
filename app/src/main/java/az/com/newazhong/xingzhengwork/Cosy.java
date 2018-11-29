package az.com.newazhong.xingzhengwork;

import java.util.List;

/**
 * Created by dell on 2018/11/11.
 */

public class Cosy {

    /**
     * Success : 成功
     * Data : [{"CH":5201,"SSD":"舒适"},{"CH":5202,"SSD":"一般"}]
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
         * CH : 5201
         * SSD : 舒适
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
