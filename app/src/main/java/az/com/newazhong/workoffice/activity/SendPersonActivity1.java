package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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
import az.com.newazhong.workoffice.adapter.SendPersonAdapter1;
import az.com.newazhong.workoffice.adapter.SendPersonDepAdapter;
import az.com.newazhong.workoffice.bean.SendPerson;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendPersonActivity1 extends BaseActivity implements SendPersonAdapter1.CallBackPosition,
        SendPersonDepAdapter.CallDepBackPosition {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    String Url = Constant.BASE_URL + "user/duser";
    List<SendPerson> listPerson = new ArrayList<>();
    List<SendPerson> listPerson1 = new ArrayList<>();
    List<SendPerson> listPerson2 = new ArrayList<>();
    List<String> listDep = new ArrayList<>();
    String headerT;
    @Bind(R.id.recyclerViewDep)
    RecyclerView recyclerViewDep;

    String Dep;
    @Bind(R.id.tv_tittle1)
    TextView tvTittle1;
    @Bind(R.id.iv_left1)
    ImageView ivLeft1;
    @Bind(R.id.tv_right1)
    TextView tvRight1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        headerT = intent.getStringExtra("send");
        header.setTvTitle(headerT);

        final String session = new SharedPreferencesHelper(this, "login").getData(this, "session", "");
//        ProgressDialogUtil.startLoad(this, "获取数据中");
        final Message message = new Message();
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyHttpURLConnection.getData(Url, session, new BaseRequestBackTLisenter() {
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    String data = msg.obj.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonData = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonData.length(); i++) {
                            JSONObject jsonSObject = jsonData.getJSONObject(i);
                            JSONArray jsonSArray = jsonSObject.getJSONArray("users");
                            String dName = jsonSObject.getString("dName");
                            listDep.add(dName);
                            if (!jsonSArray.equals("[]")) {
                                for (int k = 0; k < jsonSArray.length(); k++) {
                                    JSONObject jsonObjectData = jsonSArray.getJSONObject(k);
                                    SendPerson bean = new SendPerson();
                                    bean.setName(jsonObjectData.getString("trueName"));
                                    bean.setdName(dName);
                                    bean.setId(jsonObjectData.getString("id"));
                                    listPerson.add(bean);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SendPersonDepAdapter adapter1 = new SendPersonDepAdapter(SendPersonActivity1.this, listDep);
                    LinearLayoutManager manager1 = new LinearLayoutManager(SendPersonActivity1.this);
                    recyclerViewDep.setLayoutManager(manager1);
                    recyclerViewDep.setAdapter(adapter1);
                    adapter1.setOnDepItemLitener(SendPersonActivity1.this);
//                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(SendPersonActivity1.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(SendPersonActivity1.this, "login").removeData(SendPersonActivity1.this);
                    Intent intent = new Intent(SendPersonActivity1.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_send_person1;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        if (headerT.equals("审核人")) {
            Intent i = new Intent();
            i.putExtra("name", (Serializable) listPerson1);
            setResult(Constant.TAG_FIVE, i);
            finish();
        } else if (headerT.equals("抄送人")) {
            Intent i = new Intent();
            i.putExtra("name", (Serializable) listPerson1);
            setResult(Constant.TAG_SIX, i);
            finish();
        }
    }

    @Override
    public void onItemClick(int position) {
//        listPerson.clear();
        if (listPerson1.contains(listPerson2.get(position))) {
            listPerson1.remove(listPerson2.get(position));
        } else {
            listPerson1.add(listPerson2.get(position));
        }
        Intent i = new Intent();
        i.putExtra("name", (Serializable) listPerson1);
        setResult(Constant.TAG_FIVE, i);
        finish();
    }

    @Override
    public void onDepItemClick(int position) {
        Dep = listDep.get(position);
        listPerson2.clear();
        for (int i = 0; i < listPerson.size(); i++) {
            if (listPerson.get(i).getdName().equals(Dep)) {
                listPerson2.add(listPerson.get(i));
            }
        }
        if (listPerson2.size() == 0) {
            Toast.makeText(this, "暂无该部门对应人员", Toast.LENGTH_SHORT).show();
        } else {
            SendPersonAdapter1 adapter = new SendPersonAdapter1(SendPersonActivity1.this, listPerson2);
            LinearLayoutManager manager = new LinearLayoutManager(SendPersonActivity1.this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemLitener(SendPersonActivity1.this);
            recyclerViewDep.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.iv_left1, R.id.tv_right1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left1:
                if (recyclerViewDep.getVisibility() == View.VISIBLE&&recyclerView.getVisibility() == View.GONE){
                    finish();
                }else if (recyclerViewDep.getVisibility() == View.GONE&&recyclerView.getVisibility() == View.VISIBLE){
                    recyclerViewDep.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_right1:
                if (headerT.equals("审核人")) {
                    Intent i = new Intent();
                    i.putExtra("name", (Serializable) listPerson1);
                    setResult(Constant.TAG_FIVE, i);
                    finish();
                } else if (headerT.equals("抄送人")) {
                    Intent i = new Intent();
                    i.putExtra("name", (Serializable) listPerson1);
                    setResult(Constant.TAG_SIX, i);
                    finish();
                }
                break;
        }
    }
}
