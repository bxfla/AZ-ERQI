package az.com.newazhong.propagandwindow.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.activity.CommunityDetailActivity;
import az.com.newazhong.propagandwindow.bean.ShowActivityPhoto;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/2/27.
 */

public class ActivityContentAdapter extends RecyclerView.Adapter<ActivityContentAdapter.ViewHolder> {
    List<ShowActivityPhoto> beanList = new ArrayList<>();
    public GetItemPosition getItemPosition;


    public interface GetItemPosition {
        void getPosition(int position);
    }

    public void getItemPositionView(GetItemPosition getItemPosition) {
        this.getItemPosition = getItemPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvDepartment, tvYY;
        private RichTextView tvContent;
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

    public ActivityContentAdapter(List<ShowActivityPhoto> beanList) {
        this.beanList = beanList;
    }

    @Override
    public ActivityContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), CommunityDetailActivity.class);
                ShowActivityPhoto showActivityPhoto = beanList.get(position);
                intent.putExtra("bean", (Serializable) showActivityPhoto);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        holder.tvYY.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               int position = holder.getAdapterPosition();
                                               if (getItemPosition != null) {
                                                   getItemPosition.getPosition(position);
                                               }
                                           }

                                           ;
//                Intent intent = new Intent(MyApplication.getContextObject(), CommunityDetailActivity.class);
//                ShowActivityPhoto showActivityPhoto = beanList.get(position);
//                intent.putExtra("bean",(Serializable)showActivityPhoto);
//                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getContextObject().startActivity(intent);
//            new
//
//            AlertDialogUtil(MyApplication.getContextObject()
//
//            ).
//
//            showDialog("您确定要预约吗",new AlertDialogCallBack() {
//                @Override
//                public void confirm () {
//                    Toast.makeText(MyApplication.getContextObject(), "XXX", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void cancel () {
//
//                }
//            }
//
//            );
//        }
                                       }

        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ActivityContentAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(beanList.get(position).title);
        holder.tvContent.setHtml1(beanList.get(position).content, 500);
        holder.tvTime.setText(beanList.get(position).time);
        holder.tvDepartment.setText(beanList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }
}
