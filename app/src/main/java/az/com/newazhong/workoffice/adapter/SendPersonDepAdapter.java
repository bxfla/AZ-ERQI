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

/**
 * Created by Administrator on 2018/7/5.
 */

public class SendPersonDepAdapter extends RecyclerView.Adapter<SendPersonDepAdapter.ViewHolder> {
    Context context;
    private CallDepBackPosition callBackPosition;
    List<String> beanList = new ArrayList<>();

    public SendPersonDepAdapter(Context context, List<String> checkList) {
        this.context = context;
        this.beanList = checkList;
    }

    public interface CallDepBackPosition {
        void onDepItemClick(int position);
    }

    public void setOnDepItemLitener(CallDepBackPosition callBackPosition) {
        this.callBackPosition = callBackPosition;
    }

    @Override
    public SendPersonDepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_senddep, parent, false);
        ViewHolder viewHolser = new ViewHolder(view);
        return viewHolser;
    }

    @Override
    public void onBindViewHolder(final SendPersonDepAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(beanList.get(position));
        holder.position = position;
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.onDepItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        int position;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
