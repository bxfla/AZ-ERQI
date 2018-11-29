package az.com.newazhong.workguide.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.refreshview.CustomRefreshView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseFastJSONRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.utils.ActivityCollector;
import az.com.newazhong.utilsclass.utils.FastUtils;
import az.com.newazhong.utilsclass.utils.HttpUrlConnectionUtils;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.workguide.adapter.WorkGuideListAdapter;
import az.com.newazhong.workguide.bean.WorkGuideList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkGuideListActivity extends BaseActivity  {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    CustomRefreshView recyclerView;
    @Bind(R.id.activity_workguide_list)
    LinearLayout activityWorkguideList;

    List<WorkGuideList.DataBean.ContentBean> beanWorkGuideList = new ArrayList<>();
    List<WorkGuideList.DataBean.ContentBean> beanWorkGuideList1 = new ArrayList<>();
    WorkGuideListAdapter workGuideListAdapter;
    AlertDialog dialog;
    String departName;
    String result,session;
    SharedPreferencesHelper preference;
    String guideCategoryId = "";
    int page = 0;
    int size = 20;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        preference = new SharedPreferencesHelper(this,"login");
        session = preference.getData(this,"session","");
        Intent intent = getIntent();
        departName = intent.getStringExtra("name");
        guideCategoryId = intent.getStringExtra("guideCategoryId");
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
        recyclerView.getRecyclerView().setLayoutManager(manager);
        ProgressDialogUtil.startLoad(this,"获取数据中");
        getData();
        setClient();
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                url = Constant.BASE_URL+"guide?size="+size+"&page="+page;
                url = url+"&guideCategoryId="+guideCategoryId;
                result = HttpUrlConnectionUtils.sendGet(url,session);
                if (result == null){
                    Message message = new Message();
                    message.what = Constant.TAG_ONE;
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = Constant.TAG_TWO;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private void setClient() {
        recyclerView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                beanWorkGuideList.clear();
                WorkGuideListActivity.this.page = 0;
                WorkGuideListActivity.this.size = 20;
                getData();
            }

            @Override
            public void onLoadMore() {
                page = page+1;
                WorkGuideListActivity.this.size = 20;
                getData();
            }
        });
    }

    private void getWorkGuideList(String url) {
        FastUtils.getFastData(url, new BaseFastJSONRequestBackLisenter() {
            @Override
            public void success(String t) {
                WorkGuideList workGuideList = JSON.parseObject(t,WorkGuideList.class);
                Toast.makeText(WorkGuideListActivity.this, String.valueOf(workGuideList.isResult()), Toast.LENGTH_SHORT).show();
                if (String.valueOf(workGuideList.isResult()).equals("false")){
                    if (dialog == null || !dialog.isShowing()) {
                        dialog = new AlertDialog.Builder(WorkGuideListActivity.this).create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        LayoutInflater inflater = LayoutInflater.from(WorkGuideListActivity.this);
                        View view = inflater.inflate(R.layout.dialog_with_one_title, null, false);
                        TextView tv_content = (TextView) view.findViewById(R.id.content);
                        TextView tv_yes = (TextView) view.findViewById(R.id.yes);
                        tv_content.setText(getResources().getString(R.string.no_content));
                        tv_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                        dialog.getWindow().setContentView(view);
                    }
                }else {
//                    for (int i=0;i<workGuideList.getData().getAuditMaterials().size();i++){
//                        beanWorkGuideList.add(workGuideList.getData().getAuditMaterials().get(i));
//                    }
                    Message message = new Message();
                    message.what = Constant.TAG_ONE;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void fail(String message) {
                Toast.makeText(WorkGuideListActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_workguide_list;
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
            switch (msg.what) {
                case Constant.TAG_TWO:
                    WorkGuideList workGuideList = JSON.parseObject(result,WorkGuideList.class);
                    if (workGuideList!=null){
                        beanWorkGuideList1.clear();
                        for (int i=0;i<workGuideList.getData().getContent().size();i++){
                                beanWorkGuideList.add(workGuideList.getData().getContent().get(i));
                                beanWorkGuideList1.add(workGuideList.getData().getContent().get(i));
                        }
                        if(beanWorkGuideList.size()==0){
                            Toast.makeText(WorkGuideListActivity.this, "暂无对应事件", Toast.LENGTH_SHORT).show();
                        }else {
                            if (workGuideList.getData().getContent().size() == 20&&beanWorkGuideList1.size()==20&&beanWorkGuideList.size()==20){
                                workGuideListAdapter = new WorkGuideListAdapter(beanWorkGuideList);
                                recyclerView.setAdapter(workGuideListAdapter);
                                recyclerView.complete();
                            }else if (workGuideList.getData().getContent().size()<20&&beanWorkGuideList1.size()>0&&beanWorkGuideList.size()<20){
                                workGuideListAdapter = new WorkGuideListAdapter(beanWorkGuideList);
                                recyclerView.setAdapter(workGuideListAdapter);
                                recyclerView.complete();
                                recyclerView.onNoMore();
                            }else if (workGuideList.getData().getContent().size() == 20&&beanWorkGuideList1.size()==20&&beanWorkGuideList.size()>=0){
                                workGuideListAdapter.notifyDataSetChanged();
                                recyclerView.complete();
                            }else if (workGuideList.getData().getContent().size()<20&&beanWorkGuideList1.size()<0){
                                workGuideListAdapter = new WorkGuideListAdapter(beanWorkGuideList);
                                recyclerView.setAdapter(workGuideListAdapter);
                                recyclerView.complete();
                            }else if (workGuideList.getData().getContent().size()<20&&beanWorkGuideList1.size()>0){
                                workGuideListAdapter.notifyDataSetChanged();
                                //adapter.notifyItemRangeInserted(adapter.getItemCount(), beanList.size() - 1);
                                recyclerView.onNoMore();
                            }
//                            else if (workGuideList.getData().getContent().size() == 20&&beanWorkGuideList.size()>20){
//                                workGuideListAdapter.notifyDataSetChanged();
//                                recyclerView.complete();
//                            }
//                            workGuideListAdapter = new WorkGuideListAdapter(beanWorkGuideList);
//                            recyclerView.setAdapter(workGuideListAdapter);
                        }
                    }else {
                        Toast.makeText(WorkGuideListActivity.this, "暂无对应事件", Toast.LENGTH_SHORT).show();
                    }

                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_ONE:
                    if (dialog == null || !dialog.isShowing()) {
                        dialog = new AlertDialog.Builder(WorkGuideListActivity.this).create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        LayoutInflater inflater = LayoutInflater.from(WorkGuideListActivity.this);
                        View view = inflater.inflate(R.layout.dialog_with_one_title, null, false);
                        TextView tv_content = (TextView) view.findViewById(R.id.content);
                        TextView tv_yes = (TextView) view.findViewById(R.id.yes);
                        tv_content.setText(getResources().getString(R.string.no_content));
                        tv_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                        dialog.getWindow().setContentView(view);
                        ProgressDialogUtil.stopLoad();
                    }
                    break;
            }
        }
    };
}
