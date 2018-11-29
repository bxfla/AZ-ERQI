package az.com.newazhong.propagandwindow.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/9/2.
 */

public class ActivityContent implements Serializable {


    /**
     * result : true
     * message : null
     * data : {"content":[{"id":129,"article":{"title":"2222222","content":"<p>2222222222<\/p>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","enrol":{"enrolStartDate":null,"enrolEndDate":null,"openEnrol":false},"createTime":"2018-08-18 09:59:11","creator":null,"modifiedTime":"2018-08-18 09:59:11","modifier":null}],"pageable":{"sort":{"sorted":true,"unsorted":false},"pageSize":10,"pageNumber":0,"offset":0,"paged":true,"unpaged":false},"last":true,"totalPages":1,"totalElements":5,"first":true,"sort":{"sorted":true,"unsorted":false},"numberOfElements":5,"size":10,"number":0}
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

    public static class DataBean implements Serializable{
        /**
         * content : [{"id":129,"article":{"title":"2222222","content":"<p>2222222222<\/p>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","enrol":{"enrolStartDate":null,"enrolEndDate":null,"openEnrol":false},"createTime":"2018-08-18 09:59:11","creator":null,"modifiedTime":"2018-08-18 09:59:11","modifier":null}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"pageSize":10,"pageNumber":0,"offset":0,"paged":true,"unpaged":false}
         * last : true
         * totalPages : 1
         * totalElements : 5
         * first : true
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 5
         * size : 10
         * number : 0
         */

        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
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

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean implements Serializable{
            /**
             * sort : {"sorted":true,"unsorted":false}
             * pageSize : 10
             * pageNumber : 0
             * offset : 0
             * paged : true
             * unpaged : false
             */

            private SortBean sort;
            private int pageSize;
            private int pageNumber;
            private int offset;
            private boolean paged;
            private boolean unpaged;

            public SortBean getSort() {
                return sort;
            }

            public void setSort(SortBean sort) {
                this.sort = sort;
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

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
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

            public static class SortBean implements Serializable{
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

        public static class ContentBean implements Serializable{
            /**
             * id : 129
             * article : {"title":"2222222","content":"<p>2222222222<\/p>","status":"UNAUDITED"}
             * departmentId : 4
             * departmentName : 张庄路街道办事处
             * enrol : {"enrolStartDate":null,"enrolEndDate":null,"openEnrol":false}
             * createTime : 2018-08-18 09:59:11
             * creator : null
             * modifiedTime : 2018-08-18 09:59:11
             * modifier : null
             */

            private int id;
            private ArticleBean article;
            private int departmentId;
            private String departmentName;
            private EnrolBean enrol;
            private String createTime;
            private String modifiedTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public ArticleBean getArticle() {
                return article;
            }

            public void setArticle(ArticleBean article) {
                this.article = article;
            }

            public int getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(int departmentId) {
                this.departmentId = departmentId;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public EnrolBean getEnrol() {
                return enrol;
            }

            public void setEnrol(EnrolBean enrol) {
                this.enrol = enrol;
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

            public static class ArticleBean implements Serializable{
                /**
                 * title : 2222222
                 * content : <p>2222222222</p>
                 * status : UNAUDITED
                 */

                private String title;
                private String content;
                private String status;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }

            public static class EnrolBean implements Serializable{
                /**
                 * enrolStartDate : null
                 * enrolEndDate : null
                 * openEnrol : false
                 */

                private boolean openEnrol;

                public boolean isOpenEnrol() {
                    return openEnrol;
                }

                public void setOpenEnrol(boolean openEnrol) {
                    this.openEnrol = openEnrol;
                }
            }
        }
    }
}
