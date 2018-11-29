package az.com.newazhong.communityprofile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.communityprofile.bean.Community;
import az.com.newazhong.communityprofile.bean.MarkerBean;
import az.com.newazhong.communityprofile.bean.ShowMDept;
import az.com.newazhong.communityprofile.presenter.CommunityPresenter;
import az.com.newazhong.communityprofile.presenter.presenterimpl.CommunityPresenterimpl;
import az.com.newazhong.communityprofile.view.CommunityView;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.myViews.RichTextView;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static az.com.newazhong.R.id.map;

public class CommunityProfileActivity extends BaseActivity implements CommunityView {

    @Bind(R.id.header)
    Header header;
    @Bind(map)
    MapView mapView;
    @Bind(R.id.tvInsideDept)
    TextView tvInsideDept;
    @Bind(R.id.tvNeighborhood)
    TextView tvNeighborhood;
    @Bind(R.id.tvVillage)
    TextView tvVillage;

    List<ShowMDept> beanList = new ArrayList<>();
    CommunityPresenter mDeptPresenter;
    @Bind(R.id.tvcontent)
    RichTextView tvcontent;
    @Bind(R.id.activity_communityprofile)
    LinearLayout activityCommunityprofile;
    private AMap aMap;//地图对象
    Intent intent;
    /**
     * 添加多个market
     */
    private List<MarkerBean> marketList;
    private List<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        imageList.add(R.drawable.marker1);
        imageList.add(R.drawable.marker2);
        imageList.add(R.drawable.marker3);
        imageList.add(R.drawable.marker4);
        imageList.add(R.drawable.marker5);
        imageList.add(R.drawable.marker6);
        imageList.add(R.drawable.marker7);
        imageList.add(R.drawable.marker9);
        //显示地图
        mapView = (MapView) findViewById(map);
        //必须要写
        mapView.onCreate(savedInstanceState);
        //获取地图对象
        aMap = mapView.getMap();
        //aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
        LatLng JDB1 = new LatLng(36.653077, 116.929917);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(JDB1));
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(JDB1));
//        // 定义多边形的5个点点坐标
//        LatLng latLngZ01 = new LatLng(36.66652565, 116.89504623);
//        LatLng latLngZ02 = new LatLng(36.64307971, 116.89277172);
//        LatLng latLngZ03 = new LatLng(36.64473252, 116.91328526);
//        LatLng latLngZ04 = new LatLng(36.65141227, 116.91830635);
//        LatLng latLngZ05 = new LatLng(36.65148113, 116.93306923);
//        LatLng latLngZ06 = new LatLng(36.66638796, 116.93568707);
//        // 声明 多边形参数对象
//        PolygonOptions polygonOptions = new PolygonOptions();
//        // 添加 多边形的每个顶点（顺序添加）
//        polygonOptions.add(latLngZ01, latLngZ02, latLngZ03, latLngZ04, latLngZ05, latLngZ06);
//        polygonOptions.strokeWidth(15) // 多边形的边框
//                .strokeColor(Color.argb(213, 1000, 1, 1)) // 边框颜色
//                .fillColor(Color.argb(1, 1, 1, 1));   // 多边形的填充色
//        aMap.addPolygon(polygonOptions);
//
//        LatLng latLngM01 = new LatLng(36.65140366, 116.91089272);
//        LatLng latLngM02 = new LatLng(36.64733218, 116.91063523);
//        LatLng latLngM03 = new LatLng(36.64730636, 116.91272736);
//        LatLng latLngM04 = new LatLng(36.6513348, 116.91299558);
//
//        // 声明 多边形参数对象
//        PolygonOptions polygonOptions1 = new PolygonOptions();
//        // 添加 多边形的每个顶点（顺序添加）
//        polygonOptions1.add(latLngM01, latLngM02, latLngM03, latLngM04);
//        polygonOptions1.strokeWidth(16) // 多边形的边框
//                .strokeColor(Color.argb(progress, 1, 1, 1)) // 边框颜色
//                .fillColor(Color.argb(progress, 1, 1, 1));// 多边形的填充色
//        aMap.addPolygon(polygonOptions1);
//
//        LatLng latLngT01 = new LatLng(36.65820333, 116.92367077);
//        LatLng latLngT02 = new LatLng(36.65494129, 116.92323089);
//        LatLng latLngT03 = new LatLng(36.65470889, 116.92544103);
//        LatLng latLngT04 = new LatLng(36.65802259, 116.92588091);
//        // 声明 多边形参数对象
//        PolygonOptions polygonOptions2 = new PolygonOptions();
//        // 添加 多边形的每个顶点（顺序添加）
//        polygonOptions2.add(latLngT01, latLngT02, latLngT03, latLngT04);
//        polygonOptions2.strokeWidth(16) // 多边形的边框
//                .strokeColor(Color.argb(355, 227, 80, 5)) // 边框颜色
//                .fillColor(Color.argb(355, 227, 80, 5));   // 多边形的填充色
//        aMap.addPolygon(polygonOptions2);
//
//        LatLng latLngL01 = new LatLng(36.65737708, 116.92607403);
//        LatLng latLngL02 = new LatLng(36.65464864, 116.92568779);
//        LatLng latLngL03 = new LatLng(36.65438182, 116.9291532);
//        LatLng latLngL04 = new LatLng(36.65762667, 116.92962527);
//        // 声明 多边形参数对象
//        PolygonOptions polygonOptions3 = new PolygonOptions();
//        // 添加 多边形的每个顶点（顺序添加）
//        polygonOptions3.add(latLngL01, latLngL02, latLngL03, latLngL04);
//        polygonOptions3.strokeWidth(16) // 多边形的边框
//                .strokeColor(Color.argb(180, 224, 171, 10)) // 边框颜色
//                .fillColor(Color.argb(180, 224, 171, 10));   // 多边形的填充色
//        aMap.addPolygon(polygonOptions3);
//
//        LatLng latLngZZ01 = new LatLng(36.66012262, 116.92633152);
//        LatLng latLngZZ02 = new LatLng(36.65813448, 116.92614913);
//        LatLng latLngZZ03 = new LatLng(36.65786767, 116.92975402);
//        LatLng latLngZZ04 = new LatLng(36.65972672, 116.93018317);
//        // 声明 多边形参数对象
//        PolygonOptions polygonOptions4 = new PolygonOptions();
//        // 添加 多边形的每个顶点（顺序添加）
//        polygonOptions4.add(latLngZZ01, latLngZZ02, latLngZZ03, latLngZZ04);
//        polygonOptions4.strokeWidth(16) // 多边形的边框
//                .strokeColor(Color.argb(180, 200, 40, 5)) // 边框颜色
//                .fillColor(Color.argb(180, 200, 40, 5));   // 多边形的填充色
//        aMap.addPolygon(polygonOptions4);
        addMoreMarket();
        mDeptPresenter = new CommunityPresenterimpl(this, this);
        mDeptPresenter.getMDeptPresenterData();
    }

    private void addMoreMarket() {
        if (marketList == null) {
            marketList = new ArrayList<>();
        }
        marketList.add(new MarkerBean(36.647149,116.910167, "明星小区"));
        marketList.add(new MarkerBean(36.660439,116.924731, "桃园北区"));
        marketList.add(new MarkerBean(36.654384,116.910316, "桃园南区"));
        marketList.add(new MarkerBean(36.647732,116.914744, "景秀荣祥"));
        marketList.add(new MarkerBean(36.632733,116.903168, "世纪中华城"));
        marketList.add(new MarkerBean(36.662917,116.932724, "张庄村"));
        marketList.add(new MarkerBean(36.656464,116.927797, "刘庄村"));
        marketList.add(new MarkerBean(36.672406,116.903867, "大饮马村"));
        for (int i = 0; i < marketList.size(); i++) {
            aMap.addMarker(new MarkerOptions()
//                    .anchor(1.5f, 3.5f)
                    .position(new LatLng(marketList.get(i).getLatitude(),//设置纬度
                            marketList.get(i).getLongitude()))//设置经度
                    .title(marketList.get(i).getTitle())//设置标题
                    //.snippet(marketList.get(i).getContent())//设置内容
                    // .setFlat(true) // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    //.draggable(true) //设置Marker可拖动
                    //.setInfoWindowOffset(0,-64)
                    .icon(BitmapDescriptorFactory.fromResource(imageList.get(i))));
        }
        //设置自定义弹窗
//        aMap.setInfoWindowAdapter(new WindowAdapter(this));
//        //绑定信息窗点击事件
//        aMap.setOnInfoWindowClickListener(new WindowAdapter(this));
//        aMap.setOnMarkerClickListener(new WindowAdapter(this));
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_communityprofile;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @OnClick({R.id.tvInsideDept, R.id.tvNeighborhood, R.id.tvVillage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvInsideDept:
                intent = new Intent(this, MDeptActivity.class);
                startActivity(intent);
                break;
            case R.id.tvNeighborhood:
                intent = new Intent(this, NeighborhoodActivity.class);
                intent.putExtra("header","居委会");
                startActivity(intent);
                break;
            case R.id.tvVillage:
                intent = new Intent(this, VillageActivity.class);
                intent.putExtra("header","村委会");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getMDeptData(Community mDept) {
        ProgressDialogUtil.startLoad(this,"获取数据中");
        ShowMDept shouMDeptBean = new ShowMDept();
        for (int i = 0; i < mDept.getData().getContent().size(); i++) {
            String dep = String.valueOf(mDept.getData().getContent().get(i).getTag());
            dep = dep.substring(1,dep.indexOf("]"));
            if (mDept.getData().getContent().get(i).getOpen().equals("true")&&
                    dep.equals("街道办")){
                String content = mDept.getData().getContent().get(i).getDescribes();
                shouMDeptBean.listContext = content;
                beanList.add(shouMDeptBean);
            }
        }
        Message message = new Message();
        message.what = Constant.TAG_ONE;
        handler.sendMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    if ((beanList.size()!=0)){
                        if (beanList.get(0).getListContext()!=null){
                            tvcontent.setText(Html.fromHtml(beanList.get(0).getListContext()));
                        }
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = Constant.TAG_TWO;
                            handler.sendMessage(message);
                        }
                    },2000);
                    break;
                case Constant.TAG_TWO:
                    ProgressDialogUtil.stopLoad();
            }
        }
    };
}
