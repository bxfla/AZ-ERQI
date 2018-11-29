package az.com.newazhong.partyBuildingPropaganda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.workoffice.activity.PropagandaActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropagListActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rbPropStydy)
    RadioButton rbPropStydy;
    @Bind(R.id.rbClassStudy)
    RadioButton rbClassStudy;
    @Bind(R.id.rbPropyActivity)
    RadioButton rbPropyActivity;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_propag_list;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({R.id.rbPropStydy, R.id.rbClassStudy, R.id.rbPropyActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbPropStydy:
                intent = new Intent(PropagListActivity.this,PropagandaActivity.class);
                intent.putExtra("type","党员教育");
                intent.putExtra("tag","11");
                startActivity(intent);
                break;
            case R.id.rbClassStudy:
                intent = new Intent(PropagListActivity.this,PropagandaActivity.class);
                intent.putExtra("type","干部学习");
                intent.putExtra("tag","22");
                startActivity(intent);
                break;
            case R.id.rbPropyActivity:
                intent = new Intent(PropagListActivity.this,PropagandaActivity.class);
                intent.putExtra("type","组织活动");
                intent.putExtra("tag","33");
                startActivity(intent);
                break;
        }
    }
}
