package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;

/**
 * Created by Administrator on 2018/10/26.
 */

public class WorkOfficeListAdapter extends RecyclerView.Adapter<WorkOfficeListAdapter.ViewHolder> {
    Context context;
    List<String> dataList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public WorkOfficeListAdapter(Context context, List<String> nameList, List<Integer> imageList) {
        this.context = context;
        this.dataList = nameList;
        this.imageList = imageList;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public WorkOfficeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_workoffice,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkOfficeListAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.textView.setText(dataList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
