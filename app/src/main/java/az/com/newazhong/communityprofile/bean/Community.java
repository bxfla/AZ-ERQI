package az.com.newazhong.communityprofile.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/3/23.
 */

public class Community implements Serializable {

    /**
     * result : true
     * message : null
     * data : {"content":[{"id":143,"name":"书记办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":29,"describes":"<p>书记办公室<\/p>","tag":["虚拟部门"],"open":false,"createTime":"2018-08-18 13:19:40","creator":null,"modifiedTime":"2018-08-18 13:19:54","modifier":null},{"id":142,"name":"主任办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":28,"describes":"<p>主任办公室<\/p>","tag":["虚拟部门"],"open":false,"createTime":"2018-08-18 11:35:45","creator":null,"modifiedTime":"2018-08-18 13:17:43","modifier":null},{"id":93,"name":"执法中队","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":24,"describes":"<p>执法中队<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:42:25","creator":null,"modifiedTime":"2018-08-16 16:42:25","modifier":null},{"id":91,"name":"政法科","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":23,"describes":"<p>政法科<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:42:09","creator":null,"modifiedTime":"2018-08-16 16:42:09","modifier":null},{"id":90,"name":"张庄村委会","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":22,"describes":"<p>张庄村委会<\/p>","tag":["村委会"],"open":true,"createTime":"2018-08-16 16:41:47","creator":null,"modifiedTime":"2018-08-16 16:41:47","modifier":null},{"id":89,"name":"行政办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":21,"describes":"<p>行政办公室<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:41:28","creator":null,"modifiedTime":"2018-08-16 16:41:28","modifier":null},{"id":88,"name":"信访办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":20,"describes":"<p>信访办公室<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:41:10","creator":null,"modifiedTime":"2018-08-16 16:41:10","modifier":null},{"id":87,"name":"武装部","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":19,"describes":"<p>武装部<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:40:51","creator":null,"modifiedTime":"2018-08-16 16:40:51","modifier":null},{"id":86,"name":"统计科","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":18,"describes":"<p>统计科<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:40:34","creator":null,"modifiedTime":"2018-08-16 16:40:34","modifier":null}],"pageable":{"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true},"totalPages":3,"last":false,"totalElements":30,"first":true,"sort":{"unsorted":false,"sorted":true},"numberOfElements":10,"size":10,"number":0}
     */

    private boolean result;
    private Object message;
    private DataBean data;

    public static Community objectFromData(String str) {

        return new Gson().fromJson(str, Community.class);
    }

    public static Community objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Community.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Community> arrayCommunityFromData(String str) {

        Type listType = new TypeToken<ArrayList<Community>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Community> arrayCommunityFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Community>>() {
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
         * content : [{"id":143,"name":"书记办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":29,"describes":"<p>书记办公室<\/p>","tag":["虚拟部门"],"open":false,"createTime":"2018-08-18 13:19:40","creator":null,"modifiedTime":"2018-08-18 13:19:54","modifier":null},{"id":142,"name":"主任办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":28,"describes":"<p>主任办公室<\/p>","tag":["虚拟部门"],"open":false,"createTime":"2018-08-18 11:35:45","creator":null,"modifiedTime":"2018-08-18 13:17:43","modifier":null},{"id":93,"name":"执法中队","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":24,"describes":"<p>执法中队<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:42:25","creator":null,"modifiedTime":"2018-08-16 16:42:25","modifier":null},{"id":91,"name":"政法科","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":23,"describes":"<p>政法科<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:42:09","creator":null,"modifiedTime":"2018-08-16 16:42:09","modifier":null},{"id":90,"name":"张庄村委会","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":22,"describes":"<p>张庄村委会<\/p>","tag":["村委会"],"open":true,"createTime":"2018-08-16 16:41:47","creator":null,"modifiedTime":"2018-08-16 16:41:47","modifier":null},{"id":89,"name":"行政办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":21,"describes":"<p>行政办公室<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:41:28","creator":null,"modifiedTime":"2018-08-16 16:41:28","modifier":null},{"id":88,"name":"信访办公室","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":20,"describes":"<p>信访办公室<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:41:10","creator":null,"modifiedTime":"2018-08-16 16:41:10","modifier":null},{"id":87,"name":"武装部","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":19,"describes":"<p>武装部<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:40:51","creator":null,"modifiedTime":"2018-08-16 16:40:51","modifier":null},{"id":86,"name":"统计科","parentId":null,"parent":{"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false},"children":[],"sort":18,"describes":"<p>统计科<\/p>","tag":["内设机构"],"open":true,"createTime":"2018-08-16 16:40:34","creator":null,"modifiedTime":"2018-08-16 16:40:34","modifier":null}]
         * pageable : {"sort":{"unsorted":false,"sorted":true},"pageSize":10,"pageNumber":0,"offset":0,"unpaged":false,"paged":true}
         * totalPages : 3
         * last : false
         * totalElements : 30
         * first : true
         * sort : {"unsorted":false,"sorted":true}
         * numberOfElements : 10
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

        public static class PageableBean implements Serializable{
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

            public static class SortBean implements Serializable{
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

        public static class ContentBean implements Serializable {
            /**
             * id : 143
             * name : 书记办公室
             * parentId : null
             * parent : {"id":4,"name":"张庄路街道办事处","parent":null,"sort":0,"describes":"<h2><br><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述，这是街道办描述这是街道办描述。<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>这是街道办描述这是街道办描述这是街道办描述这是<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>街道办描述，这是街道办描述。这是街道办描述？？<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>？这是街道办描述，这是街道办描述，这是街道办描述<\/strong><\/mark><\/span><\/h2><h2><span class=\"text-huge\"><mark class=\"pen-red\"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。<\/strong><\/mark><\/span><\/h2>","tag":["街道办"],"open":true,"createTime":"2018-08-13 10:51:46","creator":null,"modifiedTime":"2018-08-24 11:40:46","modifier":"1","new":false}
             * children : []
             * sort : 29
             * describes : <p>书记办公室</p>
             * tag : ["虚拟部门"]
             * open : false
             * createTime : 2018-08-18 13:19:40
             * creator : null
             * modifiedTime : 2018-08-18 13:19:54
             * modifier : null
             */

            private int id;
            private String name;
            private Object parentId;
            private ParentBean parent;
            private int sort;
            private String describes;
            private String open;
            private String createTime;
            private Object creator;
            private String modifiedTime;
            private Object modifier;
            private List<?> children;
            private List<String> tag;

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

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public ParentBean getParent() {
                return parent;
            }

            public void setParent(ParentBean parent) {
                this.parent = parent;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getDescribes() {
                return describes;
            }

            public void setDescribes(String describes) {
                this.describes = describes;
            }

            public String getOpen() {
                return open;
            }

            public void setOpen(String open) {
                this.open = open;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
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

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }

            public List<String> getTag() {
                return tag;
            }

            public void setTag(List<String> tag) {
                this.tag = tag;
            }

            public static class ParentBean implements Serializable{
                /**
                 * id : 4
                 * name : 张庄路街道办事处
                 * parent : null
                 * sort : 0
                 * describes : <h2><br><span class="text-huge"><mark class="pen-red"><strong>这是街道办描述，这是街道办描述这是街道办描述。</strong></mark></span></h2><h2><span class="text-huge"><mark class="pen-red"><strong>这是街道办描述这是街道办描述这是街道办描述这是</strong></mark></span></h2><h2><span class="text-huge"><mark class="pen-red"><strong>街道办描述，这是街道办描述。这是街道办描述？？</strong></mark></span></h2><h2><span class="text-huge"><mark class="pen-red"><strong>？这是街道办描述，这是街道办描述，这是街道办描述</strong></mark></span></h2><h2><span class="text-huge"><mark class="pen-red"><strong>，这是街道办描述，这是街道办描述，这是街道办描述。</strong></mark></span></h2>
                 * tag : ["街道办"]
                 * open : true
                 * createTime : 2018-08-13 10:51:46
                 * creator : null
                 * modifiedTime : 2018-08-24 11:40:46
                 * modifier : 1
                 * new : false
                 */

                private int id;
                private String name;
                private Object parent;
                private int sort;
                private String describes;
                private boolean open;
                private String createTime;
                private Object creator;
                private String modifiedTime;
                private String modifier;
                @JSONField(name = "new")
                private boolean newX;
                private List<String> tag;

                public static ParentBean objectFromData(String str) {

                    return new Gson().fromJson(str, ParentBean.class);
                }

                public static ParentBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ParentBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ParentBean> arrayParentBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ParentBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ParentBean> arrayParentBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ParentBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

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

                public Object getParent() {
                    return parent;
                }

                public void setParent(Object parent) {
                    this.parent = parent;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getDescribes() {
                    return describes;
                }

                public void setDescribes(String describes) {
                    this.describes = describes;
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

                public Object getCreator() {
                    return creator;
                }

                public void setCreator(Object creator) {
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

                public boolean isNewX() {
                    return newX;
                }

                public void setNewX(boolean newX) {
                    this.newX = newX;
                }

                public List<String> getTag() {
                    return tag;
                }

                public void setTag(List<String> tag) {
                    this.tag = tag;
                }
            }
        }
    }
}
