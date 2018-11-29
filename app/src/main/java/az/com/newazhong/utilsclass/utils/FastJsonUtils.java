package az.com.newazhong.utilsclass.utils;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FastJsonUtils {
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {

        }
        return t;
    }

    public static<T> List<T> getArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
        }
        return list;
    }
    public static JSONObject getJSON(String jsonString){
        JSONObject jsonObject;
        try {
             jsonObject=new JSONObject(jsonString);
        }catch (Exception e){
            jsonObject=new JSONObject();
        }
        return jsonObject;
    }
}
