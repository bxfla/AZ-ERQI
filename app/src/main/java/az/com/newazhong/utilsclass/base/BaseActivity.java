package az.com.newazhong.utilsclass.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.StatusBarUtils;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.ButterKnife;


/**
 *
 * activity的基类
 */

public abstract  class BaseActivity extends AppCompatActivity implements BaseView,View.OnClickListener,Header.ClickLister {
    private AlertDialogUtil alertDialogUtil;
    private final String TAG = "MPermissions";
    private int REQUEST_CODE_PERMISSION = 0x00099;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        new StatusBarUtils().setWindowStatusBarColor(BaseActivity.this, R.color.color_bg_selected
        );
        if(isHasHeader()){
            Header header=(Header)this.findViewById(R.id.header);
            header.setClickLister(this);
        }
        initView();
    }

    @Override
    public void initView() {
        initData();
    }

    @Override
    public void showLoading(String message) {
        ProgressDialogUtil.startLoad(this,message);

    }
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialogUtil.stopLoad();

    }

    @Override
    public void showMessage(String message) {
        if(!TextUtils.isEmpty(message)){
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void toActivity(Context context,Class targetActivity) {
        Intent intent= new Intent(context,targetActivity.getClass());
        context.startActivity(intent);
    }

    @Override
    public void toActivity(Class targetActivity) {
        Intent intent= new Intent(this,targetActivity);
        this.startActivity(intent);
    }

    @Override
    public void toActivity(Class targetActivity, Object obj) {
        Intent intent=new Intent(this,targetActivity);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",obj.getClass());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showTag(String message) {
        Log.e("Tag",message);
    }

    @Override
    public void showAlertDialg(String description, AlertDialogCallBack alertDialogCallBack) {
        alertDialogUtil= new AlertDialogUtil(this);
        alertDialogUtil.showDialog(description,alertDialogCallBack);
    }

    @Override
    public void showConfirmDialog(String description) {
        alertDialogUtil= new AlertDialogUtil(this);
        alertDialogUtil.showSmallDialog(description);
    }

    @Override
    public void onClick(View view) {

    }
    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();
    /*
        是否有header(布局中是否有自定义header)
        true  有header
        false 没有header
     */
    protected  abstract  boolean isHasHeader();

    /**
     * 右部点击事件
     * @return
     */
    protected  abstract  void rightClient();

    /**
     * header头部的返回点击事件
     */
    @Override
    public void LeftClickLister() {
        finish();
    }

    /**
     * header 头部的右侧点击事件
     */
    @Override
    public void rightClickLister() {
       // finish();
        rightClient();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void closeActivity(int resultCode) {
        setResult(resultCode);
        finish();
    }

    @Override
    public void toActivity(Class targetActivity, int state, String flag) {
        Intent intent=new Intent(this,targetActivity);
        intent.putExtra(flag,state);
        startActivity(intent);
    }

    @Override
    public void toActivity(Class targetActivity, String message, String flag) {
        Intent intent=new Intent(this,targetActivity);
        intent.putExtra(flag,message);
        startActivity(intent);
    }

    @Override
    public void toActivity(Class targetActivity, String message, String flag,int state,String flag2) {
        Intent intent=new Intent(this,targetActivity);
        intent.putExtra(flag,message);
        intent.putExtra(flag2,state);
        startActivity(intent);
    }

    @Override
    public void initData() {

    }

    /**
     * 申请权限
     */
    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        alertDialogUtil.showDialog("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。", new AlertDialogCallBack() {
            @Override
            public void confirm() {
                startAppSettings();
            }

            @Override
            public void cancel() {

            }
        });
//        new AlertDialog.Builder(this)
//                .setTitle("提示信息")
//                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startAppSettings();
//                    }
//                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        Log.d(TAG, "获取权限成功=" + requestCode);

    }

    /**
     * 权限获取失败
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        Log.d(TAG, "获取权限失败=" + requestCode);
    }
}
