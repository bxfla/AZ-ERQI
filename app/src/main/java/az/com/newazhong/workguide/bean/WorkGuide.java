package az.com.newazhong.workguide.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/3/24.
 */

public class WorkGuide implements Serializable{

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":1,"name":"社会保障","icon":"0","faceType":"MASSES","sort":1},{"id":2,"name":"医疗卫生","icon":"1","faceType":"MASSES","sort":2},{"id":3,"name":"劳动就业","icon":"1","faceType":"MASSES","sort":3},{"id":4,"name":"技能培训","icon":"1","faceType":"MASSES","sort":4},{"id":5,"name":"行政给付","icon":"1","faceType":"MASSES","sort":5},{"id":6,"name":"其他行政给付","icon":"1","faceType":"MASSES","sort":6}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":6,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":6,"size":10,"number":0}
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

    public static class DataBean {
        /**
         * content : [{"id":1,"name":"社会保障","icon":"0","faceType":"MASSES","sort":1},{"id":2,"name":"医疗卫生","icon":"1","faceType":"MASSES","sort":2},{"id":3,"name":"劳动就业","icon":"1","faceType":"MASSES","sort":3},{"id":4,"name":"技能培训","icon":"1","faceType":"MASSES","sort":4},{"id":5,"name":"行政给付","icon":"1","faceType":"MASSES","sort":5},{"id":6,"name":"其他行政给付","icon":"1","faceType":"MASSES","sort":6}]
         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
         * totalPages : 1
         * last : true
         * totalElements : 6
         * first : true
         * sort : {"unsorted":false,"sorted":true}
         * numberOfElements : 6
         * size : 10
         * number : 0
         */

        private PageableBean pageable;
        private int totalPages;
        private boolean last;
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

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
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

        public static class PageableBean {
            /**
             * sort : {"unsorted":false,"sorted":true}
             * pageSize : 10
             * pageNumber : 0
             * offset : 0
             * unpaged : false
             * paged : true
             */

            private SortBean sort;
            private int pageSize;
            private int pageNumber;
            private int offset;
            private boolean unpaged;
            private boolean paged;

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

            public static class SortBean {
                /**
                 * unsorted : false
                 * sorted : true
                 */

                private boolean unsorted;
                private boolean sorted;

                public boolean isUnsorted() {
                    return unsorted;
                }

                public void setUnsorted(boolean unsorted) {
                    this.unsorted = unsorted;
                }

                public boolean isSorted() {
                    return sorted;
                }

                public void setSorted(boolean sorted) {
                    this.sorted = sorted;
                }
            }
        }

        public static class ContentBean {
            /**
             * id : 1
             * name : 社会保障
             * icon : 0
             * faceType : MASSES
             * sort : 1
             */

            public String id;
            public String name;
            public String icon;
            public String faceType;
            public int sort;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getFaceType() {
                return faceType;
            }

            public void setFaceType(String faceType) {
                this.faceType = faceType;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
