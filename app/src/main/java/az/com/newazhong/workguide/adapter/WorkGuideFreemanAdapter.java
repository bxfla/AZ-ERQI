package az.com.newazhong.workguide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.workguide.activity.WorkGuideListActivity;
import az.com.newazhong.workguide.bean.WorkGuide;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by dell on 2018/2/28.
 */

public class WorkGuideFreemanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<WorkGuide.DataBean.ContentBean> beanFreemanList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();
    Context context;

    public WorkGuideFreemanAdapter(List<WorkGuide.DataBean.ContentBean> beanFreemanList) {
        this.beanFreemanList = beanFreemanList;
        imageList.add(R.drawable.workguidefreeman_hysy);
        imageList.add(R.drawable.workguidefreeman_hjsf);
        imageList.add(R.drawable.workguidefreeman_jypx);
        imageList.add(R.drawable.workguidefreeman_ldjy);
        imageList.add(R.drawable.workguidefreeman_zfbz);
        imageList.add(R.drawable.workguidefreeman_zyzg);
        imageList.add(R.drawable.workguidefreeman_shbz);
        imageList.add(R.drawable.workguidefreeman_nsjf);
        imageList.add(R.drawable.workguidefreeman_ylws);
        imageList.add(R.drawable.workguidefreeman_jtly);
        imageList.add(R.drawable.workguidefreeman_cjrj);
        imageList.add(R.drawable.workguidefreeman_shfw);
        imageList.add(R.drawable.workguidefreeman_mzzj);
        imageList.add(R.drawable.workguidefreeman_snfw);
        imageList.add(R.drawable.workguidefreeman_swbz);
        imageList.add(R.drawable.workguidefreeman_qt);
    }

    public static class FristViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        View itemsView;

        public FristViewHolder(View itemView) {
            super(itemView);
            itemsView = itemView;
            textView= (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FristViewHolder(LayoutInflater.from(MyApplication.getContextObject()).inflate(R.layout.adapter_workguide_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final WorkGuide.DataBean.ContentBean bean = beanFreemanList.get(position);
        FristViewHolder holderFrist = (FristViewHolder) holder;
        holderFrist.textView.setText(bean.getName());
        holderFrist.imageView.setImageResource(imageList.get(position));
        holderFrist.itemsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContextObject(), WorkGuideListActivity.class);
                intent.putExtra("name",bean.getName());
                intent.putExtra("guideCategoryId",bean.getId());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanFreemanList.size();
    }
}
