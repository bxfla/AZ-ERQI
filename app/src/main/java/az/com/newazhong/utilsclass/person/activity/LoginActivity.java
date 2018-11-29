package az.com.newazhong.utilsclass.person.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Login;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.presenter.LoginPresenter;
import az.com.newazhong.utilsclass.presenter.presenterimpl.LoginPresenterimpl;
import az.com.newazhong.utilsclass.utils.ActivityCollector;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.view.LoginView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.userPhone)
    EditText userPhone;
    @Bind(R.id.userPassword)
    EditText userPassword;
    @Bind(R.id.userResetPassword)
    TextView userResetPassword;
    @Bind(R.id.userLogin)
    Button userLogin;

    private static boolean isExit = false;
    SharedPreferencesHelper preference;
    LoginPresenter loginPresenter;
    String cookieString;
    String id;
    String phone, pwd;
    String content;
    Intent intent;
    String messageT, resultT;

    //推出程序
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Bind(R.id.btnYanZheng)
    Button btnYanZheng;
    String tag;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        preference = new SharedPreferencesHelper(this, "login");
        id = preference.getData(this, "session", "");
        if (!id.equals("")) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        loginPresenter = new LoginPresenterimpl(this, this);
        userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.userResetPassword, R.id.userLogin,R.id.btnYanZheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnYanZheng:
                timer.start();
                btnYanZheng.setClickable(false);
                String url = Constant.BASE_URL + "sms/code";
                if (userPhone.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    //HttpUrlConnectionUtils.sendPost1(url, userRegisterPhone.getText().toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String param = null;
                            String url1 = Constant.BASE_URL + "sms/code";
                            try {
                                param = "mobile=" + URLEncoder.encode(userPhone.getText().toString().trim(), "UTF-8");
                                //建立连接
                                URL url = new URL(url1);
                                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                                //设置参数
                                httpConn.setRequestMethod("POST");
                                httpConn.setDoOutput(true);
                                httpConn.setDoInput(true);
                                httpConn.setUseCaches(false);
                                httpConn.setRequestProperty("Cookie", "");
                                httpConn.connect();
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8"));
                                writer.write(param);
                                writer.flush();
                                writer.close();

                                //获得响应状态
                                int resultCode = httpConn.getResponseCode();
                                InputStream inputStream = null;
                                if (HttpURLConnection.HTTP_OK == resultCode||resultCode == 204) {
//                                    Message message = new Message();
//                                    message.what = Constant.TAG_ONE;
//                                    handler.sendMessage(message);
                                } else {
                                    inputStream = new BufferedInputStream(httpConn.getErrorStream());
                                    BufferedReader responseReader;
//                                    //处理响应流
                                    responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                                    //当正确响应时处理数据
                                    StringBuffer buffer = new StringBuffer();
                                    int count = 1024;
                                    int result = -1;
                                    char[] readChars = new char[count];
                                    String temp = null;
                                    do {
                                        result = responseReader.read(readChars, 0, count);
                                        if (result > 0) {
                                            temp = new String(readChars, 0, result);
                                            buffer.append(temp);
                                        }

                                    } while (result != -1);
                                    responseReader.close();
                                    Log.d("HttpPOST", buffer.toString());
                                    JSONObject jsonObjectC = new JSONObject(buffer.toString());
                                    messageT = jsonObjectC.getString("message")+"";
                                    Log.d("HttpPOST", messageT);
                                    Message message = new Message();
                                    message.what = Constant.TAG_FOUR;
                                    handler.sendMessage(message);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            case R.id.userLogin:
                phone = userPhone.getText().toString().trim();
                pwd = userPassword.getText().toString().trim();
                if (phone.equals("") || pwd.equals("")) {
                    Toast.makeText(this, getResources().getString(R.string.cannot_null), Toast.LENGTH_SHORT).show();
                } else {
                    ProgressDialogUtil.startLoad(this, "登陆中");
                    final String url0 = Constant.BASE_URL + "user/sms/login";
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String param = null;
                            try {
                                param = "mobile=" + URLEncoder.encode(phone, "UTF-8") + "&smsCode=" + URLEncoder.encode(pwd, "UTF-8");
                                URL url = new URL(url0);
                                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                                httpConn.setDoOutput(true);
                                httpConn.setDoInput(true);
                                httpConn.setUseCaches(false);
                                httpConn.setRequestMethod("POST");
                                httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                httpConn.setRequestProperty("Connection", "Keep-Alive");
                                httpConn.setRequestProperty("Charset", "UTF-8");
                                httpConn.setRequestProperty("Cookie", "");
                                httpConn.setInstanceFollowRedirects(false);
                                //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
                                httpConn.connect();
                                DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                                dos.writeBytes(param);
                                dos.flush();
                                dos.close();
                                int resultCode = httpConn.getResponseCode();
                                if (302 == resultCode) {
                                    cookieString = httpConn.getHeaderField("Set-Cookie");
                                    cookieString = cookieString.substring(0, cookieString.indexOf(";"));
                                    JumpPage(cookieString);
                                } else if (HttpURLConnection.HTTP_OK == resultCode) {
                                    cookieString = httpConn.getHeaderField("Set-Cookie");
                                    if (cookieString == null) {
                                        Message message = new Message();
                                        message.what = Constant.TAG_TWO;
                                        handlerT.sendMessage(message);
                                    } else {
                                        cookieString = cookieString.substring(0, cookieString.indexOf(";"));
                                        JumpPage(cookieString);
                                    }
                                }else {
                                    InputStream inputStream = null;
                                    inputStream = new BufferedInputStream(httpConn.getErrorStream());
                                    BufferedReader responseReader;
//                                    //处理响应流
                                    responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                                    //当正确响应时处理数据
                                    StringBuffer buffer = new StringBuffer();
                                    int count = 1024;
                                    int result = -1;
                                    char[] readChars = new char[count];
                                    String temp = null;
                                    do {

                                        result = responseReader.read(readChars, 0, count);
                                        if (result > 0) {
                                            temp = new String(readChars, 0, result);
                                            buffer.append(temp);
                                        }

                                    } while (result != -1);
                                    responseReader.close();
                                    Log.d("HttpPOST", buffer.toString());
                                    JSONObject jsonObjectC = new JSONObject(buffer.toString());
                                    messageT = jsonObjectC.getString("message")+"";
                                    Log.d("HttpPOST", messageT);
                                    Message message = new Message();
                                    message.what = Constant.TAG_FOUR;
                                    handler.sendMessage(message);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                }
                break;
        }
    }

    private void JumpPage(final String jsId) {
        final String url0 = Constant.BASE_URL + "user/currentUser";
        //建立连接
        try {
            URL url1 = new URL(url0);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.setRequestProperty("Cookie", jsId);
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.connect();
            //获取响应状态
            int responseCode = connection.getResponseCode();
            InputStream inputStream = null;
            if (responseCode == 200) {
                inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader responseReader;
                responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                int count = 1024;
                int result = -1;
                char[] readChars = new char[count];
                String temp = null;
                do {

                    result = responseReader.read(readChars, 0, count);
                    if (result > 0) {
                        temp = new String(readChars, 0, result);
                        buffer.append(temp);
                    }

                } while (result != -1);
                content = buffer.toString();
                responseReader.close();
                Log.d("HttpPOST", buffer.toString());
                JSONObject jsonObjectC = new JSONObject(buffer.toString());
                messageT = jsonObjectC.getString("message") + "";
                Message message = new Message();
                message.what = Constant.TAG_ONE;
                handler.sendMessage(message);
            } else if (responseCode == 500) {
                Message message = new Message();
                message.what = Constant.TAG_TWO;
                handler.sendMessage(message);
            }else if (responseCode == 401) {
                Message message = new Message();
                message.what = Constant.TAG_TWO;
                handler.sendMessage(message);
            } else {
                inputStream = new BufferedInputStream(connection.getErrorStream());
                BufferedReader responseReader;
//                                    //处理响应流
                responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                                    //当正确响应时处理数据
                StringBuffer buffer = new StringBuffer();
                int count = 1024;
                int result = -1;
                char[] readChars = new char[count];
                String temp = null;
                do {

                    result = responseReader.read(readChars, 0, count);
                    if (result > 0) {
                        temp = new String(readChars, 0, result);
                        buffer.append(temp);
                    }

                } while (result != -1);
                responseReader.close();
                Log.d("HttpPOST", buffer.toString());
                JSONObject jsonObjectC = new JSONObject(buffer.toString());
                messageT = jsonObjectC.getString("message") + "";
                Message message = new Message();
                message.what = Constant.TAG_THREE;
                handler.sendMessage(message);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 第一个参数表示总时间，第二个参数表示间隔时间。意思就是每隔一秒会回调一次方法onTick，然后60秒之后会回调onFinish方法
    final CountDownTimer timer = new CountDownTimer(100000, 1000) {

        @Override
        public void onTick(long arg0) {
            // 定期定期回调
            btnYanZheng.setText((arg0 / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            // 结束后回到
            btnYanZheng.setText("获取验证码");
            btnYanZheng.setClickable(true);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    JSONObject jsonObject = null;
                    try {
                        String depName = null;
                        jsonObject = new JSONObject(content);
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        String trueName = jsonObjectData.getString("trueName");
                        String mobile = jsonObjectData.getString("mobile");
                        String userType = jsonObjectData.getString("masses");
                        String id = jsonObjectData.getString("id");
                        if (!userType.equals("true")){
                            JSONArray jsonArray = jsonObjectData.getJSONArray("departments");
                            JSONObject jsonObjectDep = jsonArray.getJSONObject(0);
                            depName = jsonObjectDep.getString("name");
                        }
                        if (depName!=null){
                            preference.saveData(LoginActivity.this, "depName", depName);
                        }
                        preference.saveData(LoginActivity.this, "session", cookieString);
                        preference.saveData(LoginActivity.this, "trueName", trueName);
                        preference.saveData(LoginActivity.this, "mobile", mobile);
                        preference.saveData(LoginActivity.this, "userType", userType);
                        preference.saveData(LoginActivity.this, "userId", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ProgressDialogUtil.stopLoad();
                    if (tag!=null&&tag.equals("false")){
                        finish();
                    }else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case Constant.TAG_TWO:
                    ProgressDialogUtil.stopLoad();
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    Toast.makeText(LoginActivity.this, "登陆失败"+messageT, Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(LoginActivity.this, "登陆失败"+messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_FIVE:
                    Toast.makeText(LoginActivity.this, "登陆失败"+messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    private Handler handlerT = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ProgressDialogUtil.stopLoad();
            Toast.makeText(LoginActivity.this, "登陆失败，请检查账号密码", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void getLoginData(Login login) {
        if (String.valueOf(login.isSuccess()).equals("false")) {
        } else {
            id = login.getBody().getUserInfo().getId();
            String loginName = login.getBody().getUserInfo().getLoginName();
            String userPhone = login.getBody().getUserInfo().getPhone();
            preference.saveData(this, "userId", id);
            preference.saveData(this, "loginName", loginName);
            preference.saveData(this, "userPhone", userPhone);
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
