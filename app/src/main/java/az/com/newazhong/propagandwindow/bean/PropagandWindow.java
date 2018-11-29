package az.com.newazhong.propagandwindow.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/3/30.
 */

public class PropagandWindow implements Serializable{
    /**
     * result : true
     * message : null
     * data : {"content":[{"id":352,"article":{"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-25 10:24:20","creator":"2","modifiedTime":"2018-08-25 10:26:32","modifier":"337"},{"id":267,"article":{"title":"string1","content":"string","status":"UNAUDITED"},"departmentId":38,"departmentName":"残疾人联合会","news":false,"createTime":"2018-08-20 10:16:51","creator":null,"modifiedTime":"2018-08-23 14:32:27","modifier":"1"},{"id":261,"article":{"title":"【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导","content":"<h2 style=\"text-align:center;\"><span class=\"text-huge\">【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导<\/span><\/h2><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111306.png\"><\/figure><h3 style=\"text-align:justify;\">按照区扫黑除恶办公室统一安排，区检察院刘相博同志，区公安分局张琳同志来我办检查督导扫黑除恶宣传落实情况。会上，张庄路街道办事处副主任牛家刚同志对扫黑除恶宣传工作进行了汇报。刘相博同志对扫黑除恶宣传工作进行了明确的指导，并强调要进一步加大扫黑除恶宣传力度。<\/h3><p>&nbsp;<\/p><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111328.png\"><\/figure><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111346.png\"><\/figure><h3 style=\"text-align:justify;\">会后，刘相博、张琳同志对景秀荣祥居委会和世纪中华城居委会入户宣传工作进行了实地督导，并随机抽查群众知晓率情况。<\/h3><h3 style=\"text-align:justify;\">督导组对张庄路街道\u201c扫黑除恶专项斗争\u201d宣传工作落实情况表示充分肯下一步办事处将对\u201c扫黑除恶\u201d专项斗争进行广泛宣传。让辖区内企业、单位及群众充分认识到\u201c扫黑除恶\u201d专项行动的决心和信心，提高参与\u201c扫黑除恶\u201d的意识，为\u201c扫黑除恶\u201d专项斗争创造良好的氛围。<\/h3><p>&nbsp;<\/p>","status":"UNSANCTIONED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-20 10:08:37","creator":null,"modifiedTime":"2018-08-25 13:48:08","modifier":"1"},{"id":260,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","news":false,"createTime":"2018-08-20 09:57:46","creator":null,"modifiedTime":"2018-08-23 11:13:16","modifier":"1"}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":100,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":4,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":4,"size":100,"number":0}
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
         * content : [{"id":352,"article":{"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-25 10:24:20","creator":"2","modifiedTime":"2018-08-25 10:26:32","modifier":"337"},{"id":267,"article":{"title":"string1","content":"string","status":"UNAUDITED"},"departmentId":38,"departmentName":"残疾人联合会","news":false,"createTime":"2018-08-20 10:16:51","creator":null,"modifiedTime":"2018-08-23 14:32:27","modifier":"1"},{"id":261,"article":{"title":"【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导","content":"<h2 style=\"text-align:center;\"><span class=\"text-huge\">【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导<\/span><\/h2><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111306.png\"><\/figure><h3 style=\"text-align:justify;\">按照区扫黑除恶办公室统一安排，区检察院刘相博同志，区公安分局张琳同志来我办检查督导扫黑除恶宣传落实情况。会上，张庄路街道办事处副主任牛家刚同志对扫黑除恶宣传工作进行了汇报。刘相博同志对扫黑除恶宣传工作进行了明确的指导，并强调要进一步加大扫黑除恶宣传力度。<\/h3><p>&nbsp;<\/p><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111328.png\"><\/figure><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111346.png\"><\/figure><h3 style=\"text-align:justify;\">会后，刘相博、张琳同志对景秀荣祥居委会和世纪中华城居委会入户宣传工作进行了实地督导，并随机抽查群众知晓率情况。<\/h3><h3 style=\"text-align:justify;\">督导组对张庄路街道\u201c扫黑除恶专项斗争\u201d宣传工作落实情况表示充分肯下一步办事处将对\u201c扫黑除恶\u201d专项斗争进行广泛宣传。让辖区内企业、单位及群众充分认识到\u201c扫黑除恶\u201d专项行动的决心和信心，提高参与\u201c扫黑除恶\u201d的意识，为\u201c扫黑除恶\u201d专项斗争创造良好的氛围。<\/h3><p>&nbsp;<\/p>","status":"UNSANCTIONED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-20 10:08:37","creator":null,"modifiedTime":"2018-08-25 13:48:08","modifier":"1"},{"id":260,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","news":false,"createTime":"2018-08-20 09:57:46","creator":null,"modifiedTime":"2018-08-23 11:13:16","modifier":"1"}]
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
             * id : 352
             * article : {"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"}
             * departmentId : 5
             * departmentName : 城管科
             * news : true
             * createTime : 2018-08-25 10:24:20
             * creator : 2
             * modifiedTime : 2018-08-25 10:26:32
             * modifier : 337
             */

            private int id;
            private ArticleBean article;
            private int departmentId;
            private String departmentName;
            private String news;
            private String createTime;
            private String creator;
            private String modifiedTime;
            private String modifier;

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

            public String getNews() {
                return news;
            }

            public void setNews(String news) {
                this.news = news;
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

            public static class ArticleBean {
                /**
                 * title : 333333333333333333333
                 * content : <p>undefined</p>
                 * status : APPROVED
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

//
//
//    /**
//     * result : true
//     * message : null
//     * data : {"content":[{"id":352,"article":{"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-25 10:24:20","creator":"2","modifiedTime":"2018-08-25 10:26:32","modifier":"337"},{"id":267,"article":{"title":"string1","content":"string","status":"UNAUDITED"},"departmentId":38,"departmentName":"残疾人联合会","news":false,"createTime":"2018-08-20 10:16:51","creator":null,"modifiedTime":"2018-08-23 14:32:27","modifier":"1"},{"id":261,"article":{"title":"【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导","content":"<h2 style=\"text-align:center;\"><span class=\"text-huge\">【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导<\/span><\/h2><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111306.png\"><\/figure><h3 style=\"text-align:justify;\">按照区扫黑除恶办公室统一安排，区检察院刘相博同志，区公安分局张琳同志来我办检查督导扫黑除恶宣传落实情况。会上，张庄路街道办事处副主任牛家刚同志对扫黑除恶宣传工作进行了汇报。刘相博同志对扫黑除恶宣传工作进行了明确的指导，并强调要进一步加大扫黑除恶宣传力度。<\/h3><p>&nbsp;<\/p><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111328.png\"><\/figure><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111346.png\"><\/figure><h3 style=\"text-align:justify;\">会后，刘相博、张琳同志对景秀荣祥居委会和世纪中华城居委会入户宣传工作进行了实地督导，并随机抽查群众知晓率情况。<\/h3><h3 style=\"text-align:justify;\">督导组对张庄路街道\u201c扫黑除恶专项斗争\u201d宣传工作落实情况表示充分肯下一步办事处将对\u201c扫黑除恶\u201d专项斗争进行广泛宣传。让辖区内企业、单位及群众充分认识到\u201c扫黑除恶\u201d专项行动的决心和信心，提高参与\u201c扫黑除恶\u201d的意识，为\u201c扫黑除恶\u201d专项斗争创造良好的氛围。<\/h3><p>&nbsp;<\/p>","status":"UNSANCTIONED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-20 10:08:37","creator":null,"modifiedTime":"2018-08-25 13:48:08","modifier":"1"},{"id":260,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","news":false,"createTime":"2018-08-20 09:57:46","creator":null,"modifiedTime":"2018-08-23 11:13:16","modifier":"1"}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":100,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":4,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":4,"size":100,"number":0}
//     */
//
//    private boolean result;
//    private Object message;
//    private DataBean data;
//
//    public static PropagandWindow objectFromData(String str) {
//
//        return new Gson().fromJson(str, PropagandWindow.class);
//    }
//
//    public static PropagandWindow objectFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//
//            return new Gson().fromJson(jsonObject.getString(str), PropagandWindow.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static List<PropagandWindow> arrayPropagandWindowFromData(String str) {
//
//        Type listType = new TypeToken<ArrayList<PropagandWindow>>() {
//        }.getType();
//
//        return new Gson().fromJson(str, listType);
//    }
//
//    public static List<PropagandWindow> arrayPropagandWindowFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//            Type listType = new TypeToken<ArrayList<PropagandWindow>>() {
//            }.getType();
//
//            return new Gson().fromJson(jsonObject.getString(str), listType);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return new ArrayList();
//
//
//    }
//
//    public boolean isResult() {
//        return result;
//    }
//
//    public void setResult(boolean result) {
//        this.result = result;
//    }
//
//    public Object getMessage() {
//        return message;
//    }
//
//    public void setMessage(Object message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * content : [{"id":352,"article":{"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-25 10:24:20","creator":"2","modifiedTime":"2018-08-25 10:26:32","modifier":"337"},{"id":267,"article":{"title":"string1","content":"string","status":"UNAUDITED"},"departmentId":38,"departmentName":"残疾人联合会","news":false,"createTime":"2018-08-20 10:16:51","creator":null,"modifiedTime":"2018-08-23 14:32:27","modifier":"1"},{"id":261,"article":{"title":"【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导","content":"<h2 style=\"text-align:center;\"><span class=\"text-huge\">【扫黑除恶】区检查组对张庄路街道办事处扫黑除恶宣传工作进行督导<\/span><\/h2><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111306.png\"><\/figure><h3 style=\"text-align:justify;\">按照区扫黑除恶办公室统一安排，区检察院刘相博同志，区公安分局张琳同志来我办检查督导扫黑除恶宣传落实情况。会上，张庄路街道办事处副主任牛家刚同志对扫黑除恶宣传工作进行了汇报。刘相博同志对扫黑除恶宣传工作进行了明确的指导，并强调要进一步加大扫黑除恶宣传力度。<\/h3><p>&nbsp;<\/p><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111328.png\"><\/figure><figure class=\"image\"><img src=\"http://192.168.1.62\\pic\\carousel\\TIM图片20180825111346.png\"><\/figure><h3 style=\"text-align:justify;\">会后，刘相博、张琳同志对景秀荣祥居委会和世纪中华城居委会入户宣传工作进行了实地督导，并随机抽查群众知晓率情况。<\/h3><h3 style=\"text-align:justify;\">督导组对张庄路街道\u201c扫黑除恶专项斗争\u201d宣传工作落实情况表示充分肯下一步办事处将对\u201c扫黑除恶\u201d专项斗争进行广泛宣传。让辖区内企业、单位及群众充分认识到\u201c扫黑除恶\u201d专项行动的决心和信心，提高参与\u201c扫黑除恶\u201d的意识，为\u201c扫黑除恶\u201d专项斗争创造良好的氛围。<\/h3><p>&nbsp;<\/p>","status":"UNSANCTIONED"},"departmentId":5,"departmentName":"城管科","news":true,"createTime":"2018-08-20 10:08:37","creator":null,"modifiedTime":"2018-08-25 13:48:08","modifier":"1"},{"id":260,"article":{"title":"string","content":"string","status":"UNAUDITED"},"departmentId":4,"departmentName":"张庄路街道办事处","news":false,"createTime":"2018-08-20 09:57:46","creator":null,"modifiedTime":"2018-08-23 11:13:16","modifier":"1"}]
//         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":100,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
//         * totalPages : 1
//         * last : true
//         * totalElements : 4
//         * first : true
//         * sort : {"unsorted":false,"sorted":true}
//         * numberOfElements : 4
//         * size : 100
//         * number : 0
//         */
//
//        private PageableBean pageable;
//        private int totalPages;
//        private boolean last;
//        private int totalElements;
//        private boolean first;
//        private int numberOfElements;
//        private int size;
//        private int number;
//        private List<ContentBean> content;
//
//        public static DataBean objectFromData(String str) {
//
//            return new Gson().fromJson(str, DataBean.class);
//        }
//
//        public static DataBean objectFromData(String str, String key) {
//
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//
//                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        public static List<DataBean> arrayDataBeanFromData(String str) {
//
//            Type listType = new TypeToken<ArrayList<DataBean>>() {
//            }.getType();
//
//            return new Gson().fromJson(str, listType);
//        }
//
//        public static List<DataBean> arrayDataBeanFromData(String str, String key) {
//
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//                Type listType = new TypeToken<ArrayList<DataBean>>() {
//                }.getType();
//
//                return new Gson().fromJson(jsonObject.getString(str), listType);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return new ArrayList();
//
//
//        }
//
//        public PageableBean getPageable() {
//            return pageable;
//        }
//
//        public void setPageable(PageableBean pageable) {
//            this.pageable = pageable;
//        }
//
//        public int getTotalPages() {
//            return totalPages;
//        }
//
//        public void setTotalPages(int totalPages) {
//            this.totalPages = totalPages;
//        }
//
//        public boolean isLast() {
//            return last;
//        }
//
//        public void setLast(boolean last) {
//            this.last = last;
//        }
//
//        public int getTotalElements() {
//            return totalElements;
//        }
//
//        public void setTotalElements(int totalElements) {
//            this.totalElements = totalElements;
//        }
//
//        public boolean isFirst() {
//            return first;
//        }
//
//        public void setFirst(boolean first) {
//            this.first = first;
//        }
//
//        public int getNumberOfElements() {
//            return numberOfElements;
//        }
//
//        public void setNumberOfElements(int numberOfElements) {
//            this.numberOfElements = numberOfElements;
//        }
//
//        public int getSize() {
//            return size;
//        }
//
//        public void setSize(int size) {
//            this.size = size;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//
//        public List<ContentBean> getContent() {
//            return content;
//        }
//
//        public void setContent(List<ContentBean> content) {
//            this.content = content;
//        }
//
//        public static class PageableBean {
//            /**
//             * sort : {"unsorted":false,"sorted":true}
//             * pageSize : 100
//             * pageNumber : 0
//             * offset : 0
//             * unpaged : false
//             * paged : true
//             */
//
//            private SortBean sort;
//            private int pageSize;
//            private int pageNumber;
//            private int offset;
//            private boolean unpaged;
//            private boolean paged;
//
//            public static PageableBean objectFromData(String str) {
//
//                return new Gson().fromJson(str, PageableBean.class);
//            }
//
//            public static PageableBean objectFromData(String str, String key) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//
//                    return new Gson().fromJson(jsonObject.getString(str), PageableBean.class);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            public static List<PageableBean> arrayPageableBeanFromData(String str) {
//
//                Type listType = new TypeToken<ArrayList<PageableBean>>() {
//                }.getType();
//
//                return new Gson().fromJson(str, listType);
//            }
//
//            public static List<PageableBean> arrayPageableBeanFromData(String str, String key) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//                    Type listType = new TypeToken<ArrayList<PageableBean>>() {
//                    }.getType();
//
//                    return new Gson().fromJson(jsonObject.getString(str), listType);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return new ArrayList();
//
//
//            }
//
//            public SortBean getSort() {
//                return sort;
//            }
//
//            public void setSort(SortBean sort) {
//                this.sort = sort;
//            }
//
//            public int getPageSize() {
//                return pageSize;
//            }
//
//            public void setPageSize(int pageSize) {
//                this.pageSize = pageSize;
//            }
//
//            public int getPageNumber() {
//                return pageNumber;
//            }
//
//            public void setPageNumber(int pageNumber) {
//                this.pageNumber = pageNumber;
//            }
//
//            public int getOffset() {
//                return offset;
//            }
//
//            public void setOffset(int offset) {
//                this.offset = offset;
//            }
//
//            public boolean isUnpaged() {
//                return unpaged;
//            }
//
//            public void setUnpaged(boolean unpaged) {
//                this.unpaged = unpaged;
//            }
//
//            public boolean isPaged() {
//                return paged;
//            }
//
//            public void setPaged(boolean paged) {
//                this.paged = paged;
//            }
//
//            public static class SortBean {
//                /**
//                 * unsorted : false
//                 * sorted : true
//                 */
//
//                private boolean unsorted;
//                private boolean sorted;
//
//                public static SortBean objectFromData(String str) {
//
//                    return new Gson().fromJson(str, SortBean.class);
//                }
//
//                public static SortBean objectFromData(String str, String key) {
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(str);
//
//                        return new Gson().fromJson(jsonObject.getString(str), SortBean.class);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    return null;
//                }
//
//                public static List<SortBean> arraySortBeanFromData(String str) {
//
//                    Type listType = new TypeToken<ArrayList<SortBean>>() {
//                    }.getType();
//
//                    return new Gson().fromJson(str, listType);
//                }
//
//                public static List<SortBean> arraySortBeanFromData(String str, String key) {
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(str);
//                        Type listType = new TypeToken<ArrayList<SortBean>>() {
//                        }.getType();
//
//                        return new Gson().fromJson(jsonObject.getString(str), listType);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    return new ArrayList();
//
//
//                }
//
//                public boolean isUnsorted() {
//                    return unsorted;
//                }
//
//                public void setUnsorted(boolean unsorted) {
//                    this.unsorted = unsorted;
//                }
//
//                public boolean isSorted() {
//                    return sorted;
//                }
//
//                public void setSorted(boolean sorted) {
//                    this.sorted = sorted;
//                }
//            }
//        }
//
//        public static class ContentBean {
//            /**
//             * id : 352
//             * article : {"title":"333333333333333333333","content":"<p>undefined<\/p>","status":"APPROVED"}
//             * departmentId : 5
//             * departmentName : 城管科
//             * news : true
//             * createTime : 2018-08-25 10:24:20
//             * creator : 2
//             * modifiedTime : 2018-08-25 10:26:32
//             * modifier : 337
//             */
//
//            private int id;
//            private ArticleBean article;
//            private int departmentId;
//            private String departmentName;
//            private String news;
//            private String createTime;
//            private String creator;
//            private String modifiedTime;
//            private String modifier;
//
//            public static ContentBean objectFromData(String str) {
//
//                return new Gson().fromJson(str, ContentBean.class);
//            }
//
//            public static ContentBean objectFromData(String str, String key) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//
//                    return new Gson().fromJson(jsonObject.getString(str), ContentBean.class);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            public static List<ContentBean> arrayContentBeanFromData(String str) {
//
//                Type listType = new TypeToken<ArrayList<ContentBean>>() {
//                }.getType();
//
//                return new Gson().fromJson(str, listType);
//            }
//
//            public static List<ContentBean> arrayContentBeanFromData(String str, String key) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//                    Type listType = new TypeToken<ArrayList<ContentBean>>() {
//                    }.getType();
//
//                    return new Gson().fromJson(jsonObject.getString(str), listType);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return new ArrayList();
//
//
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public ArticleBean getArticle() {
//                return article;
//            }
//
//            public void setArticle(ArticleBean article) {
//                this.article = article;
//            }
//
//            public int getDepartmentId() {
//                return departmentId;
//            }
//
//            public void setDepartmentId(int departmentId) {
//                this.departmentId = departmentId;
//            }
//
//            public String getDepartmentName() {
//                return departmentName;
//            }
//
//            public void setDepartmentName(String departmentName) {
//                this.departmentName = departmentName;
//            }
//
//            public String getNews() {
//                return news;
//            }
//
//            public void setNews(String news) {
//                this.news = news;
//            }
//
//            public String getCreateTime() {
//                return createTime;
//            }
//
//            public void setCreateTime(String createTime) {
//                this.createTime = createTime;
//            }
//
//            public String getCreator() {
//                return creator;
//            }
//
//            public void setCreator(String creator) {
//                this.creator = creator;
//            }
//
//            public String getModifiedTime() {
//                return modifiedTime;
//            }
//
//            public void setModifiedTime(String modifiedTime) {
//                this.modifiedTime = modifiedTime;
//            }
//
//            public String getModifier() {
//                return modifier;
//            }
//
//            public void setModifier(String modifier) {
//                this.modifier = modifier;
//            }
//
//            public static class ArticleBean {
//                /**
//                 * title : 333333333333333333333
//                 * content : <p>undefined</p>
//                 * status : APPROVED
//                 */
//
//                private String title;
//                private String content;
//                private String status;
//
//                public static ArticleBean objectFromData(String str) {
//
//                    return new Gson().fromJson(str, ArticleBean.class);
//                }
//
//                public static ArticleBean objectFromData(String str, String key) {
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(str);
//
//                        return new Gson().fromJson(jsonObject.getString(str), ArticleBean.class);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    return null;
//                }
//
//                public static List<ArticleBean> arrayArticleBeanFromData(String str) {
//
//                    Type listType = new TypeToken<ArrayList<ArticleBean>>() {
//                    }.getType();
//
//                    return new Gson().fromJson(str, listType);
//                }
//
//                public static List<ArticleBean> arrayArticleBeanFromData(String str, String key) {
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(str);
//                        Type listType = new TypeToken<ArrayList<ArticleBean>>() {
//                        }.getType();
//
//                        return new Gson().fromJson(jsonObject.getString(str), listType);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    return new ArrayList();
//
//
//                }
//
//                public String getTitle() {
//                    return title;
//                }
//
//                public void setTitle(String title) {
//                    this.title = title;
//                }
//
//                public String getContent() {
//                    return content;
//                }
//
//                public void setContent(String content) {
//                    this.content = content;
//                }
//
//                public String getStatus() {
//                    return status;
//                }
//
//                public void setStatus(String status) {
//                    this.status = status;
//                }
//            }
//        }
//    }

}
