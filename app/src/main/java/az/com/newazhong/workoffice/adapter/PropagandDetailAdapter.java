package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.workoffice.activity.PropagandDetailActivity;
import az.com.newazhong.workoffice.bean.DownloadFile;

/**
 * Created by Administrator on 2018/11/3.
 */

public class PropagandDetailAdapter extends RecyclerView.Adapter<PropagandDetailAdapter.ViewHolder> {
    Context context;
    List<DownloadFile> fileList = new ArrayList<>();
    private CallBackPosition callBackPosition;

    public interface CallBackPosition {
        void onItemClick(int position);
    }
    public void setOnItemLitener(CallBackPosition callBackPosition) {
        this.callBackPosition = callBackPosition;
    }

    public PropagandDetailAdapter(PropagandDetailActivity propagandDetailActivity, List<DownloadFile> fileList) {
        this.context = propagandDetailActivity;
        this.fileList = fileList;
    }

    @Override
    public PropagandDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_propaganddetail,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PropagandDetailAdapter.ViewHolder holder, final int position) {
        //holder.tvName.setText(fileList.get(position).getName());
        holder.tvValue.setText(fileList.get(position).getName());
        holder.tvValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvValue;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
        }
    }
}
