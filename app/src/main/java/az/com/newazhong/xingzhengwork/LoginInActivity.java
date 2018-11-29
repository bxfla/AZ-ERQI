package az.com.newazhong.xingzhengwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginInActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.btn_signin)
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login_in;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick(R.id.btn_signin)
    public void onViewClicked() {
        Intent intent =  new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
