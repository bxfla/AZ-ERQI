package az.com.newazhong.xingzhengwork.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceClient;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.CurrentDate;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/12.
 */

public class FragmentSignIn extends Fragment implements LocationSource, AMapLocationListener {
    View view;
    @Bind(R.id.tvSignInTime)
    TextView tvSignInTime;
    @Bind(R.id.tvSignInDate)
    TextView tvSignInDate;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvDepartment)
    TextView tvDepartment;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvSmallAddress)
    TextView tvSmallAddress;
    @Bind(R.id.map)
    MapView mapView;
    @Bind(R.id.etText)
    EditText etText;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvData)
    TextView tvData;
    @Bind(R.id.btnSignin)
    FrameLayout btnSignin;

    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    //定义接收广播的action字符串
    public static final String GEOFENCE_BROADCAST_ACTION = "com.location.apis.geofencedemo.broadcast";
    GeoFenceClient mGeoFenceClient;
    String messageT;

    String url0 = Constant.BASE_URL + "card/setting";
    SharedPreferencesHelper preferencesHelper;
    int status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);

        String currentDate = new CurrentDate().getCurrentData();
        String currentTime = new CurrentDate().getCurrentTime();
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "login");
        String trueName = sharedPreferencesHelper.getData(getActivity(), "trueName", "");
        String depName = sharedPreferencesHelper.getData(getActivity(), "depName", "");
        tvName.setText(trueName);
        tvDepartment.setText(depName);
        tvTime.setText(currentTime);
        tvData.setText(currentDate);

        tvSignInTime.setText(currentTime);
        tvSignInDate.setText(currentDate);

        // 此方法必须重写
        mapView.onCreate(savedInstanceState);
        AMap aMap = mapView.getMap();
        //隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        // 卫星地图模式
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        //缩放比例
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //实例化地理围栏客户端
        mGeoFenceClient = new GeoFenceClient(getActivity());

        mlocationClient = new AMapLocationClient(getActivity());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        aMap.setLocationSource(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();


        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);

        ProgressDialogUtil.startLoad(getActivity(), "获取经纬度...");
        final String session = new SharedPreferencesHelper(getActivity(), "login").getData(getActivity(), "session", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url0);
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
                        String longitude = jsonObjectContent.getString("longitude");
                        String latitude = jsonObjectContent.getString("latitude");
                        String radius = jsonObjectContent.getString("radius");
                        preferencesHelper.saveData(getActivity(), "longitude", longitude);
                        preferencesHelper.saveData(getActivity(), "latitude", latitude);
                        preferencesHelper.saveData(getActivity(), "radius", radius);
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

        return view;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnSignin)
    public void onViewClicked() {
        switch (view.getId()) {
            case R.id.btnSignin:
                if (status == 1) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //建立连接
                                String param = "address=" + URLEncoder.encode(tvAddress.getText().toString().trim(), "UTF-8") + "&inRange=" + "false" + "&memo=" + URLEncoder.encode("", "UTF-8");
                                final String url1 = Constant.BASE_URL + "record";
                                String session = new SharedPreferencesHelper(getActivity(), "login").getData(getActivity(), "session", "");
                                URL url = new URL(url1);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                //设置连接属性
                                connection.setRequestMethod("POST"); //设置URL请求方法
                                connection.setDoOutput(true); //使用URL连接进行输出
                                connection.setDoInput(true); //使用URL连接进行输入
                                connection.setUseCaches(false); //忽略缓存
                                connection.addRequestProperty("Cookie", session);
                                //设置请求属性
//                                connection.setRequestProperty("Content-Type", "application/octet-stream");
//                                connection.setRequestProperty("Connection", "Keep-Alive");
//                                connection.setRequestProperty("Charset", "UTF-8");
//                                connection.addRequestProperty("Cookie", "JSESSIONID="+session+"");
//                                connection.setInstanceFollowRedirects(false);
//                                connection.setConnectTimeout(8000);
//                                connection.setReadTimeout(8000);
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                                writer.write(param);
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
                            }
                        }
                    }).start();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //建立连接
                                if (etText.getText().toString().length() == 0) {
                                    Message message = new Message();
                                    message.what = Constant.TAG_ONE;
                                    handler.sendMessage(message);
                                } else {
                                    String param = "address=" + URLEncoder.encode(tvAddress.getText().toString().trim(), "UTF-8") + "&inRange=" + "false" + "&memo=" + URLEncoder.encode(URLEncoder.encode(etText.getText().toString()), "UTF-8");
                                    final String url1 = Constant.BASE_URL + "record";
                                    String session = new SharedPreferencesHelper(getActivity(), "login").getData(getActivity(), "session", "");
                                    URL url = new URL(url1);
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    //设置连接属性
                                    connection.setRequestMethod("POST");
                                    connection.setDoOutput(true);
                                    connection.setDoInput(true);
                                    connection.setUseCaches(false);
                                    connection.addRequestProperty("Cookie", session);
                                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                                    writer.write(param);
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
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
//                    }
                }
                break;
        }
    }

//    /**
//     * 定位成功后回调函数
//     */
//    @Override
//    public void onLocationChanged(AMapLocation aMapLocation) {
//        if (mListener != null && aMapLocation != null) {
//            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
//                // 显示系统小蓝点
//                mListener.onLocationChanged(aMapLocation);
//                //获取当前定位结果来源，如网络定位结果，详见官方定位类型表
//                aMapLocation.getLocationType();
//                //获取纬度
//                aMapLocation.getLatitude();
//                //获取经度
//                aMapLocation.getLongitude();
//                //获取精度信息
//                aMapLocation.getAccuracy();
//                //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                aMapLocation.getAddress();
//                //国家信息
//                aMapLocation.getCountry();
//                //省信息
//                aMapLocation.getProvince();
//                //城市信息
//                aMapLocation.getCity();
//                //城区信息
//                aMapLocation.getDistrict();
//                //街道信息
//                aMapLocation.getStreet();
//                //街道门牌号信息
//                aMapLocation.getStreetNum();
//                //城市编码
//                aMapLocation.getCityCode();
//                //地区编码
//                aMapLocation.getAdCode();
//                String adress = aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict();
//                String smallAdress = aMapLocation.getStreet() + aMapLocation.getStreetNum();
//                tvAddress.setText(adress);
//                tvSmallAddress.setText(smallAdress);
//
//                //创建一个中心点坐标
//                DPoint centerPoint = new DPoint();
////                //设置中心点纬度
////                centerPoint.setLatitude(aMapLocation.getLatitude());
////                //设置中心点经度
////                centerPoint.setLongitude(aMapLocation.getLongitude());
//                //设置中心点纬度
////                centerPoint.setLatitude(116.932492);
////                //设置中心点经度
////                centerPoint.setLongitude(36.656156);
////                mGeoFenceClient.addGeoFence(centerPoint, 200F, "221355");
//
//                //创建回调监听
//                GeoFenceListener fenceListenter = new GeoFenceListener() {
//
//                    @Override
//                    public void onGeoFenceCreateFinished(List<GeoFence> list, int i, String s) {
//                        if (i == GeoFence.ADDGEOFENCE_SUCCESS) {//判断围栏是否创建成功
//                            Toast.makeText(getActivity(), "添加围栏成功", Toast.LENGTH_SHORT).show();
//                            //geoFenceList就是已经添加的围栏列表，可据此查看创建的围栏
//                            //创建并设置PendingIntent
//                            mGeoFenceClient.createPendingIntent(GEOFENCE_BROADCAST_ACTION);
//                            IntentFilter filter = new IntentFilter(
//                                    ConnectivityManager.CONNECTIVITY_ACTION);
//                            filter.addAction(GEOFENCE_BROADCAST_ACTION);
//                            getActivity().registerReceiver(mGeoFenceReceiver, filter);
//                            if (status == 1) {
//                                etText.setVisibility(View.GONE);
//                            }
//                        } else {
//                            //geoFenceList就是已经添加的围栏列表
//                            Toast.makeText(getActivity(), "添加围栏失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                };
//
//
//                //设置回调监听
//                mGeoFenceClient.setGeoFenceListener(fenceListenter);
//
//            } else {
//                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
//                Log.e("AmapErr", errText);
//            }
//        }
//    }

    private BroadcastReceiver mGeoFenceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GEOFENCE_BROADCAST_ACTION)) {
                //获取Bundle
                Bundle bundle = intent.getExtras();
                //获取围栏行为：
                status = bundle.getInt(GeoFence.BUNDLE_KEY_FENCESTATUS);
                if (status == 1) {
                    etText.setVisibility(View.GONE);
                }
            }
        }
    };

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //单次定位
            mLocationOption.setOnceLocation(true);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                //开始定位
                mlocationClient.startLocation();//启动定位
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //开始定位
                    mlocationClient.startLocation();//启动定位
                } else {
                    Toast.makeText(getActivity(), "权限被拒绝", Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        //会清除所有围栏
        mGeoFenceClient.removeGeoFence();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.TAG_TWO:
//                    AMap aMap = mapView.getMap();
//                    //隐藏缩放按钮
//                    aMap.getUiSettings().setZoomControlsEnabled(false);
//                    //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//                    // 卫星地图模式
//                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
//                    //缩放比例
//                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
//
//                    //实例化地理围栏客户端
//                    mGeoFenceClient = new GeoFenceClient(getActivity());
//
//                    //设置定位监听
//                    aMap.setLocationSource((LocationSource) MyApplication.getContextObject());
//                    //设置显示定位按钮 并且可以点击
//                    UiSettings settings = aMap.getUiSettings();
//                    // 是否显示定位按钮
//                    settings.setMyLocationButtonEnabled(true);
//                    // 是否可触发定位并显示定位层
//                    aMap.setMyLocationEnabled(true);
                    DPoint centerPoint = new DPoint();
                    //设置中心点经度
                    centerPoint.setLatitude(Double.parseDouble(preferencesHelper.getData(getActivity(), "longitude", "")));
                    centerPoint.setLongitude(Double.parseDouble(preferencesHelper.getData(getActivity(), "latitude", "")));
                    mGeoFenceClient.addGeoFence(centerPoint, Float.parseFloat(preferencesHelper.getData(getActivity(), "radius", "")), "221355");
                    if (status == 1) {
                        etText.setVisibility(View.GONE);
                    }
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(getActivity(), messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_ONE:
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("tag", "false");
                    new SharedPreferencesHelper(getActivity(), "login").removeData(getActivity());
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
                    Toast.makeText(getActivity(), messageT, Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_TWO:
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case Constant.TAG_ONE:
                    Toast.makeText(getActivity(), "请填写签到说明", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
