package az.com.newazhong.utilsclass.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/3/22.
 */

public class Banner implements Serializable{

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":"359","name":"222","url":"http://192.168.1.62\\pic\\carousel\\762b9a55-45f6-4000-9eea-48e70c253a4c.jpg","sort":4,"createTime":"2018-08-25 10:37:25","creator":null},{"id":"350","name":"1111","url":"http://192.168.1.62\\pic\\carousel\\a9a54415-b245-410c-a302-cf5c823163e0.jpg","sort":2,"createTime":"2018-08-25 10:23:39","creator":null},{"id":"349","name":"zz","url":"http://192.168.1.62\\pic\\carousel\\dbafe090-0f06-4d91-af54-ec01da7db914.jpg","sort":1,"createTime":"2018-08-25 10:23:12","creator":null},{"id":"344","name":"测试","url":"http://192.168.1.62\\pic\\carousel\\c08b35c7-4b98-4a67-8796-6d03305e0516.jpg","sort":1,"createTime":"2018-08-25 10:22:17","creator":null}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":4,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":4,"size":10,"number":0}
     */

    private boolean result;
    private Object message;
    private DataBean data;

    public static Banner objectFromData(String str) {

        return new Gson().fromJson(str, Banner.class);
    }

    public static Banner objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Banner.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Banner> arrayBannerFromData(String str) {

        Type listType = new TypeToken<ArrayList<Banner>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Banner> arrayBannerFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Banner>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

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
         * content : [{"id":"359","name":"222","url":"http://192.168.1.62\\pic\\carousel\\762b9a55-45f6-4000-9eea-48e70c253a4c.jpg","sort":4,"createTime":"2018-08-25 10:37:25","creator":null},{"id":"350","name":"1111","url":"http://192.168.1.62\\pic\\carousel\\a9a54415-b245-410c-a302-cf5c823163e0.jpg","sort":2,"createTime":"2018-08-25 10:23:39","creator":null},{"id":"349","name":"zz","url":"http://192.168.1.62\\pic\\carousel\\dbafe090-0f06-4d91-af54-ec01da7db914.jpg","sort":1,"createTime":"2018-08-25 10:23:12","creator":null},{"id":"344","name":"测试","url":"http://192.168.1.62\\pic\\carousel\\c08b35c7-4b98-4a67-8796-6d03305e0516.jpg","sort":1,"createTime":"2018-08-25 10:22:17","creator":null}]
         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
         * totalPages : 1
         * last : true
         * totalElements : 4
         * first : true
         * sort : {"unsorted":false,"sorted":true}
         * numberOfElements : 4
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

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

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

            public static PageableBean objectFromData(String str) {

                return new Gson().fromJson(str, PageableBean.class);
            }

            public static PageableBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), PageableBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<PageableBean> arrayPageableBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<PageableBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<PageableBean> arrayPageableBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<PageableBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

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

                public static SortBean objectFromData(String str) {

                    return new Gson().fromJson(str, SortBean.class);
                }

                public static SortBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), SortBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<SortBean> arraySortBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<SortBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<SortBean> arraySortBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<SortBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

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
             * id : 359
             * name : 222
             * url : http://192.168.1.62\pic\carousel\762b9a55-45f6-4000-9eea-48e70c253a4c.jpg
             * sort : 4
             * createTime : 2018-08-25 10:37:25
             */

            private String id;
            private String name;
            private String url;
            private int sort;
            private String createTime;

            public static ContentBean objectFromData(String str) {

                return new Gson().fromJson(str, ContentBean.class);
            }

            public static ContentBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ContentBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ContentBean> arrayContentBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ContentBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ContentBean> arrayContentBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ContentBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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
