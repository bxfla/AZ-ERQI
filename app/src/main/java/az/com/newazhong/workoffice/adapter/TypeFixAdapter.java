package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.workoffice.activity.TypeFixTypeActivity;
import az.com.newazhong.workoffice.bean.TypeFix;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2018/10/26.
 */

public class TypeFixAdapter extends RecyclerView.Adapter<TypeFixAdapter.ViewHolder> {
    Context context;
    TypeFix bean;
    public TypeFixAdapter(Context context, TypeFix bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public TypeFixAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice1,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), TypeFixTypeActivity.class);
                TypeFix.DataBean.ContentBean showNotice = bean.getData().getContent().get(position);
                intent.putExtra("bean",(Serializable)showNotice);
                intent.putExtra("header","会议通知");
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(TypeFixAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(bean.getData().getContent().get(position).getTitle());
        //holder.tvContent.setHtml1(bean.getData().getContent().get(position).getContent(),500);
        holder.tvContent.setVisibility(View.GONE);
        holder.tvTime.setText(bean.getData().getContent().get(position).getCreateTime());
        holder.tvDepartment.setText(bean.getData().getContent().get(position).getDepartmentName());
    }

    @Override
    public int getItemCount() {
        return bean.getData().getContent().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvDepartment;
        public RichTextView tvContent;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (RichTextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
        }
    }
}
