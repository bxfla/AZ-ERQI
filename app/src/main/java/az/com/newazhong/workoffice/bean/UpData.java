package az.com.newazhong.workoffice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/6.
 */

public class UpData implements Serializable {

    private List<ApprovalGroupBean> approvalGroup;
    private List<ApprovalItemsBean> approvalItems;
    private List<String> ccUserId;

    public List<ApprovalGroupBean> getApprovalGroup() {
        return approvalGroup;
    }

    public void setApprovalGroup(List<ApprovalGroupBean> approvalGroup) {
        this.approvalGroup = approvalGroup;
    }

    public List<ApprovalItemsBean> getApprovalItems() {
        return approvalItems;
    }

    public void setApprovalItems(List<ApprovalItemsBean> approvalItems) {
        this.approvalItems = approvalItems;
    }

    public List<String> getCcUserId() {
        return ccUserId;
    }

    public void setCcUserId(List<String> ccUserId) {
        this.ccUserId = ccUserId;
    }

    public static class ApprovalGroupBean implements Serializable {
        public String getAdopt() {
            return adopt;
        }

        public void setAdopt(String adopt) {
            this.adopt = adopt;
        }

        /**
         * adopt : true
         * auditor : string
         * memo : string
         * sort : 0
         * userId : 0
         */


        private String adopt;
        private String auditor;
        private String memo;
        private int sort;
        private int userId;

        public String getAuditor() {
            return auditor;
        }

        public void setAuditor(String auditor) {
            this.auditor = auditor;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class ApprovalItemsBean implements Serializable {
        /**
         * mOptions : ["string"]
         * name : string
         * required : true
         * sort : 0
         * type : string
         * value : string
         */

        private String name;
        private boolean required;
        private int sort;
        private String type;
        private String value;
        private List<String> mOptions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<String> getMOptions() {
            return mOptions;
        }

        public void setMOptions(List<String> mOptions) {
            this.mOptions = mOptions;
        }


        public void setMOptions1(String s) {
        }
    }
}
