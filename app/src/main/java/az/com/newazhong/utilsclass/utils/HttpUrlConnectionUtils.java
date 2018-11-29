package az.com.newazhong.utilsclass.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import az.com.newazhong.utilsclass.base.BaseRequestBackLisenter;
import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;

/**
 * Created by dell on 2018/8/27.
 */

public class HttpUrlConnectionUtils {
    /**
     * 2 　　　* Post服务请求
     * 3      *
     * 4      * @param requestUrl 请求地址
     * 5      * @param requestbody 请求参数
     * 6      * @return
     * 7
     */
    public static String sendPost(String requestUrl, String requestbody,String session) {
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //设置连接属性
            connection.setDoOutput(true); //使用URL连接进行输出
            connection.setDoInput(true); //使用URL连接进行输入
            connection.setUseCaches(false); //忽略缓存
            connection.setRequestMethod("POST"); //设置URL请求方法
            String requestString = requestbody;

            //设置请求属性
            byte[] requestStringBytes = requestString.getBytes(); //获取数据字节数据
            //connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            connection.setRequestProperty("Charset", "UTF-8");
            connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

//            //建立输出流,并写入数据
//            OutputStream outputStream = connection.getOutputStream();
//            outputStream.write(requestStringBytes);
//            outputStream.close();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (403 == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    buffer.append(readLine).append("\n");
                }
                responseReader.close();
                Log.d("HttpPOST", buffer.toString());
                return buffer.toString();//成功
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";//失败

    }

    public static String sendPost1(String requestUrl, String requestbody) {
            String  param = "mobile="+requestbody;
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接属性
            connection.setDoOutput(true); //使用URL连接进行输出
            connection.setDoInput(true); //使用URL连接进行输入
            connection.setUseCaches(false); //忽略缓存
            connection.setRequestMethod("POST"); //设置URL请求方法
            String requestString = requestbody;

            //设置请求属性
            //connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
            connection.setRequestProperty("Content-Type", "text/xml; charset=GB2312");
            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

//            //建立输出流,并写入数据
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(param);
            outputStream.close();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (302 == responseCode) { //连接成功
                String newUrl = connection.getHeaderField("Location");
                url = new URL(newUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(6000);
                DataOutputStream dos1 = new DataOutputStream(connection.getOutputStream());
                dos1.writeBytes(param);
                dos1.flush();
                dos1.close();
                //获得响应状态
                int resultCode1 = connection.getResponseCode();
                if (HttpURLConnection.HTTP_OK == resultCode1) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";//失败

    }


    /**
     * 62      * Get服务请求
     * 63      *
     * 64      * @param requestUrl
     * 65      * @return
     * 66
     */
    public static String sendGet(String requestUrl, String session) {
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", "");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            //connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");

            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            connection.connect();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (200 == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    buffer.append(readLine).append("\n");
                }
                responseReader.close();
                Log.d("HttpGET", buffer.toString());
                //JSONObject result = new JSONObject(buffer.toString());
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendGet2(String requestUrl, String session, BaseRequestBackTLisenter baseRequestBackLisenter) {
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", session);
            connection.setDoOutput(false);
            connection.setDoInput(true);
            //connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");

            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            connection.connect();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (200 == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
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
            }else if (401 == responseCode){
                baseRequestBackLisenter.failF("获取数据失败");
            }else {
                baseRequestBackLisenter.fail("faile");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendGet1(String requestUrl, BaseRequestBackLisenter baseRequestBackLisenter) {
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setDoInput(false);
            // connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            connection.connect();

            //获取响应状态
            int responseCode = connection.getResponseCode();

            if (HttpURLConnection.HTTP_OK == responseCode) { //连接成功
                //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
