package az.com.newazhong.workguide.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.workguide.activity.WorkGuideDetailsActivity;
import az.com.newazhong.workguide.bean.WorkGuideList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/3/25.
 */

public class WorkGuideListAdapter extends RecyclerView.Adapter<WorkGuideListAdapter.ViewHolder> {

    List<WorkGuideList.DataBean.ContentBean> workGuideList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public WorkGuideListAdapter(List<WorkGuideList.DataBean.ContentBean> workGuideList){
        this.workGuideList=workGuideList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mdept,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), WorkGuideDetailsActivity.class);
                WorkGuideList.DataBean.ContentBean workGuideListData = workGuideList.get(position);
                intent.putExtra("content", workGuideListData);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorkGuideList.DataBean.ContentBean workGuideListData = workGuideList.get(position);
        holder.tvTitle.setText(workGuideListData.getName());
    }

    @Override
    public int getItemCount() {
        return workGuideList.size();
    }
}

