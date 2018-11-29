package az.com.newazhong.utilsclass.person.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import az.com.newazhong.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FirstActivity extends Activity {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.activity_logo)
    LinearLayout activityLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //你需要跳转的地方的代码
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); //延迟3秒跳转
    }
}
