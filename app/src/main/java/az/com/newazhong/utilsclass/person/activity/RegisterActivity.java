package az.com.newazhong.utilsclass.person.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.Register;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.presenter.RegisterPresenter;
import az.com.newazhong.utilsclass.presenter.presenterimpl.RegisterPresenterimpl;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.view.RegisterView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.userRegisterPhone)
    EditText userRegisterPhone;
    @Bind(R.id.userRegisterSendData)
    Button userRegisterSendData;

    RegisterPresenter registerPresenter;
    String phone, loginName, pwd, pwdAga, trueName;
    @Bind(R.id.btnYanZheng)
    Button btnYanZheng;
    @Bind(R.id.etYanZheng)
    EditText etYanZheng;
    @Bind(R.id.userTrueName)
    EditText userTrueName;

    String messageT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenterimpl(this, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({ R.id.userRegisterSendData, R.id.btnYanZheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userRegisterSendData:
                phone = userRegisterPhone.getText().toString().trim();
                trueName = userTrueName.getText().toString().trim();
                pwdAga = etYanZheng.getText().toString().trim();
                if (phone.equals("") || pwdAga.equals("") || trueName.equals("")) {
                    Toast.makeText(this, getResources().getString(R.string.cannot_null), Toast.LENGTH_SHORT).show();
                } else {
                    ProgressDialogUtil.startLoad(RegisterActivity.this,"数据提交中");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url1 = Constant.BASE_URL + "user/register";
                            String parme = null;
                            try {
                                parme = "&trueName=" + URLEncoder.encode(userTrueName.getText().toString().trim(), "UTF-8")
                                        + "&mobile=" + URLEncoder.encode(userRegisterPhone.getText().toString().trim(), "UTF-8")
                                        + "&smsCode=" + URLEncoder.encode(pwdAga, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            try {
                                //建立连接
                                URL url = new URL(url1);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                                //设置连接属性
                                connection.setRequestMethod("POST"); //设置URL请求方法
                                connection.setDoOutput(true); //使用URL连接进行输出
                                connection.setDoInput(true); //使用URL连接进行输入
                                connection.setUseCaches(false); //忽略缓存
                                connection.setRequestProperty("Cookie", "");
                                //设置请求属性
//                                connection.setRequestProperty("Content-Type", "application/octet-stream");
//                                connection.setRequestProperty("Connection", "Keep-Alive");
//                                connection.setRequestProperty("Charset", "UTF-8");
//                                connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");
//                                connection.setInstanceFollowRedirects(false);
//                                connection.setConnectTimeout(8000);
//                                connection.setReadTimeout(8000);
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                                writer.write(parme);
                                writer.flush();
                                writer.close();

                                //获取响应状态
                                int responseCode = connection.getResponseCode();
//                                StringBuffer buffer = new StringBuffer();
//                                String readLine;
//                                BufferedReader responseReader;
//                                responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                                int ch;
//                                for(int length = 0;(ch = responseReader.read()) > -1 && (maxLength <= 0 || length < maxLength);length++)
//                                    buffer.append((char)ch);
//                                String s = buffer.toString();
                                    InputStream inputStream = null;
                                    if (responseCode == 201) {
                                        Message message = new Message();
                                        message.what = Constant.TAG_FOUR;
                                        handler.sendMessage(message);
                                    } else {
//                                        BufferedReader responseReader;
////                                    //处理响应流
//                                        responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
////                                    //当正确响应时处理数据
//                                        StringBuffer buffer = new StringBuffer();
//                                        String readLine;
////                                    BufferedReader responseReader;
//                                        //处理响应流
//                                        //  responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                                        while ((readLine = responseReader.readLine()) != null) {
//                                            buffer.append(readLine);
//                                        }
//                                        responseReader.close();
//                                        Log.d("HttpPOST", buffer.toString());
//                                        JSONObject jsonObject = new JSONObject(buffer.toString());
//                                        messageT = jsonObject.getString("message");
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
                                        messageT = jsonObjectC.getString("message")+"";
                                        Log.d("HttpPOST", messageT);
                                        Message message = new Message();
                                        message.what = Constant.TAG_THREE;
                                        handler.sendMessage(message);
                                    }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            case R.id.btnYanZheng:
                String url = Constant.BASE_URL + "sms/code";
                if (userRegisterPhone.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    timer.start();
                    btnYanZheng.setClickable(false);
                    //HttpUrlConnectionUtils.sendPost1(url, userRegisterPhone.getText().toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String param = null;
                            String url1 = Constant.BASE_URL + "sms/code";
                            try {
                                param = "mobile=" + URLEncoder.encode(userRegisterPhone.getText().toString().trim(), "UTF-8");
                                //建立连接
                                URL url = new URL(url1);
                                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                                //设置参数
                                httpConn.setRequestMethod("POST");      //设置POST方式连接
                                httpConn.setDoOutput(true);     //需要输出
                                httpConn.setDoInput(true);      //需要输入
                                httpConn.setUseCaches(false);   //不允许缓存
                                httpConn.setRequestProperty("Cookie", "");
//                                //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
                                httpConn.connect();

                                //建立输入流，向指向的URL传入参数
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8"));
                                writer.write(param);
                                writer.flush();
                                writer.close();

                                //获得响应状态
                                int resultCode = httpConn.getResponseCode();
                                if (HttpURLConnection.HTTP_OK == resultCode) {
                                    Message message = new Message();
                                    message.what = Constant.TAG_ONE;
                                    handler.sendMessage(message);
                                } else {
//                                    //当正确响应时处理数据
//                                    StringBuffer buffer = new StringBuffer();
//                                    String readLine;
//                                    BufferedReader responseReader;
//                                    //处理响应流
//                                    responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
//                                    while ((readLine = responseReader.readLine()) != null) {
//                                        buffer.append(readLine).append("\n");
//                                    }
//                                    responseReader.close();
//                                    Log.d("HttpPOST", buffer.toString());
//                                    Message message = new Message();
//                                    message.what = Constant.TAG_THREE;
//                                    handler.sendMessage(message);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
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

    @Override
    public void getRegisterImage(Register register) {
//        if (String.valueOf(register.isSuccess()).equals("false")) {
//        } else {
//            Message message = new Message();
//            message.what = Constant.TAG_ONE;
//            handler.sendMessage(message);
//        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
//                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_suss),
//                            Toast.LENGTH_SHORT).show();
//                    finish();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(RegisterActivity.this, "注册失败,请检查注册信息", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(RegisterActivity.this, messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    finish();
                    break;
            }

        }
    };
}
