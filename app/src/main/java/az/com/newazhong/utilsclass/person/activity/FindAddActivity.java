package az.com.newazhong.utilsclass.person.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.bean.AddFind;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.presenter.AddFindPresenter;
import az.com.newazhong.utilsclass.presenter.presenterimpl.AddFindPresenterimpl;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.utils.WXPhoto.ImagesSelectorActivity;
import az.com.newazhong.utilsclass.utils.WXPhoto.SelectorSettings;
import az.com.newazhong.utilsclass.view.AddFindView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindAddActivity extends BaseActivity implements AddFindView {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.rlMap)
    RelativeLayout rlMap;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.imageViewAdd01)
    ImageView imageViewAdd01;
    @Bind(R.id.imageViewAdd2)
    ImageView imageViewAdd2;
    @Bind(R.id.imageViewAdd03)
    ImageView imageViewAdd03;
    @Bind(R.id.rbOpen)
    RadioButton rbOpen;
    @Bind(R.id.rbClose)
    RadioButton rbClose;
    @Bind(R.id.radionGroup)
    RadioGroup radionGroup;
    @Bind(R.id.btnSend)
    Button btnSend;

    private static final int MY_PERMISSIONS_MY_UP_IMAGE = 1;
    private ArrayList<String> mResults = new ArrayList<>();
    SharedPreferencesHelper preference;
    AddFindPresenter presenter;
    String session, messageT;
    Intent intent;
    Matrix matrix;
    File tmpDir;
    String result = null;
    String getMessageT;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        preference = new SharedPreferencesHelper(this, "login");
        Intent intent = getIntent();
        tvAddress.setText(intent.getStringExtra("address"));
        presenter = new AddFindPresenterimpl(this, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_findadd;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({R.id.rlMap, R.id.imageViewAdd01, R.id.rbOpen, R.id.rbClose, R.id.btnSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlMap:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewAdd01:
                if (ContextCompat.checkSelfPermission(FindAddActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(FindAddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(FindAddActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Intent intent = new Intent(FindAddActivity.this, ImagesSelectorActivity.class);
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    startActivityForResult(intent, MY_PERMISSIONS_MY_UP_IMAGE);
                }
                break;
            case R.id.btnSend:
                if (tvAddress.getText().toString().length() == 0) {
                    Toast.makeText(this, "发生地点不能为空", Toast.LENGTH_SHORT).show();
                } else if (etAddress.getText().toString().length() == 0) {
                    Toast.makeText(this, "详细地点不能为空", Toast.LENGTH_SHORT).show();
                } else if (tmpDir == null) {
                    Toast.makeText(this, "请上传照片", Toast.LENGTH_SHORT).show();
                } else if (etContent.getText().toString().length() == 0){
                    Toast.makeText(this, "事件描述不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    String content = "<div><p>手机定位：" + tvAddress.getText().toString() + "</p><p>用户手填地址：" + etAddress.getText().toString() + "</p><p>图片：" + tmpDir + ".avator.png" + "</p><p>文字说明：" + etContent.getText().toString() + "</p></div>";
                    String open = null;
                    if (rbOpen.isChecked()) {
                        open = "true";
                    }
                    if (rbClose.isChecked()) {
                        open = "false";
                    }
                    JumpPage(session, content, open);
                }
                break;
        }
    }

    private void JumpPage(final String session, final String content, final String open) {
        ProgressDialogUtil.startLoad(this, "上传数据中");
        final String url0 = Constant.BASE_URL + "upload";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadFile(tmpDir, url0);
                    }
                }).start();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(FindAddActivity.this, ImagesSelectorActivity.class);
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
                    imageViewAdd01.setImageBitmap(newbitmap);
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
        File img = new File(tmpDir.getAbsolutePath() + "/avator.png");
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
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

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void getAddFindView(AddFind addFind) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.TAG_ONE:
                    Toast.makeText(FindAddActivity.this, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FindAddActivity.this, LoginActivity.class);
                    intent.putExtra("tag","false");
                    preference.removeData(FindAddActivity.this);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    if (messageT.equals("null")){
//                        Intent intent1 = new Intent(FindAddActivity.this, MainActivity.class);
//                        intent1.putExtra("tag", "1");
//                        startActivity(intent1);
                        ProgressDialogUtil.stopLoad();
                        finish();
                    }else {
                        Toast.makeText(FindAddActivity.this, messageT, Toast.LENGTH_SHORT).show();
                        ProgressDialogUtil.stopLoad();
                    }
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(FindAddActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                    Intent intent1 = new Intent(FindAddActivity.this, MainActivity.class);
//                    intent1.putExtra("tag", "1");
//                    startActivity(intent1);
                    ProgressDialogUtil.stopLoad();
                    finish();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(FindAddActivity.this, messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    public void uploadFile(File tmpDir, String url) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        session = preference.getData(this, "session", "");
        post.addHeader("Cookie", session);
        MultipartEntity muti = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
                Charset.forName("UTF-8"));
        // MultipartEntity muti=new MultipartEntity();
        if (tmpDir != null) {
            File filAbs = new File(tmpDir, "avator.png");
            FileBody fileBody = new FileBody(filAbs);
            muti.addPart("file", fileBody);
            try {
                muti.addPart("uploadType", new StringBody("RICH_TEXT", Charset.forName("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        post.setEntity(muti);
        //执行这个请求
        try {
            HttpResponse response = client.execute(post);
            Log.d("responce", String.valueOf(response.getStatusLine().getStatusCode()));
            if (response.getStatusLine().getStatusCode() == 401) {
                Message message = new Message();
                message.what = Constant.TAG_ONE;
                handler.sendMessage(message);
            } else if (response.getStatusLine().getStatusCode() == 201){
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    path = jsonObjectData.getString("accessPath");
                    try {
                        //建立连接
                        final String url1 = Constant.BASE_URL + "matter";
                        boolean open = false;
                        String gpsAddress = URLEncoder.encode(tvAddress.getText().toString(),"UTF-8");
                        if (rbOpen.isChecked()) {
                            open = true;
                        }
                        if (rbClose.isChecked()) {
                            open = false;
                        }
                        String content = URLEncoder.encode(etContent.getText().toString(),"UTF-8");
                        String address = URLEncoder.encode(etAddress.getText().toString().trim(),"UTF-8");
                        URL url2 = new URL(url1);
                        HttpURLConnection httpConn = (HttpURLConnection) url2.openConnection();
                        httpConn.setDoOutput(true);
                        httpConn.setDoInput(true);
                        httpConn.setUseCaches(false);
                        httpConn.setRequestMethod("POST");
                        String parm = "description=" + content + "&address=" + address+ "&gpsAddress=" + gpsAddress +"&pictures=" + path +"&open=" + open;
                        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
                        httpConn.setRequestProperty("Charset", "UTF-8");
                        httpConn.addRequestProperty("Cookie", session);
                        httpConn.connect();
                        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                        dos.write(parm.getBytes());
                        dos.flush();
                        dos.close();

                        //获得响应状态
                        int resultCode = httpConn.getResponseCode();
                        InputStream inputStream = null;
                        if (resultCode == 401){
                            Message message = new Message();
                            message.what = Constant.TAG_ONE;
                            handler.sendMessage(message);
                        }else if (resultCode == 201){
                            Message message = new Message();
                            message.what = Constant.TAG_THREE;
                            handler.sendMessage(message);
                        }else {
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
                            message.what = Constant.TAG_TWO;
                            handler.sendMessage(message);
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
            }else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    messageT = jsonObject.getString("message");
                }
                Message message = new Message();
                message.what = Constant.TAG_FOUR;
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
