package az.com.newazhong.workoffice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.workoffice.bean.SendPerson;

/**
 * Created by Administrator on 2018/7/5.
 */

public class SendPersonAdapter extends RecyclerView.Adapter<SendPersonAdapter.ViewHolder> {
    Context context;
    private CallBackPosition callBackPosition;
    List<SendPerson> beanList = new ArrayList<>();

    public SendPersonAdapter(Context context, List<SendPerson> checkList) {
        this.context = context;
        this.beanList = checkList;
    }

    public interface CallBackPosition {
        void onItemClick(int position);
    }

    public void setOnItemLitener(CallBackPosition callBackPosition) {
        this.callBackPosition = callBackPosition;
    }

    @Override
    public SendPersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sendperson, parent, false);
        ViewHolder viewHolser = new ViewHolder(view);
        return viewHolser;
    }

    @Override
    public void onBindViewHolder(final SendPersonAdapter.ViewHolder holder, final int position) {
        holder.tvName.setText(beanList.get(position).getName());
        holder.textView.setText(beanList.get(position).getdName());
        holder.position = position;
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox tvName;
        private TextView textView;
        int position;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = (CheckBox) itemView.findViewById(R.id.tvName);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
