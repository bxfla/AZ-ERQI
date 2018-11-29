package az.com.newazhong.workoffice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/27.
 */

public class TypeFixRCAP implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":1732,"startDate":"2018-10-29 00:00:00","endDate":"2018-11-04 00:00:00","content":"<p>qqqqqqqqqqq<\/p>\n","creator":null,"createTime":"2018-10-25 15:38:27"}],"pageable":{"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"unpaged":false,"paged":true},"last":true,"totalPages":1,"totalElements":1,"number":0,"size":10,"sort":{"sorted":true,"unsorted":false},"numberOfElements":1,"first":true}
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
         * content : [{"id":1732,"startDate":"2018-10-29 00:00:00","endDate":"2018-11-04 00:00:00","content":"<p>qqqqqqqqqqq<\/p>\n","creator":null,"createTime":"2018-10-25 15:38:27"}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"unpaged":false,"paged":true}
         * last : true
         * totalPages : 1
         * totalElements : 1
         * number : 0
         * size : 10
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 1
         * first : true
         */

        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private int number;
        private int size;
        private SortBeanX sort;
        private int numberOfElements;
        private boolean first;
        private List<ContentBean> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
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

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
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

        public SortBeanX getSort() {
            return sort;
        }

        public void setSort(SortBeanX sort) {
            this.sort = sort;
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
             * unpaged : false
             * paged : true
             */

            private SortBean sort;
            private int offset;
            private int pageSize;
            private int pageNumber;
            private boolean unpaged;
            private boolean paged;

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

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
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

        public static class SortBeanX implements Serializable {
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

        public static class ContentBean implements Serializable {
            /**
             * id : 1732
             * startDate : 2018-10-29 00:00:00
             * endDate : 2018-11-04 00:00:00
             * content : <p>qqqqqqqqqqq</p>

             * creator : null
             * createTime : 2018-10-25 15:38:27
             */

            private int id;
            private String startDate;
            private String endDate;
            private String content;
            private Object creator;
            private String createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
