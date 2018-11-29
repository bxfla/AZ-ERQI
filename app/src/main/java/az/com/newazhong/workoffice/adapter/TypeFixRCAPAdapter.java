package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.regex.Pattern;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.workoffice.activity.TypeFixTypeRCAPActivity;
import az.com.newazhong.workoffice.bean.TypeFixRCAP;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2018/10/26.
 */

public class TypeFixRCAPAdapter extends RecyclerView.Adapter<TypeFixRCAPAdapter.ViewHolder> {
    Context context;
    TypeFixRCAP bean;
    public TypeFixRCAPAdapter(Context context, TypeFixRCAP bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public TypeFixRCAPAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rcap,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), TypeFixTypeRCAPActivity.class);
                TypeFixRCAP.DataBean.ContentBean showNotice = bean.getData().getContent().get(position);
                intent.putExtra("bean",(Serializable)showNotice);
                intent.putExtra("header","日常安排");
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(TypeFixRCAPAdapter.ViewHolder holder, int position) {
        holder.tvContent.setHtml1(bean.getData().getContent().get(position).getContent(),500);
        holder.tvTime.setText(bean.getData().getContent().get(position).getCreateTime());
        Pattern pen = Pattern.compile(" ");
        String[] temp = pen.split(bean.getData().getContent().get(position).getStartDate());
        String[] temp1 = pen.split(bean.getData().getContent().get(position).getEndDate());
        holder.tvTitle.setText(temp[0]+"\t\t"+"至"+" \t\t"+temp1[0]+" \t\t"+"日程安排");
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
