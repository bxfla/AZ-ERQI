package az.com.newazhong.utilsclass.utils;

import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;
import az.com.newazhong.utilsclass.base.Constant;

/**
 * Created by Administrator on 2018/10/26.
 */

public class MyHttpURLConnection {

    public static String getData(String requestUrl, String session, BaseRequestBackTLisenter baseRequestBackLisenter) {
        BufferedReader responseReader = null;
        HttpURLConnection connection = null;
        try {
            //建立连接
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.addRequestProperty("Cookie", session);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (200 == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    buffer.append(readLine).append("\n");
                }
                responseReader.close();
                baseRequestBackLisenter.success(buffer.toString());
                Log.d("HttpGET", buffer.toString());
                //JSONObject result = new JSONObject(buffer.toString());
                return buffer.toString();
            } else if (401 == responseCode) {
                baseRequestBackLisenter.failF("获取数据失败");
            } else {
                InputStream inputStream = null;
                inputStream = new BufferedInputStream(connection.getErrorStream());
                BufferedReader responseReader1;
//                                    //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                                    //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                int count = 1024;
                int result = -1;
                char[] readChars = new char[count];
                String temp = null;
                do {
                    result = responseReader.read(readChars, 0, count);
                    if (result > 0) {
                        temp = new String(readChars, 0, result);
                        buffer.append(temp);
                    }

                } while (result != -1);
                responseReader.close();
                Log.d("HttpPOST", buffer.toString());
                JSONObject jsonObjectC = new JSONObject(buffer.toString());
                String messageT = jsonObjectC.getString("message")+"";
                Log.d("HttpPOST", messageT);
                Message message = new Message();
                message.what = Constant.TAG_FOUR;
                baseRequestBackLisenter.fail("faile");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseRequestBackLisenter.fail("faile");
        } finally {
            if (responseReader!=null){
                try {
                    responseReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                connection.disconnect();
            }
        }
        return "";
    }



    public static String postData(String requestUrl, String session,String data, BaseRequestBackTLisenter baseRequestBackLisenter) {
        BufferedReader responseReader = null;
        HttpURLConnection connection = null;
        try {
            //建立连接
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.addRequestProperty("Cookie", session);
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.write(data.getBytes());
            dos.flush();
            dos.close();

            //获取响应状态
            int responseCode = connection.getResponseCode();
            InputStream inputStream = null;
            if (201 == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    buffer.append(readLine).append("\n");
                }
                responseReader.close();
                baseRequestBackLisenter.success(buffer.toString());
                Log.d("HttpGET", buffer.toString());
                //JSONObject result = new JSONObject(buffer.toString());
                return buffer.toString();
            } else if (401 == responseCode) {
                baseRequestBackLisenter.failF("获取数据失败");
            } else {
                baseRequestBackLisenter.fail("faile");
                inputStream = new BufferedInputStream(connection.getErrorStream());
//                                    //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                                    //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                int count = 1024;
                int result = -1;
                char[] readChars = new char[count];
                String temp = null;
                do {
                    result = responseReader.read(readChars, 0, count);
                    if (result > 0) {
                        temp = new String(readChars, 0, result);
                        buffer.append(temp);
                    }

                } while (result != -1);
                responseReader.close();
                Log.d("HttpPOST", buffer.toString());
                JSONObject jsonObjectC = new JSONObject(buffer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (responseReader!=null){
                try {
                    responseReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                connection.disconnect();
            }
        }
        return "";
    }

    public static String doPost(String url,String session, Map<String,String> map,BaseRequestBackTLisenter baseRequestBackLisenter){
        HttpPost httpPost = null;
        String result = null;
        try{
            HttpClient httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            // 构造消息头
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Cookie", session);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            InputStream inputStream = null;
            if (200 == statusCode) { //连接成功
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            } else if (401 == statusCode) {
                baseRequestBackLisenter.failF("获取数据失败");
            } else {
                baseRequestBackLisenter.fail("faile");
            }
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
