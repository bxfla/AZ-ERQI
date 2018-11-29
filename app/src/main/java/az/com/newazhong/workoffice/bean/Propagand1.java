package az.com.newazhong.workoffice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/3.
 */

public class Propagand1 implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":1858,"name":"22","content":"<p>ds<\/p>\n","annexs":[{"name":"1537845896.png","value":"http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png"}],"type":"DEPARTMENT_BUSINESS","creator":null,"createTime":"2018-10-31 17:14:48","modifiedTime":"2018-10-31 17:15:00","modifier":null},{"id":1857,"name":"d","content":"<p>d<\/p>\n","annexs":[],"type":"LEGISLATION","creator":null,"createTime":"2018-10-31 17:11:57","modifiedTime":"2018-10-31 17:11:57","modifier":null},{"id":1856,"name":"df","content":"<p>df<\/p>\n","annexs":[{"name":"1537845964.png","value":"http://112.230.201.22:9000null72d05f67-19fe-4ce7-b9b9-a592d983515b.png"}],"type":"PARTY_BUILDING","creator":null,"createTime":"2018-10-31 17:11:43","modifiedTime":"2018-10-31 17:11:43","modifier":null},{"id":1850,"name":"sd2","content":"<p>sdfs22<\/p>\n","annexs":[{"name":"1537845964.png","value":"http://112.230.201.22:9000null72d05f67-19fe-4ce7-b9b9-a592d983515b.png"},{"name":"1537845896.png","value":"http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png"}],"type":"LEGISLATION","creator":null,"createTime":"2018-10-31 16:14:33","modifiedTime":"2018-10-31 16:50:44","modifier":null}],"pageable":{"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false},"last":true,"totalElements":4,"totalPages":1,"number":0,"size":10,"sort":{"sorted":true,"unsorted":false},"numberOfElements":4,"first":true}
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
         * content : [{"id":1858,"name":"22","content":"<p>ds<\/p>\n","annexs":[{"name":"1537845896.png","value":"http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png"}],"type":"DEPARTMENT_BUSINESS","creator":null,"createTime":"2018-10-31 17:14:48","modifiedTime":"2018-10-31 17:15:00","modifier":null},{"id":1857,"name":"d","content":"<p>d<\/p>\n","annexs":[],"type":"LEGISLATION","creator":null,"createTime":"2018-10-31 17:11:57","modifiedTime":"2018-10-31 17:11:57","modifier":null},{"id":1856,"name":"df","content":"<p>df<\/p>\n","annexs":[{"name":"1537845964.png","value":"http://112.230.201.22:9000null72d05f67-19fe-4ce7-b9b9-a592d983515b.png"}],"type":"PARTY_BUILDING","creator":null,"createTime":"2018-10-31 17:11:43","modifiedTime":"2018-10-31 17:11:43","modifier":null},{"id":1850,"name":"sd2","content":"<p>sdfs22<\/p>\n","annexs":[{"name":"1537845964.png","value":"http://112.230.201.22:9000null72d05f67-19fe-4ce7-b9b9-a592d983515b.png"},{"name":"1537845896.png","value":"http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png"}],"type":"LEGISLATION","creator":null,"createTime":"2018-10-31 16:14:33","modifiedTime":"2018-10-31 16:50:44","modifier":null}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false}
         * last : true
         * totalElements : 4
         * totalPages : 1
         * number : 0
         * size : 10
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 4
         * first : true
         */

        private PageableBean pageable;
        private boolean last;
        private int totalElements;
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
             * id : 1858
             * name : 22
             * content : <p>ds</p>

             * annexs : [{"name":"1537845896.png","value":"http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png"}]
             * type : DEPARTMENT_BUSINESS
             * creator : null
             * createTime : 2018-10-31 17:14:48
             * modifiedTime : 2018-10-31 17:15:00
             * modifier : null
             */

            private int id;
            private String name;
            private String content;
            private String type;
            private Object creator;
            private String createTime;
            private String modifiedTime;
            private Object modifier;
            private List<AnnexsBean> annexs;

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

            public List<AnnexsBean> getAnnexs() {
                return annexs;
            }

            public void setAnnexs(List<AnnexsBean> annexs) {
                this.annexs = annexs;
            }

            public static class AnnexsBean implements Serializable {
                /**
                 * name : 1537845896.png
                 * value : http://112.230.201.22:9000null9d965473-e65b-4d23-b449-62d617df66cf.png
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
