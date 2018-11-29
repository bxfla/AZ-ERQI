package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.MyHttpURLConnection;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.workoffice.adapter.PropagandAdapter;
import az.com.newazhong.workoffice.bean.Propagand;
import az.com.newazhong.workoffice.bean.Propagand1;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PropagandaActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    PropagandAdapter adapter;
    String Url11 = Constant.BASE_URL+"party"+"?type="+"EDUCATION";
    String Url22 = Constant.BASE_URL+"party"+"?type="+"LEARN";
    String Url33 = Constant.BASE_URL+"party"+"?type="+"ACTIVITY";
    String Url111 = Constant.BASE_URL+"business"+"?type="+"LEGISLATION";
    String Url222= Constant.BASE_URL+"business"+"?type="+"PARTY_BUILDING";
    String Url333 = Constant.BASE_URL+"business"+"?type="+"DEPARTMENT_BUSINESS";
    List<Propagand>beanList = new ArrayList<>();
    Propagand bean;
    Propagand1 bean1;
    String tag1;
    String Session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String headerT = intent.getStringExtra("type");
        tag1 = intent.getStringExtra("tag");
        header.setTvTitle(headerT);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager( layoutManager );
        Session = new SharedPreferencesHelper(PropagandaActivity.this,"login").
                getData(this,"session","");
        if (Session!=null){
            ProgressDialogUtil.startLoad(this,"获取数据中");
            final Message message = new Message();
            if (tag1.equals("11")){
                Session = "";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url11,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }else if (tag1.equals("22")){
                Session = "";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url22,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }else if (tag1.equals("33")){
                Session = "";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url33,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }else if (tag1.equals("111")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url111,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }else if (tag1.equals("222")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url222,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }else if (tag1.equals("333")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyHttpURLConnection.getData(Url333,Session, new BaseRequestBackTLisenter() {
                            @Override
                            public void success(Object o) {
                                message.what = Constant.TAG_ONE;
                                message.obj = o;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void fail(String msg) {
                                message.what = Constant.TAG_TWO;
                                handler.sendMessage(message);
                            }

                            @Override
                            public void failF(String msg) {
                                message.what = Constant.TAG_THREE;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();
            }

        }
//        else {
//            new SharedPreferencesHelper(PropagandaActivity.this,"login").removeData(PropagandaActivity.this);
//            Intent intent1 = new Intent(PropagandaActivity.this,LoginActivity.class);
//            startActivity(intent1);
//            finish();
//        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_propaganda;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    private void setData() {
        if (tag1.equals("11")||tag1.equals("22")||tag1.equals("33")){
            adapter = new PropagandAdapter(PropagandaActivity.this,bean,tag1);
            recyclerView.setAdapter(adapter);
        }else if (tag1.equals("111")||tag1.equals("222")||tag1.equals("333")){
            adapter = new PropagandAdapter(PropagandaActivity.this,bean1,tag1);
            recyclerView.setAdapter(adapter);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.TAG_ONE:
                    if (tag1.equals("11")||tag1.equals("22")||tag1.equals("33")){
                        String data = msg.obj.toString();
                        bean = new Gson().fromJson(data, Propagand.class);
                        //填充数据
                        setData();
                        ProgressDialogUtil.stopLoad();
                    }else if (tag1.equals("111")||tag1.equals("222")||tag1.equals("333")){
                        String data = msg.obj.toString();
                        bean1 = new Gson().fromJson(data, Propagand1.class);
                        //填充数据
                        setData();
                        ProgressDialogUtil.stopLoad();
                    }
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(PropagandaActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(PropagandaActivity.this,"login").removeData(PropagandaActivity.this);
                    Intent intent = new Intent(PropagandaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
