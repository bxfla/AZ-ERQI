package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import az.com.newazhong.workoffice.adapter.TypeFixAdapter;
import az.com.newazhong.workoffice.adapter.TypeFixRCAPAdapter;
import az.com.newazhong.workoffice.bean.TypeFix;
import az.com.newazhong.workoffice.bean.TypeFixRCAP;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TypeFixActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    List<TypeFix> dataList = new ArrayList<>();
    String noticeUrl = Constant.BASE_URL + "notice" + "?type=" + "MEMO_NOTICE";
    String noticeUr2 = Constant.BASE_URL + "notice" + "?type=" + "MEETING_NOTICE";
    String noticeUrl1 = Constant.BASE_URL + "schedule";
    String type;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        SharedPreferencesHelper preference = new SharedPreferencesHelper(this, "login");
        String userId = preference.getData(this, "userId", "");
        noticeUr2 = noticeUr2 + "&ccUserId=" + userId;
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        header.setTvTitle(type);
        ProgressDialogUtil.startLoad(this, "加载数据中");
        final String session = new SharedPreferencesHelper(this, "login").getData(this, "session", "");
        if (!session.equals("")) {
            final Message message = new Message();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (type.equals("会议通知")) {
                        MyHttpURLConnection.getData(noticeUr2, session, new BaseRequestBackTLisenter() {
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
                    } else if (type.equals("日程安排")) {
                        MyHttpURLConnection.getData(noticeUrl1, session, new BaseRequestBackTLisenter() {
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
                    } else if (type.equals("内部通知")) {
                        MyHttpURLConnection.getData(noticeUr2, session, new BaseRequestBackTLisenter() {
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

                }
            }).start();
        } else {
            Intent intent1 = new Intent(TypeFixActivity.this, LoginActivity.class);
            new SharedPreferencesHelper(TypeFixActivity.this, "login").removeData(TypeFixActivity.this);
            startActivity(intent1);
        }

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_type_fix;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    String data = msg.obj.toString();
                    if (type.equals("会议通知") || type.equals("内部通知")) {
                        TypeFix jsonRootBean = new Gson().fromJson(data, TypeFix.class);
                        String data1 = jsonRootBean.getData().getContent().toString();
                        Log.e("XXXH",data1);
                        if (!jsonRootBean.getData().getContent().toString().equals("[]")){
                            TypeFixAdapter adapter = new TypeFixAdapter(TypeFixActivity.this, jsonRootBean);
                            LinearLayoutManager manager = new LinearLayoutManager(TypeFixActivity.this);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                        }else {
                            recyclerView.setVisibility(View.GONE);
                            llNoContent.setVisibility(View.VISIBLE);
                        }
                        ProgressDialogUtil.stopLoad();
                    } else if (type.equals("日程安排")) {
                        TypeFixRCAP jsonRootBean = new Gson().fromJson(data, TypeFixRCAP.class);
                        TypeFixRCAPAdapter adapter = new TypeFixRCAPAdapter(TypeFixActivity.this, jsonRootBean);
                        LinearLayoutManager manager = new LinearLayoutManager(TypeFixActivity.this);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        ProgressDialogUtil.stopLoad();
                    }

                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(TypeFixActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    Intent intent = new Intent(TypeFixActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
