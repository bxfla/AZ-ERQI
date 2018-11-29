package az.com.newazhong.utilsclass.person.activity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import butterknife.ButterKnife;

public class QueryActivityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }).start();
    }

    private void getData() {
        String session = new SharedPreferencesHelper(this,"login").getData(this,"session","");
        String url1 = Constant.BASE_URL+"registration";
        try {
            //建立连接
            URL url = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接属性
            connection.setDoOutput(true); //使用URL连接进行输出
            connection.setDoInput(true); //使用URL连接进行输入
            connection.setUseCaches(false); //忽略缓存
            connection.addRequestProperty("Cookie", session);
            connection.setRequestMethod("GET"); //设置URL请求方法

//            //设置请求属性
//            //connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
//            connection.setRequestProperty("Charset", "UTF-8");
//            connection.setRequestProperty("accept","application/json");
//            connection.setInstanceFollowRedirects(false);
//            connection.setConnectTimeout(8000);
//            connection.setReadTimeout(8000);

//            //建立输出流,并写入数据
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.close();
            //获取响应状态
            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) { //连接成功
                String newUrl = connection.getHeaderField("Location");
                url = new URL(newUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(6000);
                connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");
                connection.setRequestMethod("POST"); //设置URL请求方法

                //设置请求属性
                //connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("accept","application/json");
                DataOutputStream dos1 = new DataOutputStream(connection.getOutputStream());
//                dos1.writeBytes(param);
                dos1.flush();
                dos1.close();
                //获得响应状态
                int resultCode1 = connection.getResponseCode();
                if (HttpURLConnection.HTTP_OK == resultCode1) {
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_query_activity;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
