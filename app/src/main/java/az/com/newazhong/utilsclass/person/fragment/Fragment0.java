package az.com.newazhong.utilsclass.person.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.communityprofile.activity.CommunityProfileActivity;
import az.com.newazhong.noticeandannouncement.Activity.NoticeActivity;
import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.noticeandannouncement.bean.ShowNotice;
import az.com.newazhong.noticeandannouncement.presenter.NoticePresenter;
import az.com.newazhong.noticeandannouncement.presenter.presenterimpl.NoticePresenterimpl;
import az.com.newazhong.noticeandannouncement.view.NoticeView;
import az.com.newazhong.partyBuildingPropaganda.PropagListActivity;
import az.com.newazhong.partypropaganda.PartyPropagandaActivity;
import az.com.newazhong.propagandwindow.activity.CommunityDemeanorActivity;
import az.com.newazhong.propagandwindow.activity.WorkPropagandDetailActivity;
import az.com.newazhong.propagandwindow.activity.WorkTrendsActivity;
import az.com.newazhong.propagandwindow.bean.PropagandWindow;
import az.com.newazhong.propagandwindow.bean.ShowPropagandWindow;
import az.com.newazhong.propagandwindow.presenter.PropagandWindowPresenter;
import az.com.newazhong.propagandwindow.presenter.presenterimpl.PropagandWindowPresenterimpl;
import az.com.newazhong.propagandwindow.utils.MessageEvent;
import az.com.newazhong.propagandwindow.view.PropagandWindowView;
import az.com.newazhong.street12345.activity.Street12345Activity;
import az.com.newazhong.utilsclass.base.BaseFragment;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.bean.Banner;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.utilsclass.myViews.ScrollForeverTextView;
import az.com.newazhong.utilsclass.presenter.BannerPresenter;
import az.com.newazhong.utilsclass.presenter.presenterimpl.BannerPresenterimpl;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import az.com.newazhong.utilsclass.utils.GlideImageLoader;
import az.com.newazhong.utilsclass.view.BannerView;
import az.com.newazhong.workguide.activity.WorkGuideActivity;
import az.com.newazhong.workoffice.activity.WorkOfficeListActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static az.com.newazhong.utilsclass.utils.ListToString.ListToString;

/**
 * Created by dell on.
 */

public class Fragment0 extends BaseFragment implements BannerView, PropagandWindowView, NoticeView {
    @Bind(R.id.header)
    Header header;
    @Bind(R.id.tvSeeItNow)
    ScrollForeverTextView tvSeeItNow;
    @Bind(R.id.rbCommunityProfile)
    RadioButton rbCommunityProfile;
    @Bind(R.id.rbThePartyPropaganda)
    RadioButton rbThePartyPropaganda;
    @Bind(R.id.rbCatalogAndAnnouncemen)
    RadioButton rbCatalogAndAnnouncemen;
    @Bind(R.id.rbWorkGuide)
    RadioButton rbWorkGuide;
    @Bind(R.id.rbCommunity123)
    RadioButton rbCommunity123;
    @Bind(R.id.rbAdministrativeOffice)
    RadioButton rbAdministrativeOffice;
    @Bind(R.id.moreNew)
    TextView moreNew;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    RichTextView tvContent;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_title1)
    TextView tvTitle1;
    @Bind(R.id.tv_content1)
    RichTextView tvContent1;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    @Bind(R.id.banner)
    com.youth.banner.Banner banner;
    @Bind(R.id.newItem01)
    LinearLayout newItem01;
    @Bind(R.id.newItem02)
    LinearLayout newItem02;
    @Bind(R.id.rbWorkStaty)
    RadioButton rbWorkStaty;
    @Bind(R.id.comdBetaf)
    RadioButton comdBetaf;
    @Bind(R.id.rbWorkOffice)
    RadioButton rbWorkOffice;
    private View view;
    private Intent intent;

    AlertDialogUtil alertDialogUtil;
    List<ShowNotice> beanNoticeList = new ArrayList<>();
    List<ShowPropagandWindow> beanList = new ArrayList<>();
    List<String> imageList = new ArrayList<String>();
    List<String> titleList = new ArrayList<String>();
    List<String> noticeList = new ArrayList<String>();
    PropagandWindowPresenter propagandWindowPresenter;
    BannerPresenter bannerPresenter;
    NoticePresenter noticePresenter;
    SharedPreferencesHelper sharedPreferencesHelper;
    String title;
    private static AlertDialog dialog = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment0, container, false);

        LayoutInflater flat = LayoutInflater.from(getActivity());
        View v = flat.inflate(R.layout.loading, null);
        // v.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        // 创建对话
        dialog = new AlertDialog.Builder(getActivity(), R.style.dialog).create();
        // 设置返回键点击消失对话框
        dialog.setCancelable(false);
        // 设置点击返回框外边不消失
        dialog.setCanceledOnTouchOutside(false);
        // 给该对话框增加系统权限
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        // 显示对话
        dialog.show();
        // 加载控件
        TextView title = (TextView) v.findViewById(R.id.loading_title);
        title.setVisibility(View.VISIBLE);
        title.setText("加载数据中");

        // 必须放到显示对话框下面，否则显示不出效果
        Window window = dialog.getWindow();
        // window.getAttributes().x = 0;
        // window.getAttributes().y = 0;//设置y坐标

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        // params.alpha = 0.6f;
        window.setAttributes(params); // 加载布局组件
        dialog.getWindow().setContentView(v);

        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "login");
        alertDialogUtil = new AlertDialogUtil(getActivity());
        bannerPresenter = new BannerPresenterimpl(this, getActivity());
        bannerPresenter.getBannerImagePresenter();
        Fresco.initialize(getActivity());
        ButterKnife.bind(this, view);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bannerPresenter.getBannerImagePresenter();
                imageList.clear();
                titleList.clear();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userType = sharedPreferencesHelper.getData(getActivity(), "userType", "");
        if (userType.equals("true") || userType.equals("")) {
            rbWorkOffice.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //R.id.rbPropagandaWindow,
    @OnClick({R.id.rbCommunityProfile, R.id.rbThePartyPropaganda,
            R.id.rbCatalogAndAnnouncemen, R.id.rbWorkGuide,
            R.id.rbCommunity123, R.id.rbAdministrativeOffice, R.id.moreNew,
            R.id.newItem01, R.id.newItem02, R.id.tvSeeItNow, R.id.rbWorkStaty, R.id.comdBetaf,
            R.id.rbWorkOffice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbCommunityProfile:
                intent = new Intent(getActivity(), CommunityProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbThePartyPropaganda:
                intent = new Intent(getActivity(), PartyPropagandaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbCatalogAndAnnouncemen:
                intent = new Intent(getActivity(), NoticeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbWorkGuide:
                intent = new Intent(getActivity(), WorkGuideActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbCommunity123:
                intent = new Intent(getActivity(), Street12345Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbAdministrativeOffice:
//                intent = new Intent(getActivity(), SignInActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                intent = new Intent(getActivity(), PropagListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbWorkOffice:
                intent = new Intent(getActivity(), WorkOfficeListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.moreNew:
                intent = new Intent(getActivity(), WorkTrendsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.newItem01:
                if (beanList.size() != 0) {
                    ShowPropagandWindow showPropagandWindow = beanList.get(0);
                    Intent intent = new Intent(MyApplication.getContextObject(), WorkPropagandDetailActivity.class);
                    EventBus.getDefault().post(new MessageEvent(showPropagandWindow.getContent()));
                    intent.putExtra("bean", (Serializable) showPropagandWindow);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    alertDialogUtil.showSmallDialog(getResources().getString(R.string.no_content));
                }
                break;
            case R.id.newItem02:
                if (beanList.size() >= 2) {
                    ShowPropagandWindow showPropagandWindow = beanList.get(1);
                    Intent intent = new Intent(MyApplication.getContextObject(), WorkPropagandDetailActivity.class);
                    EventBus.getDefault().post(new MessageEvent(showPropagandWindow.getContent()));
                    intent.putExtra("bean", (Serializable) showPropagandWindow);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    alertDialogUtil.showSmallDialog(getResources().getString(R.string.no_content));
                }
                break;
            case R.id.tvSeeItNow:
                intent = new Intent(getActivity(), NoticeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rbWorkStaty:
                intent = new Intent(getActivity(), WorkTrendsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.comdBetaf:
                intent = new Intent(getActivity(), CommunityDemeanorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getBannerImage(Banner banner) {
        Message message = new Message();
        if (banner.getData().getContent().size() == 0) {
            message.what = Constant.TAG_ONE;
            handler.sendMessage(message);
        } else {
            imageList.clear();
            for (int i = 0; i < banner.getData().getContent().size(); i++) {
                imageList.add(banner.getData().getContent().get(i).getUrl());
                titleList.add("");
            }
            message.what = Constant.TAG_TWO;
            handler.sendMessage(message);
        }
        noticePresenter = new NoticePresenterimpl(this, getActivity());
        noticePresenter.getNoticePresenter("");
    }

    /**
     * 获取工作动态
     *
     * @param propagandWindow
     */
    @Override
    public void getPropagandWindow(PropagandWindow propagandWindow) {
        beanList.clear();
        if (propagandWindow.getData() != null) {
            for (int i = 0; i < propagandWindow.getData().getContent().size(); i++) {
                ShowPropagandWindow showPropagandWindow = new ShowPropagandWindow();
                if (propagandWindow.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED")
                        && propagandWindow.getData().getContent().get(i).getNews().equals("true")) {
                    showPropagandWindow.title = propagandWindow.getData().getContent().get(i).getArticle().getTitle();
                    showPropagandWindow.content = propagandWindow.getData().getContent().get(i).getArticle().getContent();
                    showPropagandWindow.name = propagandWindow.getData().getContent().get(i).getDepartmentName();
                    showPropagandWindow.time = propagandWindow.getData().getContent().get(i).getCreateTime();
                    beanList.add(showPropagandWindow);
                }
            }
            if (beanList.size() == 1) {
                tvTitle.setText(beanList.get(0).getTitle());
                tvContent.setHtml1(beanList.get(0).getContent(), 500);
            }
            if (beanList.size() != 0 && beanList.size() >= 2) {
                tvTitle.setText(beanList.get(0).getTitle());
                tvContent.setHtml1(beanList.get(0).getContent(), 500);
                tvTitle1.setText(beanList.get(1).getTitle());
                tvContent1.setHtml1(beanList.get(1).getContent(), 500);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    Message message = new Message();
                    message.what = 111;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onActivityCreated(View view, Bundle savedInstanceState) {

    }

    /**
     * 获取跑马灯数据
     *
     * @param notice
     */
    @Override
    public void getNoticeView(Notice notice) {
        if (notice.getData() != null) {
            for (int i = 0; i < notice.getData().getContent().size(); i++) {
                ShowNotice showNotice = new ShowNotice();
                if (notice.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED")
                        && String.valueOf(notice.getData().getContent().get(i).isAttention()).equals("true")) {
                    showNotice.title = notice.getData().getContent().get(i).getArticle().getTitle();
                    showNotice.content = notice.getData().getContent().get(i).getArticle().getContent();
                    showNotice.name = notice.getData().getContent().get(i).getDepartmentName();
                    showNotice.time = notice.getData().getContent().get(i).getCreateTime();
                    beanNoticeList.add(showNotice);
                }
            }
        }
        if (beanNoticeList.size() == 1) {
            title = beanNoticeList.get(0).getTitle();
            title = title + "       " + title + "     " + title + "     " + title + "     ";
            tvSeeItNow.setText(title.replaceAll("#", "  " + "\n" + "\n" + "\n"));
        }
        if (beanNoticeList.size() >= 2) {
            for (int i = 0; i < beanNoticeList.size(); i++) {
                title = beanNoticeList.get(i).getTitle() + "  ";
                noticeList.add(title);
            }
            String centent = ListToString(noticeList);
            Log.d("XXX", centent);
            String ooo = centent.replaceAll("  #", "   ");
            Log.d("XXX2", ooo);
            tvSeeItNow.setText(centent.replaceAll("#", "  " + "\n" + "\n" + "\n"));
        }
        propagandWindowPresenter = new PropagandWindowPresenterimpl(this, getActivity());
        propagandWindowPresenter.getPropagandWindowPresenter();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    Toast.makeText(getActivity(), getResources().getString(R.string.no_iamge)
                            , Toast.LENGTH_SHORT).show();
                    break;
                case Constant.TAG_TWO:
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(imageList);
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置轮播时间
                    banner.setDelayTime(6000);
                    //标题
                    banner.setBannerTitles(titleList);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                    srl.setRefreshing(false);
                    break;
                case 111:
                    dialog.cancel();
                    break;
            }
        }
    };
}