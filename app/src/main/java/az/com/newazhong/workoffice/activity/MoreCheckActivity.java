package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.workoffice.adapter.MoreCheckAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MoreCheckActivity extends BaseActivity implements MoreCheckAdapter.CallBackPosition{
    List<String> dataListing = new ArrayList<>();
    @Bind(R.id.header)
    Header header;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.activity_more_check)
    LinearLayout activityMoreCheck;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        dataListing = intent.getStringArrayListExtra("list");
        type = intent.getStringExtra("type");
        MoreCheckAdapter adapter = new MoreCheckAdapter(MoreCheckActivity.this, dataListing);
        LinearLayoutManager manager = new LinearLayoutManager(MoreCheckActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemLitener(MoreCheckActivity.this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_more_check;
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
        if (type.equals("1")){
            String data = dataListing.get(position);
            Intent intent = new Intent();
            intent.putExtra("result", data);
            setResult(Constant.TAG_ONE, intent);
            finish();
        }else if (type.equals("2")){
            String data = dataListing.get(position);
            Intent intent = new Intent();
            intent.putExtra("result", data);
            setResult(Constant.TAG_TWO, intent);
            finish();
        }
    }
}
