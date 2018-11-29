package az.com.newazhong.propagandwindow.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/4/4.
 */

public class ActivityPhoto implements Serializable {


    /**
     * result : true
     * message : null
     * data : {"content":[{"id":316,"article":{"title":"1111","content":null,"status":"UNAUDITED"},"departmentId":5,"departmentName":"城管科","picture":[{"name":"258c085ddde94474aca8213ea211834e-4.jpg","picture":"http://192.168.1.201//upload/258c085ddde94474aca8213ea211834e-4.jpg","sort":1},{"name":"picture-1.jpg","picture":"http://192.168.1.62/\\carousel\\picture-1.jpg","sort":1}],"createTime":"2018-08-23 15:59:27","creator":"1","modifiedTime":"2018-08-25 10:26:49","modifier":"1"},{"id":272,"article":{"title":"测试活动照片上传1111-编辑","content":"<p>测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111<\/p>","status":"UNAUDITED"},"departmentId":40,"departmentName":"安监中队","picture":[{"name":"head - 副本 - 副本 (2).jpg","picture":"http://192.168.1.201/\\pic\\carousel\\head.jpg","sort":1},{"name":"card - 副本.jpg","picture":"http://192.168.1.201/\\pic\\carousel\\card.jpg","sort":1},{"name":"head - 副本 (2).jpg","picture":"http://192.168.1.201/\\pic\\carousel\\head.jpg","sort":1}],"createTime":"2018-08-20 11:18:23","creator":null,"modifiedTime":"2018-08-23 09:19:58","modifier":"1"}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":100,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":4,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":4,"size":100,"number":0}
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
         * content : [{"id":316,"article":{"title":"1111","content":null,"status":"UNAUDITED"},"departmentId":5,"departmentName":"城管科","picture":[{"name":"258c085ddde94474aca8213ea211834e-4.jpg","picture":"http://192.168.1.201//upload/258c085ddde94474aca8213ea211834e-4.jpg","sort":1},{"name":"picture-1.jpg","picture":"http://192.168.1.62/\\carousel\\picture-1.jpg","sort":1}],"createTime":"2018-08-23 15:59:27","creator":"1","modifiedTime":"2018-08-25 10:26:49","modifier":"1"},{"id":272,"article":{"title":"测试活动照片上传1111-编辑","content":"<p>测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111测试活动照片上传1111<\/p>","status":"UNAUDITED"},"departmentId":40,"departmentName":"安监中队","picture":[{"name":"head - 副本 - 副本 (2).jpg","picture":"http://192.168.1.201/\\pic\\carousel\\head.jpg","sort":1},{"name":"card - 副本.jpg","picture":"http://192.168.1.201/\\pic\\carousel\\card.jpg","sort":1},{"name":"head - 副本 (2).jpg","picture":"http://192.168.1.201/\\pic\\carousel\\head.jpg","sort":1}],"createTime":"2018-08-20 11:18:23","creator":null,"modifiedTime":"2018-08-23 09:19:58","modifier":"1"}]
         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":100,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
         * totalPages : 1
         * last : true
         * totalElements : 4
         * first : true
         * sort : {"unsorted":false,"sorted":true}
         * numberOfElements : 4
         * size : 100
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
             * pageSize : 100
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
             * id : 316
             * article : {"title":"1111","content":null,"status":"UNAUDITED"}
             * departmentId : 5
             * departmentName : 城管科
             * picture : [{"name":"258c085ddde94474aca8213ea211834e-4.jpg","picture":"http://192.168.1.201//upload/258c085ddde94474aca8213ea211834e-4.jpg","sort":1},{"name":"picture-1.jpg","picture":"http://192.168.1.62/\\carousel\\picture-1.jpg","sort":1}]
             * createTime : 2018-08-23 15:59:27
             * creator : 1
             * modifiedTime : 2018-08-25 10:26:49
             * modifier : 1
             */

            private int id;
            private ArticleBean article;
            private int departmentId;
            private String departmentName;
            private String createTime;
            private String creator;
            private String modifiedTime;
            private String modifier;
            private List<PictureBean> picture;

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

            public String getModifier() {
                return modifier;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public List<PictureBean> getPicture() {
                return picture;
            }

            public void setPicture(List<PictureBean> picture) {
                this.picture = picture;
            }

            public static class ArticleBean {
                /**
                 * title : 1111
                 * content : null
                 * status : UNAUDITED
                 */

                private String title;
                private Object content;
                private String status;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getContent() {
                    return content;
                }

                public void setContent(Object content) {
                    this.content = content;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }

            public static class PictureBean {
                /**
                 * name : 258c085ddde94474aca8213ea211834e-4.jpg
                 * picture : http://192.168.1.201//upload/258c085ddde94474aca8213ea211834e-4.jpg
                 * sort : 1
                 */

                private String name;
                private String picture;
                private int sort;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
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
}
