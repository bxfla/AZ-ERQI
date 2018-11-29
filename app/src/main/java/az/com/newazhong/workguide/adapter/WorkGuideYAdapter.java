package az.com.newazhong.workguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.workguide.bean.WorkGuideList;

/**
 * Created by Administrator on 2018/6/26.
 */

public class WorkGuideYAdapter extends RecyclerView.Adapter<WorkGuideYAdapter.ViewHolder> {

    Context context;
    List<WorkGuideList.DataBean.ContentBean.AuditMaterialsBean> beanList = new ArrayList<>();
    public GetItemPosition getItemPosition;
    String userType,type;

    public interface GetItemPosition {
        void getPosition(int position, String tag);
    }

    public void setOnInnerItemOnClickListener(GetItemPosition getItemPosition){
        this.getItemPosition=getItemPosition;
    }

    public WorkGuideYAdapter(Context context, List<WorkGuideList.DataBean.ContentBean.AuditMaterialsBean> anquanList,String userType
            ,String type) {
        this.context = context;
        beanList = anquanList;
        this.userType = userType;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_workguidey, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        if (type.equals("true")){
//            holder.tvGet.setText("审核中");
//            holder.tvGet.setEnabled(true);
//            holder.tvGet.setBackgroundColor(context.getResources().getColor(R.color.balance_gray));
//            holder.tvName.setText(beanList.get(position).getName());
//        }else {
//            holder.tvGet.setText("上传照片");
//            holder.tvGet.setEnabled(false);
//            holder.tvGet.setBackgroundColor(context.getResources().getColor(R.color.color_bg_selected));
            holder.tvName.setText(beanList.get(position).getName());
            holder.position =position;
            holder.tvGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getItemPosition.getPosition(position,"photo");
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        ImageView imageView;
        public Button tvGet;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvGet = (Button) itemView.findViewById(R.id.tvGet);
            imageView = (ImageView) itemView.findViewById(R.id.imaheView);
        }
    }
}
