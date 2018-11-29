package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.workoffice.adapter.WorkOfficeListAdapter;
import az.com.newazhong.workoffice.bean.WorkOfficeUrlList;
import az.com.newazhong.xingzhengwork.SignInActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkOfficeListActivity extends BaseActivity implements WorkOfficeListAdapter.OnItemClickListener {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    List<String> nameList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();
    WorkOfficeListAdapter adapter;
    WorkOfficeUrlList bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }
    //获取事项列表
    private void getData() {
//        nameList.add("考勤打卡");
        nameList.add("会议通知");
        nameList.add("日程安排");
        nameList.add("申请审批");
        nameList.add("业务培训");
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
        adapter = new WorkOfficeListAdapter(WorkOfficeListActivity.this, nameList,imageList);
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_work_office_list;
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
            if (nameList.get(position).equals("日程安排")){
                intent = new Intent(WorkOfficeListActivity.this,TypeFixActivity.class);
                intent.putExtra("type","日程安排");
                startActivity(intent);
            }else if (nameList.get(position).equals("考勤打卡")){
                intent = new Intent(WorkOfficeListActivity.this,SignInActivity.class);
                startActivity(intent);
            }else if (nameList.get(position).equals("会议通知")){
//                intent = new Intent(WorkOfficeListActivity.this,MemoActivity.class);
//                intent.putExtra("type","会议通知");
//                startActivity(intent);
                intent = new Intent(this,TypeFixActivity.class);
                intent.putExtra("type","会议通知");
                startActivity(intent);
            }else if (nameList.get(position).equals("业务培训")){
                intent = new Intent(WorkOfficeListActivity.this,VacationalActivity.class);
                intent.putExtra("type","业务培训");
                startActivity(intent);
            }else if (nameList.get(position).equals("申请审批")){
                intent = new Intent(WorkOfficeListActivity.this,PageListActivity.class);
                intent.putExtra("type","申请审批");
                startActivity(intent);
            }
        }
    }
}
