package az.com.newazhong.propagandwindow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.propagandwindow.activity.YuYueActivity;
import az.com.newazhong.propagandwindow.adapter.ActivityContentAdapter;
import az.com.newazhong.propagandwindow.bean.ActivityContent;
import az.com.newazhong.propagandwindow.bean.ShowActivityPhoto;
import az.com.newazhong.utilsclass.base.BaseFastJSONRequestBackLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.utils.FastUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on.
 */

public class FragmentContent extends Fragment implements ActivityContentAdapter.GetItemPosition{
    @Bind(R.id.pullLoadMoreRecyclerView)
    RecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.llNoContent)
    LinearLayout llNoContent;

    List<ShowActivityPhoto> beanList = new ArrayList<>();
    ActivityContentAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        String url = Constant.BASE_URL+"mien?mienType=ACTIVITY&page=0&size=100";
        FastUtils.getFastData(url, new BaseFastJSONRequestBackLisenter() {
            @Override
            public void success(String o) {
                ActivityContent resultBeanData = JSON.parseObject(o,ActivityContent.class);
                if (resultBeanData.getData().getContent().size() == 0) {
                    llNoContent.setVisibility(View.VISIBLE);
                    pullLoadMoreRecyclerView.setVisibility(View.GONE);
                } else {
                    for (int i = 0; i < resultBeanData.getData().getContent().size(); i++) {
                        if (resultBeanData.getData().getContent().get(i).getArticle().getStatus().equals("APPROVED")){
                            ShowActivityPhoto showActivityPhoto = new ShowActivityPhoto();
                            showActivityPhoto.title = resultBeanData.getData().getContent().get(i).getArticle().getTitle();
                            showActivityPhoto.content = String.valueOf(resultBeanData.getData().getContent().get(i).getArticle().getContent());
                            showActivityPhoto.name = resultBeanData.getData().getContent().get(i).getDepartmentName();
                            showActivityPhoto.time = resultBeanData.getData().getContent().get(i).getCreateTime();
                            showActivityPhoto.id = String.valueOf(resultBeanData.getData().getContent().get(i).getId());
                            beanList.add(showActivityPhoto);
                        }
                    }
                    setAdapterData();
                }
            }

            @Override
            public void fail(String message) {
                Log.e("XXX","解析成功失败");
            }
        });
        return view;
    }

    private void setAdapterData() {
        if (beanList.size()!=0){
            LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContextObject());
            pullLoadMoreRecyclerView.setLayoutManager(manager);
            adapter = new ActivityContentAdapter(beanList);
            pullLoadMoreRecyclerView.setAdapter(adapter);
            adapter.getItemPositionView(this);
        }else {
//            llNoContent.setVisibility(View.VISIBLE);
//            pullLoadMoreRecyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "暂无活动内容", Toast.LENGTH_SHORT).show();
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

    @Override
    public void getPosition(int position) {
        Intent intent = new Intent(MyApplication.getContextObject(), YuYueActivity.class);
        String mienId = beanList.get(position).getId();
        intent.putExtra("id",mienId);
        startActivity(intent);
    }
}