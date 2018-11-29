package az.com.newazhong.utilsclass.person.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import az.com.newazhong.street12345.activity.view.StreetView;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.person.activity.FindAddActivity;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.person.adapter.QuestionFragmentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/8/1.
 */

public class Fragment1 extends Fragment implements StreetView {
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    CustomRefreshView recyclerView;
    StreetPresenter presenter;
    QuestionFragmentAdapter adapter;
    List<Street.DataBean.ContentBean> beanList = new ArrayList<>();
    @Bind(R.id.tv_tittle)
    TextView tvTittle;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    String messageT, content;
    int page = 0;
    int size = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        recyclerView.getRecyclerView().setLayoutManager(manager);
        recyclerView.setLoadMoreEnable(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                page = 0;
                size = 1000;
                getData(page,size);
            }
        });
        //getData(page,size);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setClient();
    }

    private void getData(final int page, final int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String session = new SharedPreferencesHelper(getActivity(), "login").getData(getActivity(), "session", "");
                    String url0 = Constant.BASE_URL + "matter" + "?page=" + page + "&size=" + size;
                    Log.e("XXX", url0);
                    URL url = new URL(url0);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);
                    connection.setRequestProperty("Cookie", "");

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
                getData(page, size);
            }

            @Override
            public void onLoadMore() {
//                page+=1;
//                size = 1000;
//                getData(page,size);
            }
        });
    }

    private void setAdapterData() {
        adapter = new QuestionFragmentAdapter(getActivity(), beanList, "no");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        SharedPreferencesHelper preference;
        preference = new SharedPreferencesHelper(getActivity(), "login");
        String session = preference.getData(getActivity(), "session", "");
        if (session.equals("")) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), FindAddActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getStreetView(Street street) {
        for (int i = 0; i < street.getData().getContent().size(); i++) {
            // beanList.add(street.getData().getContent().get(i));
        }
        adapter = new QuestionFragmentAdapter(getActivity(), beanList, "no");
    }

    @Override
    public void onStart() {
        super.onStart();
        beanList.clear();
        page = 0;
        size = 1000;
        getData(page, size);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Street resultBeanData = JSON.parseObject(content, Street.class);
            if (messageT.equals("false")) {
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            } else if (resultBeanData.getData().getContent().size() == 0 && page == 0) {
                llNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            } else if (resultBeanData.getData().getContent().size() == 0 && page != 0) {
                recyclerView.onNoMore();
                swipeRefreshLayout.setRefreshing(false);
            } else if (resultBeanData.getData().getContent().size() !=0 && page == 0) {
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
//                    beanList.add(show12345);
                    if (resultBeanData.getData().getContent().get(i).isOpen()){
                        beanList.add(resultBeanData.getData().getContent().get(i));
                    }
                }
                setAdapterData();
                recyclerView.onNoMore();
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.setRefreshEnable(false);
                recyclerView.setLoadMoreEnable(false);
            } else if (resultBeanData.getData().getContent().size() != 0 && page != 0) {
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
//                    beanList.add(show12345);
                    beanList.add(resultBeanData.getData().getContent().get(i));
                }
                adapter.notifyDataSetChanged();
                recyclerView.complete();
                swipeRefreshLayout.setRefreshing(false);
            } else {
                for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                    Show12345 show12345 = new Show12345();
//                    show12345.title = resultBeanData.getData().getContent().get(i).getTitle();
//                    show12345.content = String.valueOf(resultBeanData.getData().getContent().get(i).getContent());
//                    show12345.name = resultBeanData.getData().getContent().get(i).getSponsorName();
//                    show12345.time = resultBeanData.getData().getContent().get(i).getModifiedTime();
//                    show12345.type = String.valueOf(resultBeanData.getData().getContent().get(i).getMatterState());
//                    beanList.add(show12345);
                    beanList.add(resultBeanData.getData().getContent().get(i));
                }
                setAdapterData();
                recyclerView.complete();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };
}
