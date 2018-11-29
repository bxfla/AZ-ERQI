package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import az.com.newazhong.workoffice.adapter.PageHistoryAdapter;
import az.com.newazhong.workoffice.bean.PagerHistory;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PageHistoryActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PagerHistory> beanList = new ArrayList<>();
    String Id,HeaderT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Id = intent.getStringExtra("Id");
        HeaderT = intent.getStringExtra("header");
        header.setTvTitle(HeaderT);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager( layoutManager );
        final String Session = new SharedPreferencesHelper(PageHistoryActivity.this,"login").
                getData(this,"session","");
        final String Url = Constant.BASE_URL+"approval/apply/page";
        final Message message = new Message();
        ProgressDialogUtil.startLoad(this,"获取数据中");
        if (Session!=null&&!Session.equals("")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyHttpURLConnection.getData(Url, Session, new BaseRequestBackTLisenter() {
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
        }else {
            Intent intent1 = new Intent(PageHistoryActivity.this, LoginActivity.class);
            new SharedPreferencesHelper(PageHistoryActivity.this,"login").removeData(PageHistoryActivity.this);
            startActivity(intent1);
            ProgressDialogUtil.stopLoad();        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_page_history;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 111:
                    Toast.makeText(PageHistoryActivity.this, "解析数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_ONE:
                    String data = msg.obj.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("content");
                        for (int i = 0;i<jsonArray.length();i++){
                            PagerHistory bean = new PagerHistory();
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject3 = jsonObject2.getJSONObject("approvalGroup");
                            int num = jsonObject3.length();
//                            if(num!=0){
//                                String auditor = "";
//                                for (int k = 0;k<num;k++){
//                                    String num1 = String.valueOf(k);
//                                    JSONObject jsonObject4 = new JSONObject(num1);
//                                    auditor = jsonObject4.getString("auditor");
//                                }
//                                bean.setAuditor(auditor);
//                                if (jsonObject2.get("approvalName").equals(HeaderT)){
//                                    bean.setCopy(jsonObject2.getString("proposerName"));
//                                    bean.setTitle(jsonObject2.getString("approvalName"));
//                                    bean.setStartTime(jsonObject2.getString("createTime"));
//                                    bean.setEndTime(jsonObject2.getString("modifiedTime"));
//                                    bean.setId(jsonObject2.getString("id"));
//                                    beanList.add(bean);
//                                }
//                            }else {
                                if (jsonObject2.get("approvalName").equals(HeaderT)){
                                    bean.setCopy(jsonObject2.getString("proposerName"));
                                    bean.setTitle(jsonObject2.getString("approvalName"));
                                    bean.setStartTime(jsonObject2.getString("createTime"));
                                    bean.setEndTime(jsonObject2.getString("modifiedTime"));
                                    bean.setId(jsonObject2.getString("id"));
                                    beanList.add(bean);
                                }
//                            }
                        }
                        PageHistoryAdapter adapter = new PageHistoryAdapter(PageHistoryActivity.this,beanList);
                        recyclerView.setAdapter(adapter);
                        ProgressDialogUtil.stopLoad();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Message message = new Message();
                        message.what = 111;
                        handler.sendMessage(message);
                    }
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(PageHistoryActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(PageHistoryActivity.this,"login").removeData(PageHistoryActivity.this);
                    Intent intent = new Intent(PageHistoryActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
