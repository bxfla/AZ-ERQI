package az.com.newazhong.propagandwindow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyPropagandaWinActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.btnWorkTrends)
    Button btnWorkTrends;
    @Bind(R.id.btnCommunityMien)
    Button btnCommunityMien;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_partypropaganda;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({R.id.btnWorkTrends, R.id.btnCommunityMien})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnWorkTrends:
                intent = new Intent(this,WorkTrendsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCommunityMien:
                intent = new Intent(this,CommunityDemeanorActivity.class);
                startActivity(intent);
                break;
        }
    }
}
