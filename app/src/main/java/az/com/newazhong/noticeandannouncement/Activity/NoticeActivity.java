package az.com.newazhong.noticeandannouncement.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.Fragment_Adapter;
import az.com.newazhong.R;
import az.com.newazhong.noticeandannouncement.fragment.FragmentAnnouncemen;
import az.com.newazhong.noticeandannouncement.fragment.FragmentCtalog;
import az.com.newazhong.noticeandannouncement.fragment.FragmentPolicy;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.NoScrollViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NoticeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rbPolicy)
    RadioButton rbPolicy;
    @Bind(R.id.rbCtalog)
    RadioButton rbCtalog;
    @Bind(R.id.rbAnnouncemen)
    RadioButton rbAnnouncemen;
    @Bind(R.id.radionGroup)
    RadioGroup radionGroup;
    @Bind(R.id.noScrollViewPager)
    NoScrollViewPager noScrollViewPager;

    public Fragment fragment01, fragment02, fragment03;
    List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        fragment01 = new FragmentPolicy();
        fragment02 = new FragmentCtalog();
        fragment03 = new FragmentAnnouncemen();
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
        radionGroup.setOnCheckedChangeListener(this);
        noScrollViewPager.setAdapter(new Fragment_Adapter(getSupportFragmentManager(), list));
        noScrollViewPager.setCurrentItem(0,false);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_notice;
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
            case R.id.rbPolicy:
                noScrollViewPager.setCurrentItem(0, false);
                break;
            case R.id.rbCtalog:
                noScrollViewPager.setCurrentItem(1, false);
                break;
            case R.id.rbAnnouncemen:
                noScrollViewPager.setCurrentItem(2, false);
                break;
        }
    }
}
