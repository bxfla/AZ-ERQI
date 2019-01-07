package az.com.newazhong.utilsclass.person.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.AlertDialogCallBack;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.bean.Version;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.fragment.Fragment0;
import az.com.newazhong.utilsclass.person.fragment.Fragment1;
import az.com.newazhong.utilsclass.person.fragment.Fragment2;
import az.com.newazhong.utilsclass.presenter.VersionPresenter;
import az.com.newazhong.utilsclass.presenter.presenterimpl.VersionPresenterimpl;
import az.com.newazhong.utilsclass.utils.ActivityCollector;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import az.com.newazhong.utilsclass.view.VersionView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements VersionView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.rb0)
    RadioButton rb0;
    @Bind(R.id.rb1)
    RadioButton rb1;
    @Bind(R.id.rb2)
    RadioButton rb2;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    AlertDialogUtil alertDialogUtil;
    VersionPresenter presenter;
    public Fragment fragment01, fragment02, fragment03;
    private static boolean isExit = false;
    List<Fragment> list = new ArrayList<Fragment>();
    Fragment mContent;
    String tag;
    //推出程序
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        new SharedPreferencesHelper(this, "fragment_tag").saveData(this, "fragment_tag", "1");
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        presenter = new VersionPresenterimpl(this, this);
        presenter.getVersionPresenter("ANDROID");
    }

    //推出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.rb0:
//                fragmentContainer.setCurrentItem(0, false);
//                new SharedPreferencesHelper(this,"fragment_tag").saveData(this,"fragment_tag","1");
//                break;
//            case R.id.rb1:
//                fragmentContainer.setCurrentItem(1, false);
//                new SharedPreferencesHelper(this,"fragment_tag").saveData(this,"fragment_tag","2");
//                break;
//            case R.id.rb2:
//                fragmentContainer.setCurrentItem(2, false);
//                new SharedPreferencesHelper(this,"fragment_tag").saveData(this,"fragment_tag","3");
//                break;
//        }
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    // 获取软件版本号
    private int getVersionCode() {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = MainActivity.this.getPackageManager()
                    .getPackageInfo(MainActivity.this.getApplicationInfo().packageName, 0).versionCode;
            Log.e("updateversionmanager",
                    "versioncode=" + String.valueOf(versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public void getVersionView(Version version) {
        int version1 = getVersionCode();
        int newVersion = version.getData().getVersion();
        final String path = version.getData().getUrl();
        if (version1 != newVersion && newVersion > version1) {
            new AlertDialogUtil(MainActivity.this).showDialog("最新版本" + newVersion + ".1.0", new AlertDialogCallBack() {
                @Override
                public void confirm() {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(path);//此处填链接
                    intent.setData(content_url);
                    startActivity(intent);
                }

                @Override
                public void cancel() {
                    MainActivity.this.finish();
                }
            });
        } else {
            srl.setVisibility(View.GONE);
            fragment01 = new Fragment0();
            fragment02 = new Fragment1();
            fragment03 = new Fragment2();
            startFragmentAdd(fragment01);
//            list.add(fragment01);
//            list.add(fragment02);
//            list.add(fragment03);
//            mainRg.setOnCheckedChangeListener(this);
//            fragmentContainer.setAdapter(new Fragment_Adapter(getSupportFragmentManager(), list));
//            if (tag != null && tag.equals("1")) {
//                fragmentContainer.setCurrentItem(1, false);
//                rb1.setChecked(true);
//            } else {
//                fragmentContainer.setCurrentItem(0, false);
//            }
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                finish();
                break;
        }
    }

    @OnClick({R.id.rb0, R.id.rb1, R.id.rb2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb0:
                startFragmentAdd( fragment01);
                break;
            case R.id.rb1:
                startFragmentAdd( fragment02);
                break;
            case R.id.rb2:
                startFragmentAdd( fragment03);
                break;
            default:
                break;
        }
    }

    // fragment的切换
    private void startFragmentAdd(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        if (mContent == null) {
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();
            mContent = fragment;
        }
        if (mContent != fragment) {
            // 先判断是否被add过
            if (!fragment.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                fragmentTransaction.hide(mContent).add(R.id.fragment_container, fragment).commit();
            } else {
                // 隐藏当前的fragment，显示下一个
                fragmentTransaction.hide(mContent).show(fragment).commit();
            }
            mContent = fragment;
        }
    }
}
