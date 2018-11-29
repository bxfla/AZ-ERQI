package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VacationalActivity extends BaseActivity {

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
        return R.layout.activity_vacational;
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
                intent = new Intent(VacationalActivity.this,PropagandaActivity.class);
                intent.putExtra("type","法律法规");
                intent.putExtra("tag","111");
                startActivity(intent);
                break;
            case R.id.rbClassStudy:
                intent = new Intent(VacationalActivity.this,PropagandaActivity.class);
                intent.putExtra("type","党建宣传");
                intent.putExtra("tag","222");
                startActivity(intent);
                break;
            case R.id.rbPropyActivity:
                intent = new Intent(VacationalActivity.this,PropagandaActivity.class);
                intent.putExtra("type","部门组织培训");
                intent.putExtra("tag","333");
                startActivity(intent);
                break;
        }
    }
}
