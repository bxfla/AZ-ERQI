package az.com.newazhong.street12345.activity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/8/27.
 */

public class Street implements Serializable{


    /**
     * result : true
     * message : null
     * data : {"content":[{"id":993,"sponsorId":965,"sponsorName":"胡肖峰","address":"济南","gpsAddress":"山东省济南市历下区华阳路73号","description":"测试事件4","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:55:53","creator":"965","modifiedTime":"2018-09-11 14:55:53","modifier":null},{"id":987,"sponsorId":965,"sponsorName":"胡肖峰","address":"测试地点","gpsAddress":"山东省济南市历下区华阳路67-1号","description":"测试事件3","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:40:01","creator":"965","modifiedTime":"2018-09-11 14:40:01","modifier":null},{"id":986,"sponsorId":965,"sponsorName":"胡肖峰","address":"测试地点","gpsAddress":"山东省济南市历下区华阳路67-1号","description":"测试事件1","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:39:19","creator":"965","modifiedTime":"2018-09-11 14:39:19","modifier":null},{"id":982,"sponsorId":965,"sponsorName":"胡肖峰","address":"济南","gpsAddress":"山东省济南市历下区华阳路73号","description":"测试事件","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:34:56","creator":"965","modifiedTime":"2018-09-11 14:34:56","modifier":null}],"pageable":{"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false},"totalElements":4,"last":true,"totalPages":1,"number":0,"size":10,"sort":{"sorted":true,"unsorted":false},"first":true,"numberOfElements":4}
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
         * content : [{"id":993,"sponsorId":965,"sponsorName":"胡肖峰","address":"济南","gpsAddress":"山东省济南市历下区华阳路73号","description":"测试事件4","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:55:53","creator":"965","modifiedTime":"2018-09-11 14:55:53","modifier":null},{"id":987,"sponsorId":965,"sponsorName":"胡肖峰","address":"测试地点","gpsAddress":"山东省济南市历下区华阳路67-1号","description":"测试事件3","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:40:01","creator":"965","modifiedTime":"2018-09-11 14:40:01","modifier":null},{"id":986,"sponsorId":965,"sponsorName":"胡肖峰","address":"测试地点","gpsAddress":"山东省济南市历下区华阳路67-1号","description":"测试事件1","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:39:19","creator":"965","modifiedTime":"2018-09-11 14:39:19","modifier":null},{"id":982,"sponsorId":965,"sponsorName":"胡肖峰","address":"济南","gpsAddress":"山东省济南市历下区华阳路73号","description":"测试事件","pictures":["http://113.128.68.106:9000/pic/carousel/avator.png"],"matterState":"UNACCEPTED","allotDepartmentId":null,"allotDepartmentName":null,"departmentId":null,"departmentName":null,"estimate":null,"memo":null,"circulations":null,"open":true,"createTime":"2018-09-11 14:34:56","creator":"965","modifiedTime":"2018-09-11 14:34:56","modifier":null}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false}
         * totalElements : 4
         * last : true
         * totalPages : 1
         * number : 0
         * size : 10
         * sort : {"sorted":true,"unsorted":false}
         * first : true
         * numberOfElements : 4
         */

        private PageableBean pageable;
        private int totalElements;
        private boolean last;
        private int totalPages;
        private int number;
        private int size;
        private SortBeanX sort;
        private boolean first;
        private int numberOfElements;
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
             * id : 993
             * sponsorId : 965
             * sponsorName : 胡肖峰
             * address : 济南
             * gpsAddress : 山东省济南市历下区华阳路73号
             * description : 测试事件4
             * pictures : ["http://113.128.68.106:9000/pic/carousel/avator.png"]
             * matterState : UNACCEPTED
             * allotDepartmentId : null
             * allotDepartmentName : null
             * departmentId : null
             * departmentName : null
             * estimate : null
             * memo : null
             * circulations : null
             * open : true
             * createTime : 2018-09-11 14:55:53
             * creator : 965
             * modifiedTime : 2018-09-11 14:55:53
             * modifier : null
             */

            private int id;
            private int sponsorId;
            private String sponsorName;
            private String address;
            private String gpsAddress;
            private String description;
            private String matterState;
            private Object allotDepartmentId;
            private Object allotDepartmentName;
            private Object departmentId;
            private Object departmentName;
            private Object estimate;
            private Object memo;
            private Object circulations;
            private boolean open;
            private String createTime;
            private String creator;
            private String modifiedTime;
            private Object modifier;
            private List<String> pictures;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSponsorId() {
                return sponsorId;
            }

            public void setSponsorId(int sponsorId) {
                this.sponsorId = sponsorId;
            }

            public String getSponsorName() {
                return sponsorName;
            }

            public void setSponsorName(String sponsorName) {
                this.sponsorName = sponsorName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getGpsAddress() {
                return gpsAddress;
            }

            public void setGpsAddress(String gpsAddress) {
                this.gpsAddress = gpsAddress;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getMatterState() {
                return matterState;
            }

            public void setMatterState(String matterState) {
                this.matterState = matterState;
            }

            public Object getAllotDepartmentId() {
                return allotDepartmentId;
            }

            public void setAllotDepartmentId(Object allotDepartmentId) {
                this.allotDepartmentId = allotDepartmentId;
            }

            public Object getAllotDepartmentName() {
                return allotDepartmentName;
            }

            public void setAllotDepartmentName(Object allotDepartmentName) {
                this.allotDepartmentName = allotDepartmentName;
            }

            public Object getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(Object departmentId) {
                this.departmentId = departmentId;
            }

            public Object getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(Object departmentName) {
                this.departmentName = departmentName;
            }

            public Object getEstimate() {
                return estimate;
            }

            public void setEstimate(Object estimate) {
                this.estimate = estimate;
            }

            public Object getMemo() {
                return memo;
            }

            public void setMemo(Object memo) {
                this.memo = memo;
            }

            public Object getCirculations() {
                return circulations;
            }

            public void setCirculations(Object circulations) {
                this.circulations = circulations;
            }

            public boolean isOpen() {
                return open;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public String getModifiedTime() {
                return modifiedTime;
            }

            public void setModifiedTime(String modifiedTime) {
                this.modifiedTime = modifiedTime;
            }

            public Object getModifier() {
                return modifier;
            }

            public void setModifier(Object modifier) {
                this.modifier = modifier;
            }

            public List<String> getPictures() {
                return pictures;
            }

            public void setPictures(List<String> pictures) {
                this.pictures = pictures;
            }
        }
    }
}
