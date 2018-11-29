package az.com.newazhong.workoffice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/26.
 */

public class WorkOfficeUrlList implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":1749,"name":"用章申请","approvalItems":[{"name":"是由","required":true,"type":"文本","mOptions":[],"sort":1},{"name":"使用时间","required":true,"type":"时间（秒）","mOptions":[],"sort":2},{"name":"附件","required":true,"type":"附件","mOptions":[],"sort":3}],"enable":true,"createTime":"2018-10-27 09:12:07","modifiedTime":"2018-10-27 09:34:06"},{"id":1747,"name":"请假申请","approvalItems":[{"name":"开始时间","required":true,"type":"时间（秒）","mOptions":[],"sort":1},{"name":"结束时间","required":true,"type":"时间（秒）","mOptions":[],"sort":2},{"name":"是由","required":true,"type":"文本","mOptions":[],"sort":3},{"name":"类型","required":true,"type":"多选框","mOptions":["年假","病假","事假","婚假"],"sort":4},{"name":"附件","required":true,"type":"附件","mOptions":[],"sort":5}],"enable":true,"createTime":"2018-10-26 15:36:12","modifiedTime":"2018-10-27 09:29:57"}],"pageable":{"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false},"totalElements":3,"last":true,"totalPages":1,"number":0,"size":10,"sort":{"sorted":true,"unsorted":false},"numberOfElements":3,"first":true}
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
         * content : [{"id":1749,"name":"用章申请","approvalItems":[{"name":"是由","required":true,"type":"文本","mOptions":[],"sort":1},{"name":"使用时间","required":true,"type":"时间（秒）","mOptions":[],"sort":2},{"name":"附件","required":true,"type":"附件","mOptions":[],"sort":3}],"enable":true,"createTime":"2018-10-27 09:12:07","modifiedTime":"2018-10-27 09:34:06"},{"id":1747,"name":"请假申请","approvalItems":[{"name":"开始时间","required":true,"type":"时间（秒）","mOptions":[],"sort":1},{"name":"结束时间","required":true,"type":"时间（秒）","mOptions":[],"sort":2},{"name":"是由","required":true,"type":"文本","mOptions":[],"sort":3},{"name":"类型","required":true,"type":"多选框","mOptions":["年假","病假","事假","婚假"],"sort":4},{"name":"附件","required":true,"type":"附件","mOptions":[],"sort":5}],"enable":true,"createTime":"2018-10-26 15:36:12","modifiedTime":"2018-10-27 09:29:57"}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false}
         * totalElements : 3
         * last : true
         * totalPages : 1
         * number : 0
         * size : 10
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 3
         * first : true
         */

        private PageableBean pageable;
        private int totalElements;
        private boolean last;
        private int totalPages;
        private int number;
        private int size;
        private int numberOfElements;
        private boolean first;
        private List<ContentBean> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean implements Serializable {
            /**
             * sort : {"sorted":true,"unsorted":false}
             * offset : 0
             * pageSize : 10
             * pageNumber : 0
             * paged : true
             * unpaged : false
             */

            private SortBean sort;
            private int offset;
            private int pageSize;
            private int pageNumber;
            private boolean paged;
            private boolean unpaged;

            public SortBean getSort() {
                return sort;
            }

            public void setSort(SortBean sort) {
                this.sort = sort;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public static class SortBean implements Serializable {
                /**
                 * sorted : true
                 * unsorted : false
                 */

                private boolean sorted;
                private boolean unsorted;

                public boolean isSorted() {
                    return sorted;
                }

                public void setSorted(boolean sorted) {
                    this.sorted = sorted;
                }

                public boolean isUnsorted() {
                    return unsorted;
                }

                public void setUnsorted(boolean unsorted) {
                    this.unsorted = unsorted;
                }
            }
        }

        public static class ContentBean implements Serializable {
            /**
             * id : 1749
             * name : 用章申请
             * approvalItems : [{"name":"是由","required":true,"type":"文本","mOptions":[],"sort":1},{"name":"使用时间","required":true,"type":"时间（秒）","mOptions":[],"sort":2},{"name":"附件","required":true,"type":"附件","mOptions":[],"sort":3}]
             * enable : true
             * createTime : 2018-10-27 09:12:07
             * modifiedTime : 2018-10-27 09:34:06
             */

            private int id;
            private String name;
            private boolean enable;
            private String createTime;
            private String modifiedTime;
            private List<ApprovalItemsBean> approvalItems;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
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

            public List<ApprovalItemsBean> getApprovalItems() {
                return approvalItems;
            }

            public void setApprovalItems(List<ApprovalItemsBean> approvalItems) {
                this.approvalItems = approvalItems;
            }

            public static class ApprovalItemsBean implements Serializable {
                /**
                 * name : 是由
                 * required : true
                 * type : 文本
                 * mOptions : []
                 * sort : 1
                 */

                private String name;
                private boolean required;
                private String type;
                private int sort;
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

                public List<String> getMOptions() {
                    return mOptions;
                }

                public void setMOptions(List<String> mOptions) {
                    this.mOptions = mOptions;
                }
            }
        }
    }
}
