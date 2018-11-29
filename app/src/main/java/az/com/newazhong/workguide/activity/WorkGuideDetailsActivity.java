package az.com.newazhong.workguide.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.AlertDialogCallBack;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.MyRecyclerView;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.ActivityCollector;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import az.com.newazhong.utilsclass.utils.CustomTimePicker;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.utils.WXPhoto.ImagesSelectorActivity;
import az.com.newazhong.utilsclass.utils.WXPhoto.SelectorSettings;
import az.com.newazhong.workguide.adapter.WorkGuideYAdapter;
import az.com.newazhong.workguide.bean.WorkGuideList;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static az.com.newazhong.utilsclass.base.Constant.TAG_ONE;
import static az.com.newazhong.utilsclass.person.activity.FindAddActivity.readPictureDegree;
import static az.com.newazhong.utilsclass.person.activity.FindAddActivity.rotaingImageView;

public class WorkGuideDetailsActivity extends BaseActivity implements WorkGuideYAdapter.GetItemPosition {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvReservation)
    TextView tvReservation;
    @Bind(R.id.currentTime)
    TextView currentTime;
    @Bind(R.id.currentDate)
    TextView currentDate;
    @Bind(R.id.tvContent)
    RichTextView tvContent;

    private static final int MY_PERMISSIONS_MY_UP_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final String TAG = "RxPermissionsSample";
    AlertDialogUtil alertDialogUtil;
    CustomTimePicker customTimePicker1, customTimePicker2;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.rePhone)
    RelativeLayout rePhone;
    List<WorkGuideList.DataBean.ContentBean.AuditMaterialsBean> yList = new ArrayList<>();
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.viewb)
    View viewb;
    @Bind(R.id.myRecyclerView)
    MyRecyclerView myRecyclerView;
    WorkGuideYAdapter adapter;
    @Bind(R.id.textView2)
    TextView textView2;
    int position;
    File tmpDir;
    int Itag;
    String Id, messageT;
    WorkGuideList.DataBean.ContentBean bean;
    @Bind(R.id.webView)
    WebView webView;
    private ArrayList<String> mResults = new ArrayList<>();
    List<File> fileList = new ArrayList<>();
    String session;
    String result = null;
    String usertype;
    String path, type;
    int width;
    List<Map<String, String>> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        bean = (WorkGuideList.DataBean.ContentBean) intent.getSerializableExtra("content");
        ProgressDialogUtil.startLoad(this,"解析数据中");
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(false);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(false);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        Display display = getWindowManager().getDefaultDisplay();
        // 方法一(推荐使用)使用Point来保存屏幕宽、高两个数据
        Point outSize = new Point();
        // 通过Display对象获取屏幕宽、高数据并保存到Point对象中
        display.getSize(outSize);
        // 从Point对象中获取宽、高
        width = outSize.x;
        int y = outSize.y;


        webView.loadDataWithBaseURL(null,bean.getContent(),"text/html","utf-8",null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialogUtil.stopLoad();
            }
        },1000);
        tvTitle.setFocusable(true);
        tvTitle.setFocusableInTouchMode(true);
        tvTitle.requestFocus();
        session = new SharedPreferencesHelper(this, "login").getData(this, "session", "");
        usertype = new SharedPreferencesHelper(this, "login").getData(this, "userType", "");
//        if (usertype.equals("false")) {
//            textView2.setVisibility(View.GONE);
//            myRecyclerView.setVisibility(View.GONE);
//            tvReservation.setVisibility(View.GONE);
//        }

        type = String.valueOf(bean.isSeized());
        Id = String.valueOf(bean.getId());
        if (bean.getAuditMaterials().size() == 0) {
            textView2.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < bean.getAuditMaterials().size(); i++) {
                yList.add(bean.getAuditMaterials().get(i));
            }
        }
        tvTitle.setText(bean.getName());
        tvContent.setHtml(bean.getContent(), 500);
        alertDialogUtil = new AlertDialogUtil(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(manager);
        adapter = new WorkGuideYAdapter(this, yList, usertype, type);
        myRecyclerView.setAdapter(adapter);
        adapter.setOnInnerItemOnClickListener(this);
        if(yList.size() == 0){
            tvReservation.setVisibility(View.GONE);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数
            //addImageClickListner();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto'; img.style.width = +"+width*2+"+'px';  " +
                "}" +
                "})()");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_workguide_details;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    // 解除Evenbus
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @OnClick({R.id.tvReservation, R.id.imageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvReservation:
                alertDialogUtil.showDialog(getResources().getString(R.string.areyousure_reservation),
                        new AlertDialogCallBack() {
                            //确定
                            @Override
                            public void confirm() {
                                if (yList.size() != imageList.size()) {
                                    Toast.makeText(WorkGuideDetailsActivity.this, "请添加所有资料图片", Toast.LENGTH_SHORT).show();
                                } else {
//                                    for (int i = 0; i < yList.size(); i++) {
//                                        Map<String, String> map = new HashMap<String, String>();
//                                        String audit = JSON.toJSONString(bean.getAuditMaterials().get(i));
//                                        JSONObject jsonObject = new JSONObject();
//                                        try {
//                                            jsonObject.put("auditMaterial", audit);
//                                        } catch (org.json.JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        //map.put("auditMaterial", audit);
//                                        for (int j = 0; j < imageList.size(); j++) {
//                                            for (String kry : imageList.get(j).keySet()) {
//                                                //map.put("value", String.valueOf(imageList.get(j).get(kry)));
//                                                try {
//                                                    jsonObject.put("value", String.valueOf(imageList.get(i).get(kry)));
//                                                } catch (org.json.JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                        }
//                                        contentList.add(map);
//                                        Object[] objects = contentList.toArray();
//                                        Log.d("objects:", Arrays.toString(objects).replace("=", ":"));
//                                        final String content = Arrays.toString(objects).replace("=", ":");
//                                        final String jsId = new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").getData(WorkGuideDetailsActivity.this, "session", "");
//                                        new Thread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                String url0 = Constant.BASE_URL + "applyGuide";
//                                                String parme;
//                                                try {
//                                                    parme = "guideId=" + URLEncoder.encode(Id, "UTF-8") + "&commands=" + URLEncoder.encode(content, "UTF-8");
//                                                    //建立连接
//                                                    URL url = new URL(url0);
//                                                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//
//                                                    //设置参数
//                                                    httpConn.setDoOutput(true);
//                                                    httpConn.setDoInput(true);
//                                                    httpConn.setUseCaches(false);
//                                                    httpConn.setRequestMethod("POST");
//
//                                                    //设置请求属性
//                                                   // httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                                                    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
//                                                   // httpConn.setRequestProperty("Charset", "UTF-8");
//                                                    httpConn.addRequestProperty("Cookie", jsId);
//                                                    httpConn.setInstanceFollowRedirects(false);
//                                                    httpConn.connect();
//                                                    //建立输入流，向指向的URL传入参数
//                                                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
//                                                    dos.writeBytes(parme);
//                                                    dos.flush();
//                                                    dos.close();
//
//                                                    //获得响应状态
//                                                    int resultCode = httpConn.getResponseCode();
//                                                    InputStream inputStream = null;
//                                                    if (resultCode == 200) {
//                                                        inputStream = new BufferedInputStream(httpConn.getInputStream());
//                                                    } else {
//                                                        inputStream = new BufferedInputStream(httpConn.getErrorStream());
//                                                    }
//                                                    BufferedReader responseReader;
////                                                  //处理响应流
//                                                    responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
////                                                  //当正确响应时处理数据
//                                                    StringBuffer buffer = new StringBuffer();
//                                                    String readLine;
////                                                  BufferedReader responseReader;
//                                                    //处理响应流
//                                                    //  responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                                                    while ((readLine = responseReader.readLine()) != null) {
//                                                        buffer.append(readLine);
//                                                    }
//                                                    responseReader.close();
//                                                    Log.d("HttpPOST", buffer.toString());
//                                                    JSONObject jsonObject = new JSONObject(buffer.toString());
//                                                    messageT = jsonObject.getString("message");
//                                                    Message message = new Message();
//                                                    message.what = Constant.TAG_THREE;
//                                                    handlerToast.sendMessage(message);
//                                                } catch (UnsupportedEncodingException e) {
//                                                    e.printStackTrace();
//                                                } catch (MalformedURLException e) {
//                                                    e.printStackTrace();
//                                                } catch (IOException e) {
//                                                    e.printStackTrace();
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                } catch (org.json.JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                        }).start();
//                                    }
                                    ProgressDialogUtil.startLoad(WorkGuideDetailsActivity.this, "上传参数中");
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            sendImage(imageList);
                                        }
                                    }).start();

                                }
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                break;
            case R.id.imageView:
                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(WorkGuideDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(WorkGuideDetailsActivity.this, Manifest.permission.CALL_PHONE)) {
                        // 返回值：
                        //如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
                        //如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
                        //如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(WorkGuideDetailsActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(WorkGuideDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
                break;
        }
    }

    /**
     * 申请完权限之后拨打电话
     */

    private void CallPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + tvPhone.getText().toString().trim()));
        //开启系统拨号器
        startActivity(intent);
    }

    @Override
    public void getPosition(int position, String tag) {
        this.position = position;
        Itag = position;
        View layout = myRecyclerView.getLayoutManager().findViewByPosition(position);// 获得子item的layout
        imageView = (ImageView) layout.findViewById(R.id.imaheView);
        String session = new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").getData(WorkGuideDetailsActivity.this, "session", "");
        if (session.equals("")) {
            Intent intent = new Intent(WorkGuideDetailsActivity.this, LoginActivity.class);
            intent.putExtra("tag", "false");
            new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").removeData(WorkGuideDetailsActivity.this);
            startActivity(intent);
        }else if (new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").getData(WorkGuideDetailsActivity.this, "userType", "").equals("false")){
            Toast.makeText(this, "工作人员身份不允许提交办事事项。", Toast.LENGTH_SHORT).show();
        }else {
            if (tag.equals("photo")) {
                if (ContextCompat.checkSelfPermission(WorkGuideDetailsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(WorkGuideDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(WorkGuideDetailsActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            TAG_ONE);
                } else {
                    Intent intent = new Intent(WorkGuideDetailsActivity.this, ImagesSelectorActivity.class);
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    startActivityForResult(intent, TAG_ONE);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(WorkGuideDetailsActivity.this, ImagesSelectorActivity.class);
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    startActivityForResult(intent, MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Toast.makeText(this, "权限被拒绝，请手动开启", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (resultCode == RESULT_OK) {
                    mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                    assert mResults != null;

                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                    for (String result : mResults) {
                        sb.append(result).append("\n");
                    }
                }
                BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
                opts.inSampleSize = 2;
                if (mResults.size() == 1) {
                    int degree = readPictureDegree(mResults.get(0));
                    Bitmap cbitmap = BitmapFactory.decodeFile(mResults.get(0), opts);
                    Bitmap newbitmap = rotaingImageView(degree, cbitmap);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(newbitmap);
                    saveBitmap(newbitmap, "temp");
                }
//                    Bitmap btmUp = Bitmap.createBitmap(cbitmap01, 0, 0, cbitmap01.getWidth(),
//                            cbitmap01.getHeight(), matrix, true);
                //base64DataUp = bitmapToBase64(btmUp);
//                } else if (mResults.size() == 2) {
//                    imageViewAdd2.setVisibility(View.VISIBLE);
//                    Bitmap cbitmap01 = BitmapFactory.decodeFile(mResults.get(0), opts);
//                    imageViewAdd01.setImageBitmap(cbitmap01);
////                    Bitmap btmUp = Bitmap.createBitmap(cbitmap01, 0, 0, cbitmap01.getWidth(),
////                            cbitmap01.getHeight(), matrix, true);
//                    Bitmap cbitmap02 = BitmapFactory.decodeFile(mResults.get(1), opts);
//                    imageViewAdd2.setImageBitmap(cbitmap02);
//                    //base64DataUp = bitmapToBase64(btmUp);
//                } else if (mResults.size() == 3) {
//                    imageViewAdd2.setVisibility(View.VISIBLE);
//                    imageViewAdd03.setVisibility(View.VISIBLE);
//                    Bitmap cbitmap01 = BitmapFactory.decodeFile(mResults.get(0), opts);
//                    imageViewAdd01.setImageBitmap(cbitmap01);
//                    Bitmap cbitmap02 = BitmapFactory.decodeFile(mResults.get(1), opts);
//                    imageViewAdd2.setImageBitmap(cbitmap02);
//                    Bitmap cbitmap03 = BitmapFactory.decodeFile(mResults.get(2), opts);
//                    imageViewAdd03.setImageBitmap(cbitmap03);
//                    Bitmap btmUp = Bitmap.createBitmap(cbitmap03, 0, 0, cbitmap03.getWidth(),
//                            cbitmap03.getHeight(), matrix, true);
//                    //base64DataUp = bitmapToBase64(btmUp);
//                }
                break;
        }
    }

    /**
     * 将Bitmap写入SD卡中的一个文件中,并返回写入文件的Uri
     *
     * @param bm
     * @param dirPath
     * @return
     */
    private Uri saveBitmap(Bitmap bm, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        //新建文件存储裁剪后的图片
        File img = new File(tmpDir.getAbsolutePath() + "/avator" + Itag + ".png");
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
//            if (!fileList.contains(img)) {
//                fileList.add(img);
//                Map<String, File> map = new HashMap<>();
//                map.put(String.valueOf(Itag), img);
//                imageList.add(map);
//            }

            ProgressDialogUtil.startLoad(WorkGuideDetailsActivity.this, "提交数据中");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String urlImage = Constant.BASE_URL + "upload";
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(urlImage);
                    session = new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").getData(WorkGuideDetailsActivity.this, "session", "");
                    post.addHeader("Cookie", session);
                    MultipartEntity muti = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
                            Charset.forName("UTF-8"));
                    // MultipartEntity muti=new MultipartEntity();
                    File filAbs = new File(tmpDir, "avator" + Itag + ".png");
                    FileBody fileBody = new FileBody(filAbs);
                    muti.addPart("file", fileBody);
                    try {
                        muti.addPart("uploadType", new StringBody("GUIDE", Charset.forName("UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    post.setEntity(muti);
                    //执行这个请求
                    try {
                        HttpResponse response = client.execute(post);
                        Log.d("code", String.valueOf(response.getStatusLine().getStatusCode()));
                        if (response.getStatusLine().getStatusCode() == 401) {
                            Message message = new Message();
                            message.what = Constant.TAG_ONE;
                            handler.sendMessage(message);
                        } else if (response.getStatusLine().getStatusCode() == 201) {
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                result = EntityUtils.toString(entity, "utf-8");
                                JSONObject jsonObject = new JSONObject(result);
                                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                                path = jsonObjectData.getString("accessPath");
                                Map<String, String> map = new ArrayMap<>();
                                map.put(Itag + "", path);
                                imageList.add(map);
                            }
                            Message message = new Message();
                            message.what = Constant.TAG_THREE;
                            handler.sendMessage(message);
                        } else {
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                result = EntityUtils.toString(entity, "utf-8");
//                                JSONObject jsonObject = new JSONObject(result);
//                                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
//                                path = jsonObjectData.getString("accessPath");
//                                Map<String, String> map = new ArrayMap<>();
//                                map.put(Itag + "", path);
//                                imageList.add(map);
                            }
                            Message message = new Message();
                            message.what = Constant.TAG_TWO;
                            handler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            //返回File类型的Uri
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    //添加图片
    private void sendImage(List<Map<String, String>> imageList) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < yList.size(); i++) {
            JSONObject jsonObjectSA = new JSONObject();
            JSONObject jsonObjectSSA = new JSONObject();
            // String audit = JSON.toJSONString(bean.getAuditMaterials().get(i));
            try {
                jsonObjectSSA.put("memo", bean.getAuditMaterials().get(i).getMemo().toString());
                jsonObjectSSA.put("name", bean.getAuditMaterials().get(i).getName().toString());
                jsonObjectSSA.put("required", bean.getAuditMaterials().get(i).isRequired());
                jsonObjectSSA.put("sort", bean.getAuditMaterials().get(i).getSort());
                jsonObjectSA.put("auditMaterial", jsonObjectSSA);
                jsonObjectSA.put("value", imageList.get(i).get(String.valueOf(i)));
                jsonArray.put(jsonObjectSA);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }
        String url0 = Constant.BASE_URL + "applyGuide" + "?guideId=" + Id;
        String parme;
        try {
            parme = jsonArray.toString();
            //建立连接
            URL url2 = new URL(url0);
            HttpURLConnection httpConn = (HttpURLConnection) url2.openConnection();

            //设置参数
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            session = new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").getData(WorkGuideDetailsActivity.this, "session", "");
            httpConn.addRequestProperty("Cookie", session);
            //设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            httpConn.setInstanceFollowRedirects(false);
            httpConn.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
            dos.write(parme.getBytes());
            dos.flush();
            dos.close();

            //获得响应状态
            int resultCode = httpConn.getResponseCode();
            InputStream inputStream = null;
            if (resultCode == 401) {
                Message message = new Message();
                message.what = Constant.TAG_ONE;
                handlerToast.sendMessage(message);
            } else if (resultCode == 201) {
                Message message = new Message();
                message.what = Constant.TAG_TWO;
                handlerToast.sendMessage(message);
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
                messageT = jsonObjectC.getString("message") + "";
                Message message = new Message();
                message.what = Constant.TAG_THREE;
                handlerToast.sendMessage(message);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Handler handlerToast = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    Intent intent = new Intent(WorkGuideDetailsActivity.this, LoginActivity.class);
                    intent.putExtra("tag", "false");
                    new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").removeData(WorkGuideDetailsActivity.this);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(WorkGuideDetailsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    finish();
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(WorkGuideDetailsActivity.this, messageT, Toast.LENGTH_SHORT).show();
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
                case Constant.TAG_ONE:
                    Intent intent = new Intent(WorkGuideDetailsActivity.this, LoginActivity.class);
                    intent.putExtra("tag", "false");
                    new SharedPreferencesHelper(WorkGuideDetailsActivity.this, "login").removeData(WorkGuideDetailsActivity.this);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(WorkGuideDetailsActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };
}
