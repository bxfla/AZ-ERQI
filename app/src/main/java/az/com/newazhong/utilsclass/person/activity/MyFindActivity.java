package az.com.newazhong.utilsclass.person.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.example.refreshview.CustomRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.adapter.QuestionFragmentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFindActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    CustomRefreshView recyclerView;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    QuestionFragmentAdapter adapter;
    List<Street.DataBean.ContentBean> beanList = new ArrayList<>();
    String messageT,content;
    int page = 0;
    int size = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getData(page,size);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_find;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    private void getData(final int page, final int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String id = new SharedPreferencesHelper(MyFindActivity.this,"login").getData(MyFindActivity.this,"userId","");
                    String session = new SharedPreferencesHelper(MyFindActivity.this,"login").getData(MyFindActivity.this,"session","");
                    String url0 = Constant.BASE_URL + "matter"+"?sponsorId="+id+"&page="+page+"&size="+size;
                    URL url = new URL(url0);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);
                    connection.setRequestProperty("Cookie", session);

                    connection.connect();

                    //获取响应状态
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 200) {
                        inputStream = new BufferedInputStream(connection.getInputStream());
                    } else {
                        inputStream = new BufferedInputStream(connection.getErrorStream());
                    }
                    BufferedReader responseReader;
                    responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuffer buffer = new StringBuffer();
                    String readLine;
                    while ((readLine = responseReader.readLine()) != null) {
                        buffer.append(readLine);
                    }
                    responseReader.close();
                    Log.d("HttpPOST", buffer.toString());
                    content = buffer.toString();
                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    messageT = jsonObject.getString("result");
                    Message message = new Message();
                    message.what = Constant.TAG_ONE;
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Street resultBeanData = JSON.parseObject(content, Street.class);
            if (messageT.equals("false")){
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }else if (resultBeanData.getData().getContent().size() == 0 &&page == 0){
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }else if (resultBeanData.getData().getContent().size() == 0&&page!=0){
                recyclerView.onNoMore();
            }else if (resultBeanData.getData().getContent().size()<=10&&page==0){
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
                    beanList.add(resultBeanData.getData().getContent().get(i));
                }
                setAdapterData();
                recyclerView.onNoMore();
                recyclerView.setRefreshEnable(false);
                recyclerView.setLoadMoreEnable(false);
            }else if (resultBeanData.getData().getContent().size() != 0&&page!=0){
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
                    beanList.add(resultBeanData.getData().getContent().get(i));
                }
                adapter.notifyDataSetChanged();
                recyclerView.complete();
            } else {
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
                    beanList.add(resultBeanData.getData().getContent().get(i));
                }
                setAdapterData();
                recyclerView.complete();
            }
        }
    };

    private void setAdapterData() {
        adapter = new QuestionFragmentAdapter(MyFindActivity.this, beanList,"yes");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
