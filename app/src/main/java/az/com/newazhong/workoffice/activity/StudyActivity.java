package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import az.com.newazhong.workoffice.adapter.WorkOfficeListAdapter;
import az.com.newazhong.workoffice.bean.WorkOfficeUrlList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StudyActivity extends BaseActivity implements WorkOfficeListAdapter.OnItemClickListener {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    List<String> nameList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();
    WorkOfficeListAdapter adapter;
    String Url = Constant.BASE_URL+"approval";
    WorkOfficeUrlList bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( layoutManager );
        final String Session = new SharedPreferencesHelper(StudyActivity.this,"login").
                getData(this,"session","");
        if (Session!=null&&!Session.equals("")){
            ProgressDialogUtil.startLoad(this,"获取数据中");
            final Message message = new Message();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyHttpURLConnection.getData(Url,Session, new BaseRequestBackTLisenter() {
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

    //获取事项列表
    private void getData() {
        for (int i = 0;i<bean.getData().getContent().size();i++){
            nameList.add(bean.getData().getContent().get(i).getName());
        }
        imageList.add(R.drawable.workoffice_huiytt);
        imageList.add(R.drawable.workoffice_ricap);
        imageList.add(R.drawable.workoffice_1);
        imageList.add(R.drawable.workoffice_2);
        imageList.add(R.drawable.workoffice_3);
        imageList.add(R.drawable.workoffice_4);
        imageList.add(R.drawable.workoffice_5);
        imageList.add(R.drawable.workoffice_6);
        imageList.add(R.drawable.workoffice_7);
        imageList.add(R.drawable.workoffice_8);
        imageList.add(R.drawable.workoffice_9);
        imageList.add(R.drawable.workoffice_10);
        imageList.add(R.drawable.workoffice_11);
        imageList.add(R.drawable.workoffice_12);
        imageList.add(R.drawable.workoffice_13);
        adapter = new WorkOfficeListAdapter(StudyActivity.this, nameList,imageList);
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_study_list;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void onItemClick(int position) {
        String session = new SharedPreferencesHelper(this,"login").getData(this,"session","");
        Intent intent;

        if (session!=null&&session.equals("")){
            startActivity(new Intent(this, LoginActivity.class));
        }else {
                if (String.valueOf(bean.getData().getContent().get(position-4).getApprovalItems()).equals("[]")){
                    Toast.makeText(this, "暂无对应界面", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent1 = new Intent(this,PageActivity.class);
                    intent1.putExtra("header",bean.getData().getContent().get(position-4).getName());
                    intent1.putExtra("data", bean.getData().getContent().get(position-4));
                    startActivity(intent1);
                }
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.TAG_ONE:
                    String data = msg.obj.toString();
                    bean = new Gson().fromJson(data, WorkOfficeUrlList.class);
                    getData();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(StudyActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(StudyActivity.this,"login").removeData(StudyActivity.this);
                    Intent intent = new Intent(StudyActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
