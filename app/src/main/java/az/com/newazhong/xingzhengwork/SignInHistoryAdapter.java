package az.com.newazhong.xingzhengwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.xingzhengwork.bean.SignInHistory;

/**
 * Created by Administrator on 2018/9/12.
 */

public class SignInHistoryAdapter extends RecyclerView.Adapter<SignInHistoryAdapter.ViewHolder> {
    Context context;
    List<SignInHistory.DataBean> beanList = new ArrayList<>();
    public SignInHistoryAdapter(Context context, List<SignInHistory.DataBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public SignInHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_signhistory,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SignInHistoryAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(beanList.get(position).getName());
        holder.tvTime.setText(beanList.get(position).getRecordTime());
        holder.tvAddress.setText(beanList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvTime, tvAddress;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }
}
