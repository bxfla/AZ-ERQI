package az.com.newazhong.street12345.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
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
import az.com.newazhong.street12345.activity.presenter.StreetPresenter;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.adapter.QuestionFragmentAdapter;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class Street12345Activity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    CustomRefreshView recyclerView;
    @Bind(R.id.activity_street12345)
    LinearLayout activityStreet12345;
    StreetPresenter presenter;
    QuestionFragmentAdapter adapter;

    List<Street.DataBean.ContentBean> beanList = new ArrayList<>();
    SharedPreferencesHelper preferencesHelper;
    String messageT, content;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    int page = 0;
    int size = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        preferencesHelper = new SharedPreferencesHelper(this, "login");
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        recyclerView.getRecyclerView().setLayoutManager(manager);
        ProgressDialogUtil.startLoad(this,"获取数据中");
        getData(page,size);
        setClient();
    }

    private void getData(final int page, final int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String session = new SharedPreferencesHelper(Street12345Activity.this,"login").getData(Street12345Activity.this,"session","");
                    String url0 = Constant.BASE_URL + "matter"+"?page="+page+"&size="+size;
                    URL url = new URL(url0);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);

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

    private void setClient() {
        recyclerView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                page = 0;
                size = 1000;
                getData(page,size);
            }

            @Override
            public void onLoadMore() {
                page+=1;
                size = 1000;
                getData(page,size);
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_street12345;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Street resultBeanData = JSON.parseObject(content, Street.class);
            if (messageT.equals("false")) {
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                ProgressDialogUtil.stopLoad();
            } else if (resultBeanData.getData().getContent().size() == 0&&page == 0) {
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                ProgressDialogUtil.stopLoad();
            }else if (resultBeanData.getData().getContent().size() == 0&&page!=0){
                recyclerView.onNoMore();
            }else if (resultBeanData.getData().getContent().size()!= 0&&page==0){
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
                    if (resultBeanData.getData().getContent().get(i).isOpen()){
                        beanList.add(resultBeanData.getData().getContent().get(i));
                    }
                }
                setAdapterData();
                ProgressDialogUtil.stopLoad();
                recyclerView.onNoMore();
                recyclerView.setRefreshEnable(false);
                recyclerView.setLoadMoreEnable(false);
            }else if (resultBeanData.getData().getContent().size() != 0&&page!=0){
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
                    if (resultBeanData.getData().getContent().get(i).isOpen()){
                        beanList.add(resultBeanData.getData().getContent().get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                recyclerView.complete();
            }  else {
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
                    beanList.add(resultBeanData.getData().getContent().get(i));
                    ProgressDialogUtil.stopLoad();
                }
                setAdapterData();
                recyclerView.complete();
            }
        }
    };

    private void setAdapterData() {
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        recyclerView.getRecyclerView().setLayoutManager(manager);
        adapter = new QuestionFragmentAdapter(this, beanList,"yes");
        recyclerView.setAdapter(adapter);
//        adapter = new ActivityContentAdapter(beanList);
//        pullLoadMoreRecyclerView.setAdapter(adapter);
//        adapter.getItemPositionView(this);
    }
}
