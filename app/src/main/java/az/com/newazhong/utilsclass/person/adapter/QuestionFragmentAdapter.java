package az.com.newazhong.utilsclass.person.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.street12345.activity.StreetDetailActivity;
import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.RichTextView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by dell on 2018/3/31.
 */

public class QuestionFragmentAdapter extends RecyclerView.Adapter<QuestionFragmentAdapter.ViewHolder> {
    List<Street.DataBean.ContentBean> showMDeptList = new ArrayList<>();
    Context context;
    String tag;
    String img01;

    public QuestionFragmentAdapter(Context context, List<Street.DataBean.ContentBean> beanList, String tag) {
        this.context = context;
        this.showMDeptList = beanList;
        this.tag = tag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_questionfragment, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvName.setText(showMDeptList.get(position).getSponsorName());
        holder.tvTime.setText(showMDeptList.get(position).getCreateTime());
        holder.tvContent.setText(showMDeptList.get(position).getDescription());
        String type = showMDeptList.get(position).getMatterState();
        String img = String.valueOf(showMDeptList.get(position).getPictures());
        img01 = img.substring(1, img.indexOf("]")).trim();
        String[] temp = null;
        if (img.indexOf(",") != -1) {
            temp = img.split(",");
            Glide.with(context).load(temp[0]).placeholder(R.drawable.loding).into(holder.photo1);
            String img1 = temp[1].trim();
            Glide.with(context).load(img1).placeholder(R.drawable.loding).into(holder.photo2);
        } else {
            Glide.with(context).load(img01).placeholder(R.drawable.loding).into(holder.photo1);
        }
        if (type.equals("UNACCEPTED")) {
            holder.imageView01.setImageResource(R.drawable.resulting);
            holder.imageView02.setImageResource(R.drawable.result);
            holder.imageView03.setImageResource(R.drawable.result);
            holder.textView01.setTextColor(context.getResources().getColor(R.color.income_green));
            holder.textView02.setTextColor(context.getResources().getColor(R.color.xiaohei));
            holder.textView03.setTextColor(context.getResources().getColor(R.color.xiaohei));
        } else if (type.equals("TERMINATED")) {
            holder.textView01.setTextColor(context.getResources().getColor(R.color.xiaohei));
            holder.textView02.setTextColor(context.getResources().getColor(R.color.xiaohei));
            holder.textView03.setTextColor(context.getResources().getColor(R.color.income_green));
            holder.imageView01.setImageResource(R.drawable.result);
            holder.imageView02.setImageResource(R.drawable.result);
            holder.imageView03.setImageResource(R.drawable.resulting);
        } else {
            holder.textView01.setTextColor(context.getResources().getColor(R.color.xiaohei));
            holder.textView03.setTextColor(context.getResources().getColor(R.color.xiaohei));
            holder.textView02.setTextColor(context.getResources().getColor(R.color.income_green));
            //if (type.equals("IN_PROCESS")||type.equals("PROCESSED"))
            holder.imageView01.setImageResource(R.drawable.result);
            holder.imageView02.setImageResource(R.drawable.resulting);
            holder.imageView03.setImageResource(R.drawable.result);
        }
        holder.tvAddress.setText(showMDeptList.get(position).getGpsAddress());
        holder.imageView01.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContextObject(), StreetDetailActivity.class);
                Street.DataBean.ContentBean showActivityPhoto = showMDeptList.get(position);
                intent.putExtra("bean", (Serializable) showActivityPhoto);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContextObject().startActivity(intent);
            }
        });
        if (tag.equals("no")) {
            holder.linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return showMDeptList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvTime, tvAddress, textView01, textView02, textView03;
        private RichTextView tvContent;
        private ImageView imageView01, imageView02, imageView03, photo1, photo2;
        private LinearLayout linearLayout;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (RichTextView) itemView.findViewById(R.id.tvContent);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            textView01 = (TextView) itemView.findViewById(R.id.textView01);
            textView02 = (TextView) itemView.findViewById(R.id.textView02);
            textView03 = (TextView) itemView.findViewById(R.id.textView03);
            imageView01 = (ImageView) itemView.findViewById(R.id.imageView01);
            imageView02 = (ImageView) itemView.findViewById(R.id.imageView02);
            imageView03 = (ImageView) itemView.findViewById(R.id.imageView03);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_liucheng);
            photo1 = (ImageView) itemView.findViewById(R.id.photo1);
            photo2 = (ImageView) itemView.findViewById(R.id.photo2);
            this.itemView = itemView;
        }
    }
}
