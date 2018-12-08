package az.com.newazhong.utilsclass.person.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.person.activity.MyFindActivity;
import az.com.newazhong.utilsclass.person.activity.QueryActivityActivity;
import az.com.newazhong.utilsclass.utils.ActivityCollector;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/8/1.
 */

public class Fragment2 extends Fragment {
    @Bind(R.id.iv_photo)
    ImageView ivPhoto;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.queryUpQuestion)
    RelativeLayout queryUpQuestion;
    @Bind(R.id.queryActivity)
    RelativeLayout queryActivity;
    @Bind(R.id.queryWorkGuide)
    RelativeLayout queryWorkGuide;
    @Bind(R.id.upMyinfor)
    RelativeLayout upMyinfor;

    SharedPreferencesHelper sharedPreferencesHelper;
    AlertDialogUtil alertDialogUtil;
    String loginName, userPhone, userType;
    @Bind(R.id.btnBack)
    Button btnBack;
    private View view;
    Intent intent;
    int page = 0;
    int size = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment2, container, false);
        ButterKnife.bind(this, view);z
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "login");
        alertDialogUtil = new AlertDialogUtil(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginName = sharedPreferencesHelper.getData(getActivity(), "trueName", "");
        userType = sharedPreferencesHelper.getData(getActivity(), "userType", "");
        if (!loginName.equals("")) {
            loginName = sharedPreferencesHelper.getData(getActivity(), "trueName", "");
            userPhone = sharedPreferencesHelper.getData(getActivity(), "mobile", "");
            tvName.setText(loginName);
            tvPhone.setText(userPhone);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.btnLogin, R.id.queryUpQuestion, R.id.queryActivity, R.id.queryWorkGuide, R.id.upMyinfor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.queryUpQuestion:
                if (!userType.equals("true")){
                    Toast.makeText(getActivity(), "办公人员请到随手拍查看", Toast.LENGTH_SHORT).show();
                }else if (loginName.toString().length() == 0){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("tag","false");
                    new SharedPreferencesHelper(getActivity(),"login").removeData(getActivity());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), MyFindActivity.class);
                    intent.putExtra("tag","false");
                    startActivity(intent);
                }
                break;
            case R.id.queryActivity:
                intent = new Intent(getActivity(), QueryActivityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.queryWorkGuide:
                break;
        }
    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
        ActivityCollector.finidhAll();
        Intent intent = new Intent(MyApplication.getContextObject(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        new SharedPreferencesHelper(getActivity(),"login").removeData(MyApplication.getContextObject());
    }
}
