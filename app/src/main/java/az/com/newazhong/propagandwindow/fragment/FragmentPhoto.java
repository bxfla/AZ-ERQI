package az.com.newazhong.propagandwindow.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.adapter.ActivityPhotoAdapter;
import az.com.newazhong.propagandwindow.bean.ActivityPhoto;
import az.com.newazhong.utilsclass.base.BaseFastJSONRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.utils.AlertDialogUtil;
import az.com.newazhong.utilsclass.utils.FastUtils;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2017/8/1.
 */

public class FragmentPhoto extends Fragment {
    View view;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.gallery)
    Gallery gallery;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    private AlertDialogUtil alertDialogUtil;
    List<String> beanList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    List<String> showImageList = new ArrayList<>();
    List<String> showImageList1 = new ArrayList<>();
    List<String> showTitleList = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    List<Map<String, String>> mapList = new ArrayList<>();
    ActivityPhotoAdapter adapter;
    int inde, num;
    float startX = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        ProgressDialogUtil.startLoad(getActivity(), "获取数据中");
        alertDialogUtil = new AlertDialogUtil(getActivity());
        String url = Constant.BASE_URL + "mien?mienType=PICTURE&page=0&size=1000";
        FastUtils.getFastData(url, new BaseFastJSONRequestBackLisenter() {
            @Override
            public void success(String o) {
                ActivityPhoto resultBeanData = JSON.parseObject(o, ActivityPhoto.class);
                if (resultBeanData.getData().getContent().size() == 0) {
                    llNoContent.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    ProgressDialogUtil.stopLoad();
                } else {
                    String imgUrl;
                    String title;
                    for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
                        int num = resultBeanData.getData().getContent().get(i).getPicture().size();
                        if (resultBeanData.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED") && num != 0) {
                            for (int j = 0; j < num; j++) {
                                imgUrl = resultBeanData.getData().getContent().get(i).getPicture().get(j).getPicture();
                                beanList.add(imgUrl);
                                titleList.add(resultBeanData.getData().getContent().get(i).getArticle().getTitle());
                                Map<String, String> map = new HashMap<>();
                                map.put(resultBeanData.getData().getContent().get(i).getArticle().getTitle(), imgUrl);
                                mapList.add(map);
                                if (!showTitleList.contains(resultBeanData.getData().getContent().get(i).getArticle().getTitle())) {
                                    showTitleList.add(resultBeanData.getData().getContent().get(i).getArticle().getTitle());
                                    showImageList.add(resultBeanData.getData().getContent().get(i).getPicture().get(0).getPicture());
                                }
                            }
                        }
                    }
                    Message message = new Message();
                    message.what = Constant.TAG_ONE;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void fail(String message) {
                Log.e("XXX", "解析成功失败");
            }
        });
        return view;
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


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (beanList.size() != 0) {
                for (int i = 0; i < mapList.size(); i++) {
                    Map<String, String> mapValue = mapList.get(i);
                    for (String key : mapValue.keySet()) {
                        if (key.equals(showTitleList.get(0))) {
                            if (!showImageList1.contains(mapValue.get(key))){
                                showImageList1.add(mapValue.get(key));
                            }
                        }
                    }
                }

                String img = beanList.get(0);
                Glide.with(getActivity()).load(img).placeholder(R.drawable.loding).into(imageView);
//            List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
//            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//                @Override
//                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                    int flag = o1.getValue().compareTo(o2.getValue());
//                    if (flag == 0) {
//                        return o1.getKey().compareTo(o2.getKey());
//                    }
//                    return flag;
//                }
//            });
//
//            //遍历list得到map里面排序后的元素
//            for (Map.Entry<String, String> en : list) {
//                Log.d("mycontent",en.getKey() + " " + en.getValue());
//                System.out.println(en.getKey() + " " + en.getValue());
//                if (!showTitleList.contains(en.getKey())){
//                    showTitleList.add(en.getKey());
//                    showImageList.add(en.getValue());
//                }
//            }
                adapter = new ActivityPhotoAdapter(getActivity(), showImageList, showTitleList);
                gallery.setAdapter(adapter);
                ProgressDialogUtil.stopLoad();
                gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String title = showTitleList.get(position);
                        num = 0;
                        showImageList1.clear();
                        for (int i = 0; i < mapList.size(); i++) {
                            Map<String, String> mapValue = mapList.get(i);
                            for (String key : mapValue.keySet()) {
                                if (key.equals(showTitleList.get(position))) {
                                    if (!showImageList1.contains(mapValue.get(key))){
                                        showImageList1.add(mapValue.get(key));
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < titleList.size(); i++) {
                            if (title.equals(titleList.get(i))) {
                                inde = i;
                                Glide.with(getActivity()).load(showImageList1.get(0)).placeholder(R.drawable.loding).into(imageView);
                                break;
                            }
                        }
                    }
                });
                alertDialogUtil = new AlertDialogUtil(getActivity());
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            // 手指落下
                            case MotionEvent.ACTION_DOWN: {
                                startX = event.getX();
                                break;
                            }
                            case MotionEvent.ACTION_UP: {
                                // 从右向左滑动
                                if (event.getX() - startX > 50) {
                                    if (num == 0) {
                                        alertDialogUtil.showSmallDialog(getResources().getString(R.string.first_paper));
                                    } else {
                                        Glide.with(getActivity()).load(showImageList1.get(num - 1)).placeholder(R.drawable.loding).into(imageView);
                                        num -= 1;
                                        //gallery.setSelection(inde);
                                    }
                                }
                                // 从左向右滑动
                                if (startX - event.getX() > 50) {
                                    if (num != showImageList1.size() - 1) {
                                        Glide.with(getActivity()).load(showImageList1.get(num + 1)).placeholder(R.drawable.loding).into(imageView);
                                        num += 1;
                                        //gallery.setSelection(inde);
                                    } else {
                                        alertDialogUtil.showSmallDialog(getResources().getString(R.string.last_paper));
                                    }
                                }
                                break;
                            }
                        }
                        return true;
                    }
                });
            } else {
                llNoContent.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                ProgressDialogUtil.stopLoad();
            }

        }
    };
}