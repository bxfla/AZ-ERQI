package az.com.newazhong.propagandwindow.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.bean.ShowPropagandWindow;
import az.com.newazhong.propagandwindow.utils.MessageEvent;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkPropagandDetailActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvDepartment)
    TextView tvDepartment;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.webView)
    WebView webView;
    private DisplayImageOptions options;
    ShowPropagandWindow showPropagandWindow;
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
        showPropagandWindow = (ShowPropagandWindow) intent.getSerializableExtra("bean");
        tvTitle.setText(showPropagandWindow.getTitle());
        tvDepartment.setText(showPropagandWindow.getName());
        tvTime.setText(showPropagandWindow.getTime());
//        WindowManager manager = this.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        width = outMetrics.widthPixels;
//        int height = outMetrics.heightPixels;

        // 通过Activity类中的getWindowManager()方法获取窗口管理，再调用getDefaultDisplay()方法获取获取Display对象
        Display display = getWindowManager().getDefaultDisplay();

        // 方法一(推荐使用)使用Point来保存屏幕宽、高两个数据
        Point outSize = new Point();
        // 通过Display对象获取屏幕宽、高数据并保存到Point对象中
        display.getSize(outSize);
        // 从Point对象中获取宽、高
        width = outSize.x;
        int y = outSize.y;


        //tvContent.setText(Html.fromHtml(showPropagandWindow.getContent(), new URLImageParser(tvContent),null));
        //tvContent.setHtml(showPropagandWindow.getContent(),height2);
//        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.loding)
//                .showImageForEmptyUri(R.drawable.loding)
//                .showImageOnFail(R.drawable.loding)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.NONE)//设置图片的缩放模式
//                .displayer(new FadeInBitmapDisplayer(300))
//                .build();
//        tvContent.setText(Html.fromHtml(showPropagandWindow.getContent(), new URLImageGetter(showPropagandWindow.getContent(), WorkPropagandDetailActivity.this, tvContent, options, width2), null));
        webView.loadDataWithBaseURL(null,showPropagandWindow.getContent(),"text/html","utf-8",null);
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

    // 接受EventBus，更新UI
    public void onEventMainThread(MessageEvent event) {
        String Con = event.getMessage();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_workpropagand_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
