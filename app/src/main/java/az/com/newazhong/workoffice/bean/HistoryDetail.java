package az.com.newazhong.workoffice.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/8.
 */

public class HistoryDetail implements Serializable{


    /**
     * result : true
     * message : null
     * data : {"id":1935,"proposerId":1,"proposerName":"admin","approvalName":"财务报销","items":[{"name":"报销金额","required":true,"type":"数字123","mOptions":[],"sort":1,"value":"500"},{"name":"报销类别","required":true,"type":"文本(单行)","mOptions":[],"sort":2,"value":"差旅费"},{"name":"费用明细","required":true,"type":"文本(多行)","mOptions":[],"sort":3,"value":"上海培训学习费用"}],"approvalStatus":"UNAUDITED","approvalGroup":{"1":{"auditor":"党工办","userId":289,"adopt":null,"memo":null}},"ccUsers":["计生科"],"createTime":"2018-11-06 13:59:31","modifiedTime":"2018-11-06 13:59:31"}
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
         * id : 1935
         * proposerId : 1
         * proposerName : admin
         * approvalName : 财务报销
         * items : [{"name":"报销金额","required":true,"type":"数字123","mOptions":[],"sort":1,"value":"500"},{"name":"报销类别","required":true,"type":"文本(单行)","mOptions":[],"sort":2,"value":"差旅费"},{"name":"费用明细","required":true,"type":"文本(多行)","mOptions":[],"sort":3,"value":"上海培训学习费用"}]
         * approvalStatus : UNAUDITED
         * approvalGroup : {"1":{"auditor":"党工办","userId":289,"adopt":null,"memo":null}}
         * ccUsers : ["计生科"]
         * createTime : 2018-11-06 13:59:31
         * modifiedTime : 2018-11-06 13:59:31
         */

        private int id;
        private int proposerId;
        private String proposerName;
        private String approvalName;
        private String approvalStatus;
        private ApprovalGroupBean approvalGroup;
        private String createTime;
        private String modifiedTime;
        private List<ItemsBean> items;
        private List<String> ccUsers;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProposerId() {
            return proposerId;
        }

        public void setProposerId(int proposerId) {
            this.proposerId = proposerId;
        }

        public String getProposerName() {
            return proposerName;
        }

        public void setProposerName(String proposerName) {
            this.proposerName = proposerName;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public ApprovalGroupBean getApprovalGroup() {
            return approvalGroup;
        }

        public void setApprovalGroup(ApprovalGroupBean approvalGroup) {
            this.approvalGroup = approvalGroup;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifiedTime() {
            return modifiedTime;
        }

        public void setModifiedTime(String modifiedTime) {
            this.modifiedTime = modifiedTime;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public List<String> getCcUsers() {
            return ccUsers;
        }

        public void setCcUsers(List<String> ccUsers) {
            this.ccUsers = ccUsers;
        }

        public static class ApprovalGroupBean implements Serializable {
            /**
             * 1 : {"auditor":"党工办","userId":289,"adopt":null,"memo":null}
             */

            @SerializedName("1")
            private _$1Bean _$1;

            public _$1Bean get_$1() {
                return _$1;
            }

            public void set_$1(_$1Bean _$1) {
                this._$1 = _$1;
            }

            public static class _$1Bean implements Serializable {
                /**
                 * auditor : 党工办
                 * userId : 289
                 * adopt : null
                 * memo : null
                 */

                private String auditor;
                private int userId;
                private Object adopt;
                private Object memo;

                public String getAuditor() {
                    return auditor;
                }

                public void setAuditor(String auditor) {
                    this.auditor = auditor;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public Object getAdopt() {
                    return adopt;
                }

                public void setAdopt(Object adopt) {
                    this.adopt = adopt;
                }

                public Object getMemo() {
                    return memo;
                }

                public void setMemo(Object memo) {
                    this.memo = memo;
                }
            }
        }

        public static class ItemsBean implements Serializable {
            /**
             * name : 报销金额
             * required : true
             * type : 数字123
             * mOptions : []
             * sort : 1
             * value : 500
             */

            private String name;
            private boolean required;
            private String type;
            private int sort;
            private String value;
            private List<?> mOptions;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<?> getMOptions() {
                return mOptions;
            }

            public void setMOptions(List<?> mOptions) {
                this.mOptions = mOptions;
            }
        }
    }
}
