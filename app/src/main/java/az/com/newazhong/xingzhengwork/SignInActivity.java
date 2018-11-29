package az.com.newazhong.xingzhengwork;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.fence.GeoFenceClient;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.AMapUtils;
import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.CurrentDate;
import az.com.newazhong.utilsclass.utils.HttpUrlConnectionUtils;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.xingzhengwork.bean.SignInHistory;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity implements LocationSource, AMapLocationListener {

    @Bind(R.id.tvSignInTime)
    TextView tvSignInTime;
    @Bind(R.id.tvSignInDate)
    TextView tvSignInDate;
    @Bind(R.id.map)
    MapView mapView;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvSmallAddress)
    TextView tvSmallAddress;
    @Bind(R.id.btnSignin)
    FrameLayout btnSignin;
    @Bind(R.id.etText)
    EditText etText;
    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvDepartment)
    TextView tvDepartment;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvData)
    TextView tvData;

    AMapLocationClient mlocationClient;
    //定义接收广播的action字符串
    public static final String GEOFENCE_BROADCAST_ACTION = "com.location.apis.geofencedemo.broadcast";
    GeoFenceClient mGeoFenceClient;
    String messageT, content, contentType;

    String url0 = Constant.BASE_URL + "card/setting";
    String url1 = Constant.BASE_URL + "record/dayLastRecord";
    String url2 = Constant.BASE_URL + "record/preplayCard";
    SharedPreferencesHelper preferencesHelper;
    List<SignInHistory.DataBean> beanList = new ArrayList<>();
    int status;
    @Bind(R.id.tvTime01)
    TextView tvTime01;
    final Message message = new Message();
    final Message messageToast = new Message();
    @Bind(R.id.signType)
    TextView signType;
    String workType = null;
    String currentDate;
    String currentTime;
    String smallAdress;
    @Bind(R.id.imageView)
    ImageView imageView;
    double longitude ;
    double latitude ;
    double radius;

    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private OnLocationChangedListener mListener = null;//定位监听器
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    LatLng startAddress,endAddress;
    private AMap aMap;//地图对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        currentDate = new CurrentDate().getCurrentData();
        currentTime = new CurrentDate().getCurrentTime();
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(this, "login");
        String trueName = sharedPreferencesHelper.getData(this, "trueName", "");
        String depName = sharedPreferencesHelper.getData(this, "depName", "");
        tvName.setText(trueName);
        tvDepartment.setText(depName);
        tvTime.setText(currentTime);
        tvData.setText(currentDate);

        tvSignInTime.setText(currentTime);
        tvSignInDate.setText(currentDate);

        //必须要写
        mapView.onCreate(savedInstanceState);
        //获取地图对象
        aMap = mapView.getMap();
        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        //设置定位监听
        aMap.setLocationSource(this);
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
        //定位的小图标 默认是蓝点 这里自定义一团火，其实就是一张图片
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.firetwo));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);
        if (ContextCompat.checkSelfPermission(SignInActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignInActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            //开始定位
            initLoc();
        }
    }

    //定位
    private void initLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //获取打卡类型
    private void getSignType() {
        final String session = new SharedPreferencesHelper(SignInActivity.this, "login").getData(SignInActivity.this, "session", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrlConnectionUtils.sendGet2(url2, session, new BaseRequestBackTLisenter() {
                    @Override
                    public void success(Object o) {
                        contentType = o.toString();
                        messageToast.what = Constant.TAG_ONE;
                        handlerT.sendMessage(messageToast);
                    }

                    @Override
                    public void fail(String messageT) {
                        message.what = Constant.TAG_TWO;
                        handlerH.sendMessage(message);
                    }

                    @Override
                    public void failF(String messageT) {
                        message.what = Constant.TAG_THREE;
                        handlerH.sendMessage(message);
                    }
                });
            }
        }).start();
    }

    //获取签到历史
    private void getHistoryData() {
        final String session = new SharedPreferencesHelper(SignInActivity.this, "login").getData(SignInActivity.this, "session", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrlConnectionUtils.sendGet2(url1, session, new BaseRequestBackTLisenter() {
                    @Override
                    public void success(Object o) {
                        content = o.toString();
                        message.what = Constant.TAG_ONE;
                        handlerH.sendMessage(message);
                    }

                    @Override
                    public void fail(String messageT) {
                        message.what = Constant.TAG_TWO;
                        handlerH.sendMessage(message);
                    }

                    @Override
                    public void failF(String messageT) {
                        message.what = Constant.TAG_THREE;
                        handlerH.sendMessage(message);
                    }
                });
            }
        }).start();
    }

    //获取经纬度
    private void getMapData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url0);
                    final String session = new SharedPreferencesHelper(SignInActivity.this, "login").getData(SignInActivity.this, "session", "");
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.addRequestProperty("Cookie", session);
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    connection.connect();
                    //获取响应状态
                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        //当正确响应时处理数据
                        StringBuffer buffer = new StringBuffer();
                        String readLine;
                        BufferedReader responseReader;
                        //处理响应流
                        responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((readLine = responseReader.readLine()) != null) {
                            buffer.append(readLine).append("\n");
                        }
                        JSONObject jsonObject = new JSONObject(buffer.toString());
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObjectData.getJSONArray("content");
                        JSONObject jsonObjectContent = jsonArray.getJSONObject(0);
                        longitude = Double.parseDouble(jsonObjectContent.getString("longitude"));
                        latitude = Double.parseDouble(jsonObjectContent.getString("latitude"));
                        radius = Double.parseDouble(jsonObjectContent.getString("radius"));
                        preferencesHelper.saveData(SignInActivity.this, "longitude", longitude);
                        preferencesHelper.saveData(SignInActivity.this, "latitude", latitude);
                        preferencesHelper.saveData(SignInActivity.this, "radius", String.valueOf(radius));
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler1.sendMessage(message);
                    } else if (responseCode == 401) {
                        Message message = new Message();
                        message.what = Constant.TAG_ONE;
                        handler1.sendMessage(message);
                    } else {
                        InputStream inputStream = null;
                        inputStream = new BufferedInputStream(connection.getErrorStream());
                        BufferedReader responseReader;
                        responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                        StringBuffer buffer = new StringBuffer();
                        String readLine;
                        while ((readLine = responseReader.readLine()) != null) {
                            buffer.append(readLine);
                        }
                        responseReader.close();
                        Log.d("HttpPOST", buffer.toString());
                        JSONObject jsonObject = new JSONObject(buffer.toString());
                        messageT = jsonObject.getString("message");
                        Message message = new Message();
                        message.what = Constant.TAG_THREE;
                        handler1.sendMessage(message);
                    }
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
        Intent intent = new Intent(this, SignInHistoryActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.btnSignin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                int num = (int) AMapUtils.calculateLineDistance(startAddress, endAddress);
                if (num>radius){
                    status = 0;
                }else {
                    status = 1;
                }
                if (status == 1) {
                    if (workType != null && workType.equals("CLOCK_NORMAL")) {
                        getNormalData();
                    } else if (workType != null && workType.equals("CLOCK_LATE")) {
                        imageView.setBackground(getResources().getDrawable(R.drawable.custom_progress_big_radius_late_n));
                        getNormalData();
//                        //迟到
//                        LayoutInflater layoutInflater = LayoutInflater.from(this);
//                        View myLoginView = layoutInflater.inflate(R.layout.alertdiolag, null);
//                        final EditText editText = (EditText) myLoginView.findViewById(R.id.editText);
//                        final TextView etTime = (TextView) myLoginView.findViewById(R.id.etTime);
//                        final TextView etAddress = (TextView) myLoginView.findViewById(R.id.etAddress);
//                        etTime.setText(currentTime);
//                        etAddress.setText(smallAdress);
//                        Button btnSure = (Button) myLoginView.findViewById(R.id.btnSure);
//                        Button btnNo = (Button) myLoginView.findViewById(R.id.btnNo);
//                        final Dialog alertDialog = new AlertDialog.Builder(this).
//                                setTitle("确定要迟到打卡吗").
//                                setView(myLoginView).
//                                create();
//                        btnSure.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (editText.getText().toString().length() == 0) {
//                                    Toast.makeText(SignInActivity.this, "请输入签到说明", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    ProgressDialogUtil.startLoad(SignInActivity.this, "数据提交中");
//                                    alertDialog.cancel();
//                                    getErrarData(editText);
//                                }
//                            }
//                        });
//                        btnNo.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                alertDialog.cancel();
//                            }
//                        });
//                        alertDialog.show();
                    } else if (workType != null && workType.equals("CLOCK_EARLY")) {
                        //早退
                        LayoutInflater layoutInflater = LayoutInflater.from(this);
                        View myLoginView = layoutInflater.inflate(R.layout.alertdiolag, null);
                        final EditText editText = (EditText) myLoginView.findViewById(R.id.editText);
                        final TextView etTime = (TextView) myLoginView.findViewById(R.id.etTime);
                        final TextView etAddress = (TextView) myLoginView.findViewById(R.id.etAddress);
                        etTime.setText(currentTime);
                        etAddress.setText(smallAdress);
                        Button btnSure = (Button) myLoginView.findViewById(R.id.btnSure);
                        Button btnNo = (Button) myLoginView.findViewById(R.id.btnNo);
                        final Dialog alertDialog = new AlertDialog.Builder(this).
                                setTitle("确定要早退打卡吗").
                                setView(myLoginView).
                                create();
                        btnSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editText.getText().toString().length() == 0) {
                                    Toast.makeText(SignInActivity.this, "请输入签到说明", Toast.LENGTH_SHORT).show();
                                } else {
                                    ProgressDialogUtil.startLoad(SignInActivity.this, "数据提交中");
                                    alertDialog.cancel();
                                    getErrarData(editText);
                                }
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }

                } else {
                    if (workType != null && workType.equals("CLOCK_NORMAL")) {
                        LayoutInflater layoutInflater = LayoutInflater.from(this);
                        View myLoginView = layoutInflater.inflate(R.layout.alertdiolag, null);
                        final EditText editText = (EditText) myLoginView.findViewById(R.id.editText);
                        final TextView etTime = (TextView) myLoginView.findViewById(R.id.etTime);
                        final TextView etAddress = (TextView) myLoginView.findViewById(R.id.etAddress);
                        etTime.setText(currentTime);
                        etAddress.setText(smallAdress);
                        Button btnSure = (Button) myLoginView.findViewById(R.id.btnSure);
                        Button btnNo = (Button) myLoginView.findViewById(R.id.btnNo);
                        final Dialog alertDialog = new AlertDialog.Builder(this).
                                setTitle("不在打卡范围内").
                                setView(myLoginView).
                                create();
                        btnSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editText.getText().toString().length() == 0) {
                                    Toast.makeText(SignInActivity.this, "请输入签到说明", Toast.LENGTH_SHORT).show();
                                } else {
                                    ProgressDialogUtil.startLoad(SignInActivity.this, "数据提交中");
                                    alertDialog.cancel();
                                    getErrarData(editText);
                                }
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        alertDialog.show();
                    } else if (workType != null && workType.equals("CLOCK_LATE")) {
                        //迟到
                        LayoutInflater layoutInflater = LayoutInflater.from(this);
                        View myLoginView = layoutInflater.inflate(R.layout.alertdiolag, null);
                        final EditText editText = (EditText) myLoginView.findViewById(R.id.editText);
                        final TextView etTime = (TextView) myLoginView.findViewById(R.id.etTime);
                        final TextView etAddress = (TextView) myLoginView.findViewById(R.id.etAddress);
                        etTime.setText(currentTime);
                        etAddress.setText(smallAdress);
                        Button btnSure = (Button) myLoginView.findViewById(R.id.btnSure);
                        Button btnNo = (Button) myLoginView.findViewById(R.id.btnNo);
                        final Dialog alertDialog = new AlertDialog.Builder(this).
                                setTitle("不在打卡范围内").
                                setView(myLoginView).
                                create();
                        btnSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editText.getText().toString().length() == 0) {
                                    Toast.makeText(SignInActivity.this, "请输入签到说明", Toast.LENGTH_SHORT).show();
                                } else {
                                    ProgressDialogUtil.startLoad(SignInActivity.this, "数据提交中");
                                    alertDialog.cancel();
                                    getErrarData(editText);
                                }
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        alertDialog.show();
                    } else if (workType != null && workType.equals("CLOCK_EARLY")) {
                        //早退
                        LayoutInflater layoutInflater = LayoutInflater.from(this);
                        View myLoginView = layoutInflater.inflate(R.layout.alertdiolag, null);
                        final EditText editText = (EditText) myLoginView.findViewById(R.id.editText);
                        final TextView etTime = (TextView) myLoginView.findViewById(R.id.etTime);
                        final TextView etAddress = (TextView) myLoginView.findViewById(R.id.etAddress);
                        etTime.setText(currentTime);
                        etAddress.setText(smallAdress);
                        Button btnSure = (Button) myLoginView.findViewById(R.id.btnSure);
                        Button btnNo = (Button) myLoginView.findViewById(R.id.btnNo);
                        final Dialog alertDialog = new AlertDialog.Builder(this).
                                setTitle("确定要早退打卡吗(不在打卡范围内)").
                                setView(myLoginView).
                                create();
                        btnSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editText.getText().toString().length() == 0) {
                                    Toast.makeText(SignInActivity.this, "请输入签到说明", Toast.LENGTH_SHORT).show();
                                } else {
                                    ProgressDialogUtil.startLoad(SignInActivity.this, "数据提交中");
                                    alertDialog.cancel();
                                    getErrarData(editText);
                                }
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }
                }
                break;
        }
    }

    private void getErrarData(final EditText editText) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //建立连接
                    String param = "address=" + URLEncoder.encode(tvAddress.getText().toString().trim()+tvSmallAddress.getText().toString().trim(), "UTF-8") + "&inRange=" + "false" + "&memo=" + URLEncoder.encode(editText.getText().toString(), "UTF-8");
                    final String url1 = Constant.BASE_URL + "record";
                    String session = new SharedPreferencesHelper(SignInActivity.this, "login").getData(SignInActivity.this, "session", "");
                    URL url = new URL(url1);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置连接属性
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.addRequestProperty("Cookie", session);
                    DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
                    writer.write(param.getBytes());
                    writer.flush();
                    writer.close();
                    //获取响应状态
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 401) {
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    } else if (responseCode == 201) {
                        Message message = new Message();
                        message.what = Constant.TAG_FOUR;
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
                        JSONObject jsonObject = new JSONObject(buffer.toString());
                        messageT = jsonObject.getString("message");
                        Message message = new Message();
                        message.what = Constant.TAG_THREE;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = Constant.TAG_FIVE;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private void getNormalData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //建立连接
                    String param = "address=" + URLEncoder.encode(tvAddress.getText().toString().trim(), "UTF-8") + "&inRange=" + "false" + "&memo=" + URLEncoder.encode("", "UTF-8");
                    final String url1 = Constant.BASE_URL + "record";
                    String session = new SharedPreferencesHelper(SignInActivity.this, "login").getData(SignInActivity.this, "session", "");
                    URL url = new URL(url1);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置连接属性
                    connection.setRequestMethod("POST"); //设置URL请求方法
                    connection.setDoOutput(true); //使用URL连接进行输出
                    connection.setDoInput(true); //使用URL连接进行输入
                    connection.setUseCaches(false); //忽略缓存
                    connection.addRequestProperty("Cookie", session);
                    DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
                    writer.write(param.getBytes());
                    writer.flush();
                    writer.close();
                    //获取响应状态
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 401) {
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    } else if (responseCode == 201) {
                        Message message = new Message();
                        message.what = Constant.TAG_FOUR;
                        handler.sendMessage(message);
                    } else {
                        inputStream = new BufferedInputStream(connection.getErrorStream());
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

                        responseReader.close();
                        Log.d("HttpPOST", buffer.toString());
                        JSONObject jsonObject = new JSONObject(buffer.toString());
                        messageT = jsonObject.getString("message");
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

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                // 显示系统小蓝点
                mListener.onLocationChanged(aMapLocation);
                //获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLocationType();
                //获取纬度
                aMapLocation.getLatitude();
                //获取经度
                aMapLocation.getLongitude();
                //获取精度信息
                aMapLocation.getAccuracy();
                //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getAddress();
                //国家信息
                aMapLocation.getCountry();
                //省信息
                aMapLocation.getProvince();
                //城市信息
                aMapLocation.getCity();
                //城区信息
                aMapLocation.getDistrict();
                //街道信息
                aMapLocation.getStreet();
                //街道门牌号信息
                aMapLocation.getStreetNum();
                //城市编码
                aMapLocation.getCityCode();
                //地区编码
                aMapLocation.getAdCode();
                String adress = aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict();
                startAddress = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                smallAdress = aMapLocation.getStreet() + aMapLocation.getStreetNum();
                tvAddress.setText(adress);
                tvSmallAddress.setText(smallAdress);

                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
//                    //添加图钉
//                    aMap.addMarker(getMarkerOptions(aMapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + "" + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" + aMapLocation.getProvince() + "" + aMapLocation.getDistrict() + "" + aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }
                ProgressDialogUtil.startLoad(this, "获取经纬度...");
                getMapData();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    //自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
//        //图标
//        options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fire));
        //位置
        options.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        String content = buffer.toString();
        //标题
        options.title(content);
        //设置多少帧刷新一次图片资源
        options.period(60);

        return options;

    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
//        if (mlocationClient == null) {
//            //初始化定位
//            mlocationClient = new AMapLocationClient(this);
//            //初始化定位参数
//            mLocationOption = new AMapLocationClientOption();
//            //设置定位回调监听
//            mlocationClient.setLocationListener(this);
//            //设置为高精度定位模式
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //单次定位
//            //mLocationOption.setOnceLocation(true);
//            //设置定位参数
//            mlocationClient.setLocationOption(mLocationOption);
//            if (ContextCompat.checkSelfPermission(SignInActivity.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(SignInActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//            } else {
//                //开始定位
//                mlocationClient.startLocation();//启动定位
//            }
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //开始定位
                    initLoc();
                } else {
                    Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //ProgressDialogUtil.startLoad(this, "获取经纬度...");
        getMapData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.TAG_TWO:
                    endAddress = new LatLng(latitude,longitude);
//                    ProgressDialogUtil.stopLoad();
//                    ProgressDialogUtil.startLoad(SignInActivity.this,"获取签到历史");
                    getHistoryData();
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(SignInActivity.this, messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_ONE:
                    Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                    intent.putExtra("tag", "false");
                    new SharedPreferencesHelper(SignInActivity.this, "login").removeData(SignInActivity.this);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
            }

        }
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_THREE:
                    Toast.makeText(SignInActivity.this, messageT, Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(SignInActivity.this, SignInActivity.class);
                    startActivity(intent3);
                    finish();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(SignInActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(SignInActivity.this, SignInActivity.class);
                    startActivity(intent2);
                    finish();
                    ProgressDialogUtil.stopLoad();
//                    finish();
                    break;
                case Constant.TAG_TWO:
                    Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                    ProgressDialogUtil.stopLoad();
                    startActivity(intent);
                    break;
                case Constant.TAG_ONE:
                    Toast.makeText(SignInActivity.this, "请填写签到说明", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_FIVE:
                    Toast.makeText(SignInActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    private Handler handlerH = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    try {
                        JSONObject jsonObject = new JSONObject(content);
                        String result = String.valueOf(jsonObject.getString("result"));
                        String timeH = null;
                        if (result.equals("true")) {
                            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                            timeH = jsonObjectData.getString("recordTime");
                            tvTime01.setText("打卡时间: " + timeH);
                            tvTime01.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    ProgressDialogUtil.stopLoad();
//                    ProgressDialogUtil.startLoad(SignInActivity.this,"获取签到类型");
                    getSignType();

                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(SignInActivity.this, "获取记录失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    finish();
                    break;
                case Constant.TAG_THREE:
                    Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    private Handler handlerT = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(contentType);
                String result = String.valueOf(jsonObject.getString("result"));
                String carType ;
                if (result.equals("true")) {
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    carType = jsonObjectData.getString("punchCardType");
                    workType = jsonObjectData.getString("cardType");
                    if (carType.equals("CLOCK_AM_START")) {
                        signType.setText("上午上班");
                    } else if (carType.equals("CLOCK_AM_END")) {
                        signType.setText("上午下班");
                    } else if (carType.equals("CLOCK_PM_START")) {
                        signType.setText("下午上班");
                    } else if (carType.equals("CLOCK_PM_END")) {
                        signType.setText("下午下班");
                    } else if (carType.equals("DUTY_NIGHT")) {
                        signType.setText("晚上值班");
                    }
                    if (workType != null && workType.equals("CLOCK_LATE")) {
                        imageView.setBackground(getResources().getDrawable(R.drawable.custom_progress_big_radius_late_n));
                    }
                } else {
//                    Intent intent = new Intent(SignInActivity.this, SignInHistoryActivity.class);
//                    intent.putExtra("tag", "on");
//                    startActivity(intent);
//                    finish();
                    btnSignin.setEnabled(false);
                    imageView.setBackground(getResources().getDrawable(R.drawable.custom_progress_big_radius_n));
                }
                ProgressDialogUtil.stopLoad();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}