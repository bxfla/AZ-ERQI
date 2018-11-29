package az.com.newazhong.noticeandannouncement.adapter;

import android.content.Context;
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
import az.com.newazhong.noticeandannouncement.Activity.NoticeanddetailActivity;
import az.com.newazhong.noticeandannouncement.bean.ShowNotice;
import az.com.newazhong.utilsclass.base.MyApplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/2/27.
 */

public class NoticeTestAdapter extends RecyclerView.Adapter<NoticeTestAdapter.ViewHolder> {
    List<ShowNotice> showMDeptList = new ArrayList<>();
    private Context mContext;

    public void addAllData(List<ShowNotice> dataList) {
        this.showMDeptList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.showMDeptList.clear();
    }

    public NoticeTestAdapter(Context context) {
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvContent, tvTime, tvDepartment;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
        }
    }

    public NoticeTestAdapter(List<ShowNotice> showMDeptList){
        this.showMDeptList=showMDeptList;
    }

    @Override
    public NoticeTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        //recyclerView监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), NoticeanddetailActivity.class);
                ShowNotice showNotice = showMDeptList.get(position);
                intent.putExtra("bean",(Serializable)showNotice);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(NoticeTestAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(showMDeptList.get(position).title);
        holder.tvContent.setText(showMDeptList.get(position).content);
        holder.tvTime.setText(showMDeptList.get(position).time);
        holder.tvDepartment.setText(showMDeptList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return showMDeptList.size();
    }

//    List<ShowNotice> beanList = new ArrayList<>();
//
//    public NoticeAdapter(Context context, List<ShowNotice> beanNoticeList) {
//        this.context = context;
//        this.beanList = beanNoticeList;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        if (viewType == normalType) {
//            return new FristViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_notice, null));
//        } else {
//            return new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.footview, null));
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//    }
//
//    //将数据与界面进行绑定的操作
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
//
//        if (viewHolder instanceof FristViewHolder) {
//            // 绑定数据到ViewHolder上
//            FristViewHolder holderFrist = (FristViewHolder) viewHolder;
//            holderFrist.tvTitle.setText(beanList.get(position).title);
//            holderFrist.tvContent.setText(beanList.get(position).content);
//            holderFrist.tvTime.setText(beanList.get(position).time);
//            holderFrist.tvDepartment.setText(beanList.get(position).name);
////            holderFrist.mTvText.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent intent = new Intent(MyApplication.getContextObject(), ContentStudyDetailsActivity.class);
////                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
////                    intent.putExtra("valueContent", beanList.get(position));
////                    MyApplication.getContextObject().startActivity(intent);
////                }
////            });
//        } else if (viewHolder instanceof FootViewHolder) {
//            ((FootViewHolder) viewHolder).mTvText1.setVisibility(View.VISIBLE);
//            if (hasMore == true) {
//                fadeTips = false;
//                if (beanList.size() > 0) {
//                    ((FootViewHolder) viewHolder).mTvText1.setText("正在加载更多...");
//                }
//            } else {
//                if (beanList.size() > 0) {
//                    ((FootViewHolder) viewHolder).mTvText1.setText("没有更多数据了");
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            ((FootViewHolder) viewHolder).mTvText1.setVisibility(View.GONE);
//                            fadeTips = true;
//                            hasMore = true;
//                        }
//                    }, 2000);
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 根据position的不同选择不同的布局类型
//     * COMMENT_FIRST = 1; 类型1
//     * COMMENT_SECOND = 2; 类型2
//     */
//    @Override
//    public int getItemViewType(int position) {
//        if (position == getItemCount() - 1) {
//            return footType;
//        } else {
//            return normalType;
//        }
//    }
//
//    //获取数据的数量
//    @Override
//    public int getItemCount() {
//        return beanList.size()+1;
//    }
//
//    // 自定义方法，获取列表中数据源的最后一个位置，比getItemCount少1，因为不计上footView
//    public int getRealLastPosition() {
//        return beanList.size();
//    }
//
//    //FristViewHolder，持有每个Item的的所有界面元素
//    public static class FristViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvTitle,tvContent,tvTime,tvDepartment;
//
//        public FristViewHolder(View itemView) {
//            super(itemView);
//            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
//            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
//            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
//            tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
//        }
//    }
//
//    //foot布局
//    public static class FootViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView mTvText1;
//
//        public FootViewHolder(View itemView) {
//            super(itemView);
//            mTvText1 = (TextView) itemView.findViewById(R.id.tips);
//        }
//    }
//
//    public static interface OnRecyclerViewItemClickListener {
//        void onItemClick(View view, String mDataset);
//    }
//
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
//        mOnItemClickListener = listener;
//    }
//
//    //重置adapter的数据源
//    public void resetDatas() {
//        beanList = new ArrayList<>();
//    }
//
//    //刷新后首页展示的数据
//    public void updateList(List<ShowNotice> newDatas, boolean hasMore) {
//        if (newDatas != null) {
//            beanList.addAll(newDatas);
//        }
//        this.hasMore = hasMore;
//        notifyDataSetChanged();
//    }
//
//    //下拉刷新是否隐藏底部footer
//    public boolean isFadeTips() {
//        return fadeTips;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//    }
}