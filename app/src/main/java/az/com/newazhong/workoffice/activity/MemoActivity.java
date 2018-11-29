package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemoActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rbCommunityProfile)
    RadioButton rbCommunityProfile;
    @Bind(R.id.rbWorkStaty)
    RadioButton rbWorkStaty;

    String noticeUrl = Constant.BASE_URL+"notice"+"?type="+"MEMO_NOTICE";
    String noticeUr2 = Constant.BASE_URL+"notice"+"?type="+"METTING_NOTICE";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_memo;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({R.id.rbCommunityProfile, R.id.rbWorkStaty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbCommunityProfile:
                intent = new Intent(this,TypeFixActivity.class);
                intent.putExtra("type","会议通知");
                startActivity(intent);
                break;
            case R.id.rbWorkStaty:
                intent = new Intent(this,TypeFixActivity.class);
                intent.putExtra("type","内部通知");
                startActivity(intent);
                break;
        }
    }
}
