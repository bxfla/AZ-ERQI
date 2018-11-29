package az.com.newazhong.xingzhengwork;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.Fragment_Adapter;
import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.NoScrollViewPager;
import az.com.newazhong.xingzhengwork.fragment.FragmentSignIn;
import az.com.newazhong.xingzhengwork.fragment.FragmentSignInHistory;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivityTest extends BaseActivity implements  RadioGroup.OnCheckedChangeListener {


    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rbSignin)
    RadioButton rbSignin;
    @Bind(R.id.rbSignHistory)
    RadioButton rbSignHistory;
    @Bind(R.id.radionGroup)
    RadioGroup radionGroup;
    @Bind(R.id.noScrollViewPager)
    NoScrollViewPager noScrollViewPager;

    public Fragment fragment01, fragment02;
    List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        fragment01 = new FragmentSignIn();
        fragment02 = new FragmentSignInHistory();
        list.add(fragment01);
        list.add(fragment02);
        radionGroup.setOnCheckedChangeListener(this);
        noScrollViewPager.setAdapter(new Fragment_Adapter(getSupportFragmentManager(), list));
        noScrollViewPager.setCurrentItem(0,false);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_signin;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rbSignin:
                noScrollViewPager.setCurrentItem(0, false);
                break;
            case R.id.rbSignHistory:
                noScrollViewPager.setCurrentItem(1, false);
                break;
        }
    }
}