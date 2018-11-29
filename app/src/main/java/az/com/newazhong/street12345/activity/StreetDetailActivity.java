package az.com.newazhong.street12345.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import az.com.newazhong.R;
import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class StreetDetailActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.textView01)
    TextView textView01;
    @Bind(R.id.textView02)
    TextView textView02;
    @Bind(R.id.textView03)
    TextView textView03;
    @Bind(R.id.imageView01)
    ImageView imageView01;
    @Bind(R.id.imageView02)
    ImageView imageView02;
    @Bind(R.id.imageView03)
    ImageView imageView03;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvContent)
    RichTextView tvContent;
    String type;
    @Bind(R.id.photo1)
    ImageView photo1;
    @Bind(R.id.photo2)
    ImageView photo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Street.DataBean.ContentBean bean = (Street.DataBean.ContentBean) intent.getSerializableExtra("bean");
        tvName.setText(bean.getSponsorName());
        tvTime.setText(bean.getCreateTime());
//        URLImageParser imageGetter = new URLImageParser(tvContent);
//        tvContent.setText(Html.fromHtml(bean.getContent(), imageGetter, null));
        String img = String.valueOf(bean.getPictures());
        img=img.substring(1,img.indexOf("]"));
        String[] temp = null;
        if (img.indexOf(",") != -1) {
            temp = img.split(",");
            Glide.with(this).load(temp[0]).placeholder(R.drawable.loding).into(photo1);
            Glide.with(this).load(temp[1]).placeholder(R.drawable.loding).into(photo2);
        }else {
            Glide.with(this).load(img).placeholder(R.drawable.loding).into(photo1);
        }
        tvContent.setHtml(bean.getDescription(), 500);
        type = bean.getMatterState();
        if (type.equals("UNACCEPTED")) {
            imageView01.setImageResource(R.drawable.resulting);
            imageView02.setImageResource(R.drawable.result);
            imageView03.setImageResource(R.drawable.result);
            textView01.setTextColor(this.getResources().getColor(R.color.income_green));
            textView02.setTextColor(this.getResources().getColor(R.color.xiaohei));
            textView03.setTextColor(this.getResources().getColor(R.color.xiaohei));
        } else if (type.equals("TERMINATED")) {
            textView01.setTextColor(this.getResources().getColor(R.color.xiaohei));
            textView02.setTextColor(this.getResources().getColor(R.color.xiaohei));
            textView03.setTextColor(this.getResources().getColor(R.color.income_green));
            imageView01.setImageResource(R.drawable.result);
            imageView02.setImageResource(R.drawable.result);
            imageView03.setImageResource(R.drawable.resulting);
        } else {
            textView01.setTextColor(this.getResources().getColor(R.color.xiaohei));
            textView03.setTextColor(this.getResources().getColor(R.color.xiaohei));
            textView02.setTextColor(this.getResources().getColor(R.color.income_green));
            //if (type.equals("IN_PROCESS")||type.equals("PROCESSED"))
            imageView01.setImageResource(R.drawable.result);
            imageView02.setImageResource(R.drawable.resulting);
            imageView03.setImageResource(R.drawable.result);
        }

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_street_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
