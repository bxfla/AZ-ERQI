package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.regex.Pattern;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.workoffice.bean.TypeFixRCAP;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TypeFixTypeRCAPActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvStartTime)
    TextView tvStartTime;
    @Bind(R.id.tvEndTime)
    TextView tvEndTime;

    TypeFixRCAP.DataBean.ContentBean typeFix;
    @Bind(R.id.webView)
    WebView webView;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

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
        Intent intent = getIntent();
        typeFix = (TypeFixRCAP.DataBean.ContentBean) intent.getSerializableExtra("bean");
        Pattern pen = Pattern.compile(" ");
        String[] temp = pen.split(typeFix.getStartDate());
        String[] temp1 = pen.split(typeFix.getEndDate());
        tvStartTime.setText("开始时间："+temp[0]);
        tvEndTime.setText("结束时间："+temp1[0]);

        Display display = getWindowManager().getDefaultDisplay();
        // 方法一(推荐使用)使用Point来保存屏幕宽、高两个数据
        Point outSize = new Point();
        // 通过Display对象获取屏幕宽、高数据并保存到Point对象中
        display.getSize(outSize);
        // 从Point对象中获取宽、高
        width = outSize.x;
        int y = outSize.y;


        webView.loadDataWithBaseURL(null,typeFix.getContent(),"text/html","utf-8",null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialogUtil.stopLoad();
            }
        },1000);
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
        return R.layout.activity_rcap_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
