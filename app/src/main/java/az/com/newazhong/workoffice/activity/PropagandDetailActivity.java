package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.workoffice.adapter.PropagandDetailAdapter;
import az.com.newazhong.workoffice.bean.DownloadFile;
import az.com.newazhong.workoffice.bean.Propagand;
import az.com.newazhong.workoffice.bean.Propagand1;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropagandDetailActivity extends BaseActivity implements PropagandDetailAdapter.CallBackPosition {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvStartTime)
    TextView tvStartTime;
    @Bind(R.id.tvEndTime)
    TextView tvEndTime;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    int width;
    Propagand.DataBean.ContentBean bean;
    Propagand1.DataBean.ContentBean bean1;
    List<String> imageList = new ArrayList<>();
    List<DownloadFile> fileList = new ArrayList<>();
    String tag1;
    @Bind(R.id.tvInst)
    TextView tvInst;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.linearLayout1)
    LinearLayout linearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        tag1 = intent.getStringExtra("tag");
        if (tag1.equals("1")) {
            bean = (Propagand.DataBean.ContentBean) intent.getSerializableExtra("bean");
            tvTitle.setText(bean.getTitle());
            tvStartTime.setText(bean.getCreateTime());
            tvEndTime.setText(bean.getModifiedTime());
            tvStartTime.setVisibility(View.GONE);
            tvEndTime.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.GONE);
            if (bean.getAnnexs() != null && bean.getAnnexs().size() != 0) {
                for (int i = 0; i < bean.getAnnexs().size(); i++) {
                    String path = bean.getAnnexs().get(i).getValue();
                    if (path.indexOf(".png") != -1 || path.indexOf(".jpeg") != -1 || path.indexOf(".jpg") != -1) {
                        imageList.add(bean.getAnnexs().get(i).getValue());
                    } else {
                        DownloadFile beanD = new DownloadFile();
                        beanD.setName(bean.getAnnexs().get(i).getName());
                        beanD.setValue(bean.getAnnexs().get(i).getValue());
                        fileList.add(beanD);
                    }
                }
            }
        } else if (tag1.equals("2")) {
            bean1 = (Propagand1.DataBean.ContentBean) intent.getSerializableExtra("bean");
            tvTitle.setText(bean1.getName());
            tvStartTime.setText(bean1.getCreateTime());
            tvEndTime.setText(bean1.getModifiedTime());
            if (bean1.getAnnexs() != null && bean1.getAnnexs().size() != 0) {
                for (int i = 0; i < bean1.getAnnexs().size(); i++) {
                    String path = bean1.getAnnexs().get(i).getValue();
                    if (path.indexOf(".png") != -1 || path.indexOf(".jpeg") != -1 || path.indexOf(".jpg") != -1) {
                        imageList.add(path);
                    } else {
                        DownloadFile beanD = new DownloadFile();
                        beanD.setName(bean1.getAnnexs().get(i).getName());
                        beanD.setValue(bean1.getAnnexs().get(i).getValue());
                        fileList.add(beanD);
                    }
                }
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        PropagandDetailAdapter adapter = new PropagandDetailAdapter(this, fileList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemLitener(this);

        ProgressDialogUtil.startLoad(this, "解析数据中");
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

        int num = 0;
        String imagePath = "";
        while (num < imageList.size()) {
            if (num == 0) {
                imagePath = "<img src=\"" + imagePath + imageList.get(num) + "\" style=\"height:229px; width:376px\" />";
                num += 1;
            } else {
                imagePath = imagePath + "<img src=\"" + imageList.get(num) + "\" style=\"height:229px; width:376px\" />";
                num += 1;
            }
        }
        if (tag1.equals("1")) {
            String data = bean.getContent() + imagePath;
            webView.loadDataWithBaseURL(null, bean.getContent() + imagePath, "text/html", "utf-8", null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ProgressDialogUtil.stopLoad();
                }
            }, 1000);
        } else if (tag1.equals("2")) {
            webView.loadDataWithBaseURL(null, bean1.getContent() + imagePath, "text/html", "utf-8", null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ProgressDialogUtil.stopLoad();
                }
            }, 1000);
        }
    }

    @OnClick(R.id.tvInst)
    public void onViewClicked() {
    }

    @Override
    public void onItemClick(int position) {
        String path = fileList.get(position).getValue();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(path);//此处填链接
        intent.setData(content_url);
        startActivity(intent);
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
                "    img.style.maxWidth = '100%'; img.style.height = 'auto'; img.style.width = +" + width * 2 + "+'px';  " +
                "}" +
                "})()");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_propagand_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
