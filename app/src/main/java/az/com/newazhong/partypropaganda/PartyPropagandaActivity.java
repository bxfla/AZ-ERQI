package az.com.newazhong.partypropaganda;

import android.os.Bundle;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import butterknife.ButterKnife;

public class PartyPropagandaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_party_propaganda;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
