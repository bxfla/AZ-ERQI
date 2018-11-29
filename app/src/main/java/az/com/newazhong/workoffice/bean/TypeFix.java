package az.com.newazhong.workoffice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/26.
 */

public class TypeFix implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":1729,"title":"222","startTime":"2018-10-25 00:00:00","address":"2aaa","content":"<p>assa<\/p>\n","ccUsers":["人力资源社会保障中心","计生科"],"pictures":[],"type":"MEETING_NOTICE","departmentName":"服务管理员","sponsor":"admin","creator":null,"createTime":"2018-10-25 15:32:31"},{"id":1728,"title":"会议通知1","startTime":"2018-10-25 00:00:03","address":"会议通知","content":"<p>会议通知111111111111111<\/p>\n","ccUsers":["人力资源社会保障中心","城管科","计生科"],"pictures":[],"type":"MEETING_NOTICE","departmentName":"服务管理员","sponsor":"admin","creator":null,"createTime":"2018-10-25 15:29:55"}],"pageable":{"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"unpaged":false,"paged":true},"totalElements":2,"last":true,"totalPages":1,"number":0,"size":10,"sort":{"sorted":true,"unsorted":false},"numberOfElements":2,"first":true}
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
         * content : [{"id":1729,"title":"222","startTime":"2018-10-25 00:00:00","address":"2aaa","content":"<p>assa<\/p>\n","ccUsers":["人力资源社会保障中心","计生科"],"pictures":[],"type":"MEETING_NOTICE","departmentName":"服务管理员","sponsor":"admin","creator":null,"createTime":"2018-10-25 15:32:31"},{"id":1728,"title":"会议通知1","startTime":"2018-10-25 00:00:03","address":"会议通知","content":"<p>会议通知111111111111111<\/p>\n","ccUsers":["人力资源社会保障中心","城管科","计生科"],"pictures":[],"type":"MEETING_NOTICE","departmentName":"服务管理员","sponsor":"admin","creator":null,"createTime":"2018-10-25 15:29:55"}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"unpaged":false,"paged":true}
         * totalElements : 2
         * last : true
         * totalPages : 1
         * number : 0
         * size : 10
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 2
         * first : true
         */

        private PageableBean pageable;
        private int totalElements;
        private boolean last;
        private int totalPages;
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

        public static class PageableBean implements Serializable{
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

        public static class SortBeanX implements Serializable{
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

        public static class ContentBean implements Serializable{
            /**
             * id : 1729
             * title : 222
             * startTime : 2018-10-25 00:00:00
             * address : 2aaa
             * content : <p>assa</p>

             * ccUsers : ["人力资源社会保障中心","计生科"]
             * pictures : []
             * type : MEETING_NOTICE
             * departmentName : 服务管理员
             * sponsor : admin
             * creator : null
             * createTime : 2018-10-25 15:32:31
             */

            private int id;
            private String title;
            private String startTime;
            private String address;
            private String content;
            private String type;
            private String departmentName;
            private String sponsor;
            private Object creator;
            private String createTime;
            private List<String> ccUsers;
            private List<?> pictures;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getSponsor() {
                return sponsor;
            }

            public void setSponsor(String sponsor) {
                this.sponsor = sponsor;
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

            public List<String> getCcUsers() {
                return ccUsers;
            }

            public void setCcUsers(List<String> ccUsers) {
                this.ccUsers = ccUsers;
            }

            public List<?> getPictures() {
                return pictures;
            }

            public void setPictures(List<?> pictures) {
                this.pictures = pictures;
            }
        }
    }
}
