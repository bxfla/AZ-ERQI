package az.com.newazhong.xingzhengwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import az.com.newazhong.utilsclass.utils.HttpUrlConnectionUtils;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.xingzhengwork.bean.SignInHistory;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInHistoryActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    List<SignInHistory.DataBean> beanList = new ArrayList<>();
    Message message = new Message();
    String session,url,content;
    SignInHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
        if (tag!=null&&tag.equals("on")){
            Toast.makeText(this, "请在对应时间段打卡", Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        session = new SharedPreferencesHelper(this,"login").getData(this,"session","");
        url = Constant.BASE_URL+"record/dayRecord";
        ProgressDialogUtil.startLoad(this,"获取数据中");
        getData(url,session);
    }

    private void getData(final String url, final String session) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrlConnectionUtils.sendGet2(url, session, new BaseRequestBackTLisenter() {
                    @Override
                    public void success(Object o) {
                        message.what = Constant.TAG_ONE;
                        Log.d("XXX",o.toString());
                        content = o.toString();
                        handler.sendMessage(message);
                    }

                    @Override
                    public void fail(String messageT) {
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void failF(String messageT) {
                        message.what = Constant.TAG_THREE;
                        handler.sendMessage(message);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_sign_in_history;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.TAG_ONE:
                    try {
                        JSONObject jsonObject = new JSONObject(content);
                        String result = String.valueOf(jsonObject.getString("result"));
                        if (result.equals("true")){
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0;i<jsonArray.length();i++){
                                SignInHistory.DataBean bean = new SignInHistory.DataBean();
                                JSONObject jsonObjectData = jsonArray.getJSONObject(i);
                                bean.setAddress(jsonObjectData.getString("address"));
                                bean.setRecordTime(jsonObjectData.getString("recordTime"));
                                bean.setName(jsonObjectData.getString("name"));
                                beanList.add(bean);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter = new SignInHistoryAdapter(SignInHistoryActivity.this,beanList);
                    recyclerView.setAdapter(adapter);
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(SignInHistoryActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    Intent intent = new Intent(SignInHistoryActivity.this, LoginActivity.class);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };
}
