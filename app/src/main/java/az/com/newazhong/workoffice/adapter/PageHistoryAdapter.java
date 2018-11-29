package az.com.newazhong.workoffice.adapter;

import android.content.Context;
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
import az.com.newazhong.workoffice.activity.PageHistoryActivity;
import az.com.newazhong.workoffice.activity.PageHistoryDetailActivity;
import az.com.newazhong.workoffice.bean.PagerHistory;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/2/27.
 */

public class PageHistoryAdapter extends RecyclerView.Adapter<PageHistoryAdapter.ViewHolder> {
    List<PagerHistory> showMDeptList = new ArrayList<>();
    Context context;
    public PageHistoryAdapter(PageHistoryActivity pageHistoryActivity, List<PagerHistory> beanList) {
        this.context = pageHistoryActivity;
        this.showMDeptList = beanList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvDepartment,tvAu;
        public TextView tvContent;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvCopy);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvAu = (TextView) itemView.findViewById(R.id.tvAu);
            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
        }
    }

    @Override
    public PageHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pagerhistory,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), PageHistoryDetailActivity.class);
                PagerHistory showNotice = showMDeptList.get(position);
                intent.putExtra("Id",showNotice.getId());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(PageHistoryAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(showMDeptList.get(position).getTitle());
        holder.tvContent.setText(showMDeptList.get(position).getCopy());
        holder.tvTime.setText(showMDeptList.get(position).getStartTime());
        holder.tvDepartment.setText(showMDeptList.get(position).getEndTime());
        //holder.tvAu.setText(showMDeptList.get(position).getAuditor());
    }

    @Override
    public int getItemCount() {
        return showMDeptList.size();
    }
}
