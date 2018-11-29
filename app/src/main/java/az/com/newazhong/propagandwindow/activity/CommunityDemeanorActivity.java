package az.com.newazhong.propagandwindow.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.Fragment_Adapter;
import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.fragment.FragmentContent;
import az.com.newazhong.propagandwindow.fragment.FragmentPhoto;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.CustomViewPager;
import az.com.newazhong.utilsclass.myViews.Header;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CommunityDemeanorActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rbPhoto)
    RadioButton rbPhoto;
    @Bind(R.id.rbContent)
    RadioButton rbContent;
    @Bind(R.id.radionGroup)
    RadioGroup radionGroup;
    @Bind(R.id.customViewPage)
    CustomViewPager customViewPage;

    public Fragment fragment01, fragment02;
    List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        fragment01 = new FragmentPhoto();
        fragment02 = new FragmentContent();
        list.add(fragment01);
        list.add(fragment02);
        radionGroup.setOnCheckedChangeListener(this);
        customViewPage.setAdapter(new Fragment_Adapter(getSupportFragmentManager(), list));
        customViewPage.setCurrentItem(0,false);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_community_demeanor;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbPhoto:
                customViewPage.setCurrentItem(0, false);
                break;
            case R.id.rbContent:
                customViewPage.setCurrentItem(1, false);
                break;
        }
    }
}
