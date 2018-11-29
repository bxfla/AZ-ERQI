package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.workoffice.activity.PropagandDetailActivity;
import az.com.newazhong.workoffice.activity.PropagandaActivity;
import az.com.newazhong.workoffice.bean.Propagand;
import az.com.newazhong.workoffice.bean.Propagand1;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2018/11/3.
 */

public class PropagandAdapter extends RecyclerView.Adapter<PropagandAdapter.ViewHolder> {
    Context context;
    Propagand bean;
    Propagand1 bean1;
    String tag1;
    public PropagandAdapter(PropagandaActivity propagandaActivity, Propagand bean,String tag1) {
        this.context = propagandaActivity;
        this.bean = bean;
        this.tag1 = tag1;
    }

    public PropagandAdapter(PropagandaActivity propagandaActivity, Propagand1 bean2,String tag1) {
        this.context = propagandaActivity;
        this.bean1 = bean2;
        this.tag1 = tag1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvDepartment;
        public RichTextView tvContent;
        public LinearLayout linearLayout;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (RichTextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(MyApplication.getContextObject(), PropagandDetailActivity.class);
                    if (tag1.equals("11")||tag1.equals("22")||tag1.equals("33")) {
                        Propagand.DataBean.ContentBean propagand = bean.getData().getContent().get(position);
                        intent.putExtra("bean",(Serializable)propagand);
                        intent.putExtra("tag","1");
                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContextObject().startActivity(intent);
                    }else if (tag1.equals("111")||tag1.equals("222")||tag1.equals("333")) {
                        Propagand1.DataBean.ContentBean propagand = bean1.getData().getContent().get(position);
                        intent.putExtra("bean",(Serializable)propagand);
                        intent.putExtra("tag","2");
                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContextObject().startActivity(intent);
                    }
                }
            });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (tag1.equals("11")||tag1.equals("22")||tag1.equals("33")){
            holder.tvTitle.setText(bean.getData().getContent().get(position).getTitle());
            holder.tvContent.setHtml1(bean.getData().getContent().get(position).getContent(),500);
            holder.tvTime.setText(bean.getData().getContent().get(position).getCreateTime());
            holder.tvDepartment.setText(bean.getData().getContent().get(position).getModifiedTime());
            holder.tvTime.setVisibility(View.GONE);
            holder.tvDepartment.setVisibility(View.GONE);
            holder.linearLayout.setVisibility(View.GONE);
        }else if (tag1.equals("111")||tag1.equals("222")||tag1.equals("333")){
            holder.tvTitle.setText(bean1.getData().getContent().get(position).getName());
            holder.tvContent.setHtml1(bean1.getData().getContent().get(position).getContent(),500);
            holder.tvTime.setText(bean1.getData().getContent().get(position).getCreateTime());
            holder.tvDepartment.setText(bean1.getData().getContent().get(position).getModifiedTime());
        }
    }

    @Override
    public int getItemCount() {
        int num = 0;
        if (tag1.equals("11")||tag1.equals("22")||tag1.equals("33")){
            num = bean.getData().getContent().size();
        }else if (tag1.equals("111")||tag1.equals("222")||tag1.equals("333")){
            num = bean1.getData().getContent().size();
        }
        return num;
    }
}

