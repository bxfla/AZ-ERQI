package az.com.newazhong.propagandwindow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.bean.Test;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseFastJSONRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.utils.FastUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuYueActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.btnSure)
    Button btnSure;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_yu_yue;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick(R.id.btnSure)
    public void onViewClicked() {
        String name = etName.getText().toString().trim();
        String Phone = etPhone.getText().toString().trim();
        if (name.length() == 0) {
            Toast.makeText(this, "注册人姓名不能为空", Toast.LENGTH_SHORT).show();
        } else if (Phone.length() == 0) {
            Toast.makeText(this, "注册人电话不能为空", Toast.LENGTH_SHORT).show();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendData();
                }
            }).start();
        }
    }

    private void sendData() {
        String url1 = Constant.BASE_URL + "registration";
        FastUtils.postFastData(url1, new BaseFastJSONRequestBackLisenter() {
            @Override
            public void success(String o) {
                Test resultBeanData = JSON.parseObject(o,Test.class);
//                if (resultBeanData.getData() == null) {
//                    llNoContent.setVisibility(View.VISIBLE);
//                    pullLoadMoreRecyclerView.setVisibility(View.GONE);
//                } else {
//                    for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
//                        if (resultBeanData.getData().getContent().get(i).getArticle().getStatus().equals("UNAUDITED")){
//                            ShowActivityPhoto showActivityPhoto = new ShowActivityPhoto();
//                            showActivityPhoto.title = resultBeanData.getData().getContent().get(i).getArticle().getTitle();
//                            showActivityPhoto.content = String.valueOf(resultBeanData.getData().getContent().get(i).getArticle().getContent());
//                            showActivityPhoto.name = resultBeanData.getData().getContent().get(i).getDepartmentName();
//                            showActivityPhoto.time = resultBeanData.getData().getContent().get(i).getCreateTime();
//                            showActivityPhoto.id = String.valueOf(resultBeanData.getData().getContent().get(i).getId());
//                            beanList.add(showActivityPhoto);
//                        }
//                    }
//                    setAdapterData();
//                }
            }

            @Override
            public void fail(String message) {
                Log.e("XXX","解析成功失败");
            }
        });
//        String param = null;
//        String session = new SharedPreferencesHelper(this, "login").getData(this, "session", "");
//        String url1 = Constant.BASE_URL + "registration";
//        try {
//            param = "booker=" + URLEncoder.encode(etName.getText().toString().trim(), "UTF-8")
//                    + "&mobile=" + URLEncoder.encode(etPhone.getText().toString().trim(), "UTF-8")
//                    + "&mienId=" + URLEncoder.encode(id, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            //建立连接
//            URL url = new URL(url1);
//            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//
//            //设置参数
//            httpConn.setDoOutput(true);     //需要输出
//            httpConn.setRequestMethod("POST");      //设置POST方式连接
////            httpConn.setRequestProperty("ser-Agent", "Fiddler");
////            //设置请求属性
////            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
////            httpConn.setRequestProperty("Charset", "UTF-8");
//            httpConn.addRequestProperty("Cookie",session);
//            httpConn.setInstanceFollowRedirects(false);
//            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
//            httpConn.connect();
//            //建立输入流，向指向的URL传入参数
//            DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
//            dos.writeBytes(param);
//            dos.flush();
//            dos.close();
//
//            //获得响应状态
//            int resultCode = httpConn.getResponseCode();
//
//            if (HttpURLConnection.HTTP_OK == resultCode) {
//                Message message = new Message();
//                message.what = Constant.TAG_ONE;
//                handler.sendMessage(message);
//            }else {
//                StringBuffer buffer = new StringBuffer();
//                String readLine;
//                BufferedReader responseReader;
//                //处理响应流
//                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
//                while ((readLine = responseReader.readLine()) != null) {
//                    buffer.append(readLine).append("\n");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(YuYueActivity.this, "预约成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
