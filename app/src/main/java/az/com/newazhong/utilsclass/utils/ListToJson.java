package az.com.newazhong.utilsclass.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import az.com.newazhong.workoffice.bean.UpData;

/**
 * Created by Administrator on 2018/11/7.
 */

public class ListToJson {
    public static JSONArray ProLogListJson(List<UpData.ApprovalItemsBean> list){
        JSONArray json = new JSONArray();
        for(UpData.ApprovalItemsBean pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("name", pLog.getName());
                jo.put("required", pLog.isRequired());
                jo.put("sort", pLog.getSort());
                jo.put("type", pLog.getType());
                jo.put("value", pLog.getValue());
                jo.put("mOptions", pLog.getMOptions());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

    public static JSONArray ProLogListJson1(List<UpData.ApprovalGroupBean> list){
        JSONArray json = new JSONArray();
        for(UpData.ApprovalGroupBean pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("adopt", true);
                jo.put("auditor", pLog.getAuditor());
                jo.put("memo", pLog.getMemo());
                jo.put("sort", pLog.getSort());
                jo.put("userId", pLog.getUserId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

    public static JSONArray ProLogListJson2(List<String> list){
        JSONArray json = new JSONArray();
        for(String pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("", pLog);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }
}
