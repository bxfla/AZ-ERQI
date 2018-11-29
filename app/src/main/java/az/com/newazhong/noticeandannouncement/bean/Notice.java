package az.com.newazhong.noticeandannouncement.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/3/27.
 */

public class Notice implements Serializable{

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":383,"article":{"title":"公告","content":"<h3>{\"result\":true,\"message\":null,\"data\":{\"content\":[],\"pageable\":{\"sort\":{\"unsorted\":false,\"sorted\":true},\"pageSize\":10,\"pageNumber\":3,\"offset\":30,\"unpaged\":false,\"paged\":true},\"totalPages\":1,\"last\":true,\"totalElements\":2,\"first\":false,\"sort\":{\"unsorted\":false,\"sorted\":true},\"numberOfElements\":0,\"size\":10,\"number\":3}}<\/h3>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:14:42","creator":"1","modifiedTime":"2018-08-26 03:14:42","modifier":null},{"id":380,"article":{"title":"通知","content":"<p>notice作名词,意为\"布告,公告,启事\",是可数名词.如:There is a notice on the office gate saying \"No Parking\".办公室门口上贴着一张\"禁止停车\"的告示.We have put a notice of renting these flats in the newspaper.我们已经在报上登了出租这几套房的启事<\/p>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:09:23","creator":"1","modifiedTime":"2018-08-26 03:09:23","modifier":null},{"id":377,"article":{"title":"通知","content":"这个是通知","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:04:43","creator":"1","modifiedTime":"2018-08-26 03:04:43","modifier":null},{"id":376,"article":{"title":"通知","content":"这个是通知","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:04:41","creator":"1","modifiedTime":"2018-08-26 03:04:41","modifier":null},{"id":354,"article":{"title":"11","content":"<p>222<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","attention":false,"type":"NOTICE","createTime":"2018-08-25 10:28:23","creator":"337","modifiedTime":"2018-08-25 10:28:43","modifier":"337"},{"id":287,"article":{"title":"string","content":"string","status":"UNSANCTIONED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:44:47","creator":null,"modifiedTime":"2018-08-23 15:04:45","modifier":"1"},{"id":286,"article":{"title":"1213213","content":"<p>312312312321<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:24:12","creator":null,"modifiedTime":"2018-08-23 15:04:41","modifier":"1"},{"id":285,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":5,"departmentName":"城管科","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:24:11","creator":null,"modifiedTime":"2018-08-23 15:04:48","modifier":"1"}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":8,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":8,"size":10,"number":0}
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
         * content : [{"id":383,"article":{"title":"公告","content":"<h3>{\"result\":true,\"message\":null,\"data\":{\"content\":[],\"pageable\":{\"sort\":{\"unsorted\":false,\"sorted\":true},\"pageSize\":10,\"pageNumber\":3,\"offset\":30,\"unpaged\":false,\"paged\":true},\"totalPages\":1,\"last\":true,\"totalElements\":2,\"first\":false,\"sort\":{\"unsorted\":false,\"sorted\":true},\"numberOfElements\":0,\"size\":10,\"number\":3}}<\/h3>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:14:42","creator":"1","modifiedTime":"2018-08-26 03:14:42","modifier":null},{"id":380,"article":{"title":"通知","content":"<p>notice作名词,意为\"布告,公告,启事\",是可数名词.如:There is a notice on the office gate saying \"No Parking\".办公室门口上贴着一张\"禁止停车\"的告示.We have put a notice of renting these flats in the newspaper.我们已经在报上登了出租这几套房的启事<\/p>","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:09:23","creator":"1","modifiedTime":"2018-08-26 03:09:23","modifier":null},{"id":377,"article":{"title":"通知","content":"这个是通知","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:04:43","creator":"1","modifiedTime":"2018-08-26 03:04:43","modifier":null},{"id":376,"article":{"title":"通知","content":"这个是通知","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":false,"type":"NOTICE","createTime":"2018-08-26 03:04:41","creator":"1","modifiedTime":"2018-08-26 03:04:41","modifier":null},{"id":354,"article":{"title":"11","content":"<p>222<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","attention":false,"type":"NOTICE","createTime":"2018-08-25 10:28:23","creator":"337","modifiedTime":"2018-08-25 10:28:43","modifier":"337"},{"id":287,"article":{"title":"string","content":"string","status":"UNSANCTIONED"},"departmentId":4,"departmentName":"张庄路街道办事处","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:44:47","creator":null,"modifiedTime":"2018-08-23 15:04:45","modifier":"1"},{"id":286,"article":{"title":"1213213","content":"<p>312312312321<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:24:12","creator":null,"modifiedTime":"2018-08-23 15:04:41","modifier":"1"},{"id":285,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":5,"departmentName":"城管科","attention":true,"type":"NOTICE","createTime":"2018-08-21 16:24:11","creator":null,"modifiedTime":"2018-08-23 15:04:48","modifier":"1"}]
         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
         * totalPages : 1
         * last : true
         * totalElements : 8
         * first : true
         * sort : {"unsorted":false,"sorted":true}
         * numberOfElements : 8
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
             * id : 383
             * article : {"title":"公告","content":"<h3>{\"result\":true,\"message\":null,\"data\":{\"content\":[],\"pageable\":{\"sort\":{\"unsorted\":false,\"sorted\":true},\"pageSize\":10,\"pageNumber\":3,\"offset\":30,\"unpaged\":false,\"paged\":true},\"totalPages\":1,\"last\":true,\"totalElements\":2,\"first\":false,\"sort\":{\"unsorted\":false,\"sorted\":true},\"numberOfElements\":0,\"size\":10,\"number\":3}}<\/h3>","status":"UNAUDITED"}
             * departmentId : 4
             * departmentName : 张庄路街道办事处
             * attention : false
             * type : NOTICE
             * createTime : 2018-08-26 03:14:42
             * creator : 1
             * modifiedTime : 2018-08-26 03:14:42
             * modifier : null
             */

            private int id;
            private ArticleBean article;
            private int departmentId;
            private String departmentName;
            private boolean attention;
            private String type;
            private String createTime;
            private String creator;
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

            public boolean isAttention() {
                return attention;
            }

            public void setAttention(boolean attention) {
                this.attention = attention;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public static class ArticleBean {
                /**
                 * title : 公告
                 * content : <h3>{"result":true,"message":null,"data":{"content":[],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":3,"offset":30,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":2,"first":false,"sort":{"unsorted":false,"sorted":true},"numberOfElements":0,"size":10,"number":3}}</h3>
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
        }
    }
}
