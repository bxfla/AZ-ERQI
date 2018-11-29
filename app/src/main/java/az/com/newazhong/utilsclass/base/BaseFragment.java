package az.com.newazhong.utilsclass.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import butterknife.ButterKnife;

/**
 * 自定义alterDialog提示框
 */

public abstract class BaseFragment extends Fragment implements BaseView,View.OnClickListener {
    private AlertDialogUtil alertDialogUtil;
    View mView=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getResourceId(),null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
        initData();
    }

    //获取view资源id
    protected  abstract int getResourceId();


    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void toActivity(Context context, Class targetActivity) {

    }

    @Override
    public void toActivity(Class targetActivity,Object obj) {
        Intent intent=new Intent(getActivity(),targetActivity);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",obj.getClass());
        intent.putExtras(bundle);
        startActivity(intent);

    }
    @Override
    public void toActivity(Class targetActivity,int state,String flag) {
        Intent intent=new Intent(getActivity(),targetActivity);
        intent.putExtra(flag,state);
        startActivity(intent);

    }
    @Override
    public void toActivity(Class targetActivity, String message, String flag) {
        Intent intent=new Intent(getActivity(),targetActivity);
        intent.putExtra(flag,message);
        startActivity(intent);
    }
    @Override
    public void toActivity(Class targetActivity, String message, String flag,int state,String flag2) {
        Intent intent=new Intent(getActivity(),targetActivity);
        intent.putExtra(flag,message);
        intent.putExtra(flag2,state);
        startActivity(intent);
    }
    @Override
    public void toActivity(Class targetActivity) {
        Intent intent=new Intent(getActivity(), targetActivity);
        startActivity(intent);

    }

    @Override
    public void showTag(String message) {

    }

    @Override
    public void showAlertDialg(String description,AlertDialogCallBack alertDialogCallBack) {
         alertDialogUtil= new AlertDialogUtil(getActivity());
         alertDialogUtil.showDialog(description,alertDialogCallBack);
    }

    @Override
    public void showConfirmDialog(String description) {
        alertDialogUtil= new AlertDialogUtil(getActivity());
        alertDialogUtil.showSmallDialog(description);
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 在oncreate中需要初始化的东西
     * 比如注册广播
     */
    public  void init(){

    }
    @Override
    public void initData(){

    }
    @Override
    public void closeActivity() {
       getActivity().finish();
    }
    @Override
    public void closeActivity(int code) {

    }
}
