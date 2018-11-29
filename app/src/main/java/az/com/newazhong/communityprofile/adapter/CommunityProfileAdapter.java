package az.com.newazhong.communityprofile.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.communityprofile.activity.CommunityProfileDetailsActivity;
import az.com.newazhong.communityprofile.bean.ShowMDept;
import az.com.newazhong.utilsclass.base.MyApplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/3/23.
 */

public class CommunityProfileAdapter extends RecyclerView.Adapter<CommunityProfileAdapter.ViewHolder> {

    List<ShowMDept> showMDeptList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public CommunityProfileAdapter(List<ShowMDept> showMDeptList){
        this.showMDeptList=showMDeptList;
    }

    @Override
    public CommunityProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mdept,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), CommunityProfileDetailsActivity.class);
                ShowMDept showMDept = showMDeptList.get(position);
                intent.putExtra("bean",showMDept);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CommunityProfileAdapter.ViewHolder holder, int position) {
        ShowMDept showMDept = showMDeptList.get(position);
        holder.tvTitle.setText(showMDept.getListTitle());
    }

    @Override
    public int getItemCount() {
        return showMDeptList.size();
    }
}
