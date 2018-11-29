package az.com.newazhong.propagandwindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;

/**
 * Created by dell on 2018/2/26.
 */

public class ActivityPhotoAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<String> beanList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    Context context;

    public ActivityPhotoAdapter(Context context, List<String> beanList,List<String> titleList) {
        this.context = context;
        this.beanList = beanList;
        this.titleList = titleList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_gallery_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            Glide.with(context).load(beanList.get(position)).into(holder.imageView);
            holder.textView.setText(titleList.get(position));
            return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
