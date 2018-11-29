package az.com.newazhong.propagandwindow.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.activity.WorkPropagandDetailActivity;
import az.com.newazhong.propagandwindow.bean.ShowPropagandWindow;
import az.com.newazhong.propagandwindow.utils.MessageEvent;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/2/27.
 */

public class PropagandWindowAdapter extends RecyclerView.Adapter<PropagandWindowAdapter.ViewHolder> {
    List<ShowPropagandWindow> showMDeptList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvDepartment,tvYY;
        public RichTextView tvContent;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (RichTextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
            tvYY = (TextView) itemView.findViewById(R.id.tvYY);
        }
    }

    public PropagandWindowAdapter(List<ShowPropagandWindow> showMDeptList){
        this.showMDeptList=showMDeptList;
    }

    @Override
    public PropagandWindowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ShowPropagandWindow showPropagandWindow = showMDeptList.get(position);
                Intent intent = new Intent(MyApplication.getContextObject(), WorkPropagandDetailActivity.class);
                EventBus.getDefault().post(new MessageEvent(showPropagandWindow.getContent()));
                intent.putExtra("bean", (Serializable) showPropagandWindow);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(PropagandWindowAdapter.ViewHolder holder, int position) {
        if (showMDeptList.get(position).title!=null&&showMDeptList.get(position).title.length()!=0){
            holder.tvTitle.setText(showMDeptList.get(position).title);
        }
        if (showMDeptList.get(position).content!=null&&showMDeptList.get(position).content.length()!=0){
            holder.tvContent.setHtml1(showMDeptList.get(position).content,500);
        }
        if (showMDeptList.get(position).time!=null&&showMDeptList.get(position).time.length()!=0){
            holder.tvTime.setText(showMDeptList.get(position).time);
        }
        if (showMDeptList.get(position).name!=null&&showMDeptList.get(position).name.length()!=0){
            holder.tvDepartment.setText(showMDeptList.get(position).name);
        }
        holder.tvYY.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return showMDeptList.size();
    }

}
