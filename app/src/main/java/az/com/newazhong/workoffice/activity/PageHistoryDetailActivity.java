package az.com.newazhong.workoffice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.MyHttpURLConnection;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.workoffice.bean.HistoryDetail;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PageHistoryDetailActivity extends BaseActivity {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    List<HistoryDetail.DataBean.ItemsBean> beanList = new ArrayList<>();
    String Id;

    //单行文本个数
    int etTextNum = 1;
    //多行文本个数
    int etTextMoreNum = 1;
    //数字个数
    int etNumNum = 1;
    //数字个数
    int etMoneyNum = 1;
    //时间个数
    int etNumTime = 1;
    //日期个数
    int etNumDate = 1;
    String aution = "";
    String copy = "";
    @Bind(R.id.tvSend)
    TextView tvSend;
    @Bind(R.id.tvCopy)
    TextView tvCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Id = getIntent().getStringExtra("Id");
        final String Session = new SharedPreferencesHelper(PageHistoryDetailActivity.this, "login").
                getData(this, "session", "");
        if (Session != null && !Session.equals("")) {
            ProgressDialogUtil.startLoad(this, "获取数据中");
            final Message message = new Message();
            final String Url = Constant.BASE_URL + "approval/" + Id + "/apply";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyHttpURLConnection.getData(Url, Session, new BaseRequestBackTLisenter() {
                        @Override
                        public void success(Object o) {
                            message.what = Constant.TAG_ONE;
                            message.obj = o;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void fail(String msg) {
                            message.what = Constant.TAG_TWO;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void failF(String msg) {
                            message.what = Constant.TAG_THREE;
                            handler.sendMessage(message);
                        }
                    });
                }
            }).start();
        } else {
            Intent intent = new Intent(PageHistoryDetailActivity.this, LoginActivity.class);
            new SharedPreferencesHelper(this, "login").removeData(this);
            startActivity(intent);
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_page_history_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    String data = msg.obj.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("items");
                        JSONObject jsonObject3 = jsonObject1.getJSONObject("approvalGroup");
                        int num = jsonObject3.length();
                        for (int k = 0; k < num; k++) {
                            if (num == 0){
                                String siz = String.valueOf(k);
                                JSONObject jsonObject4 = jsonObject3.getJSONObject(siz);
                                String name = jsonObject4.getString("auditor");
                                aution = name;
                            }else if (num>0&&k<num-1){
                                String siz = String.valueOf(k);
                                JSONObject jsonObject4 = jsonObject3.getJSONObject(siz);
                                String name = jsonObject4.getString("auditor");
                                aution = aution+name+",";
                            }else if (num>0&&k==num-1){
                                String siz = String.valueOf(k);
                                JSONObject jsonObject4 = jsonObject3.getJSONObject(siz);
                                String name = jsonObject4.getString("auditor");
                                aution = aution+name;
                            }
                        }
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("ccUsers");
                        String newString = jsonArray1.toString();
                        String newString1 = newString.toString().replace("[\"","");
                        String newString2 = newString1.toString().replace("\"]","");
                        copy = newString2.toString().replace("\",\"",",");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            HistoryDetail.DataBean.ItemsBean bean = new HistoryDetail.DataBean.ItemsBean();
                            bean.setName(jsonObject2.getString("name"));
                            bean.setType(jsonObject2.getString("type"));
                            bean.setValue(jsonObject2.getString("value"));
                            beanList.add(bean);
                        }
                        tvSend.setText(aution);
                        tvCopy.setText(copy);
                        setView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(PageHistoryDetailActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(PageHistoryDetailActivity.this, "login").removeData(PageHistoryDetailActivity.this);
                    Intent intent = new Intent(PageHistoryDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void setView() {
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getType().equals("文本(单行)")) {
                if (etTextNum == 1) {
                    linearLayout.addView(addTextView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                } else if (etTextNum == 2) {
                    linearLayout.addView(addTextView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                } else if (etTextNum == 3) {
                    linearLayout.addView(addTextView2(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                } else if (etTextNum == 4) {
                    linearLayout.addView(addTextView3(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                } else if (etTextNum == 5) {
                    linearLayout.addView(addTextView4(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                } else if (etTextNum == 6) {
                    linearLayout.addView(addTextView5(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextNum += 1;
                }
            }
            if (beanList.get(i).getType().equals("文本(多行)")) {
                if (etTextMoreNum == 1) {
                    linearLayout.addView(addMoreTextView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                } else if (etTextMoreNum == 2) {
                    linearLayout.addView(addMoreTextView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                } else if (etTextMoreNum == 3) {
                    linearLayout.addView(addMoreTextView2(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                } else if (etTextMoreNum == 4) {
                    linearLayout.addView(addMoreTextView3(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                } else if (etTextMoreNum == 5) {
                    linearLayout.addView(addMoreTextView4(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                } else if (etTextMoreNum == 6) {
                    linearLayout.addView(addMoreTextView5(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etTextMoreNum += 1;
                }
            } else if (beanList.get(i).getType().equals("数字123")) {
                if (etNumNum == 1) {
                    linearLayout.addView(addNumView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                } else if (etTextNum == 2) {
                    linearLayout.addView(addNumView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                } else if (etTextNum == 3) {
                    linearLayout.addView(addNumView2(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                } else if (etTextNum == 4) {
                    linearLayout.addView(addNumView3(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                } else if (etTextNum == 5) {
                    linearLayout.addView(addNumView4(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                } else if (etTextNum == 6) {
                    linearLayout.addView(addNumView5(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumNum += 1;
                }
            } else if (beanList.get(i).getType().equals("金额")) {
                if (etMoneyNum == 1) {
                    linearLayout.addView(addMoneyView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etMoneyNum += 1;
                } else if (etMoneyNum == 2) {
                    linearLayout.addView(addMoneyView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etMoneyNum += 1;
                } else if (etMoneyNum == 3) {
                    linearLayout.addView(addMoneyView2(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etMoneyNum += 1;
                }
            } else if (beanList.get(i).getType().equals("时间(秒)")) {
                if (etNumTime == 1) {
                    linearLayout.addView(addTimeView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumTime += 1;
                } else if (etNumTime == 2) {
                    linearLayout.addView(addTimeView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumTime += 1;
                }
            } else if (beanList.get(i).getType().equals("时间(天)")) {
                if (etNumDate == 1) {
                    linearLayout.addView(addDateView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumDate += 1;
                } else if (etNumDate == 2) {
                    linearLayout.addView(addDateView1(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                    linearLayout.addView(addLineView(this));
                    etNumDate += 1;
                }

            } else if (beanList.get(i).getType().equals("附件")) {
                linearLayout.addView(addShowImageView(this));
                linearLayout.addView(addLineView(this));
            } else if (beanList.get(i).getType().equals("多选框")) {
                linearLayout.addView(addReasonView(this, beanList.get(i).getName() + ":", beanList.get(i).getValue()));
                linearLayout.addView(addLineView(this));
            }
        }
    }

    /**
     * 添加textView和Edittext
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addTextView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView1(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView2(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView3(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView4(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView5(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }


    /**
     * 多行文本
     */
    public static View addMoreTextView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView1(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView2(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView3(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView4(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView5(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加数字textView和EditText
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addNumView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView1(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView2(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView3(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView4(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView5(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加金额
     */
    public static View addMoneyView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoneyView1(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoneyView2(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setHint("请填写数据：");
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 选择时间
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addTimeView(final PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(100, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTimeView1(final PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(100, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 选择日期
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addDateView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(100, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        textView1.setOnClickListener(pageActivity);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addDateView1(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(100, 100);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        textView1.setLayoutParams(vlp);
        textView1.setOnClickListener(pageActivity);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加事由
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addReasonView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(300, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT
                , 150);
        TextView textView = new TextView(pageActivity);
        TextView textView1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp);//设置TextView的布局
        textView1.setLayoutParams(vlp1);
        textView.setTextSize(15);
        textView1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        textView1.setText(value);
        textView1.setBackground(null);
        textView1.setTop(10);
        textView1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        textView1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(textView1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加图片
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addImageView(PageHistoryDetailActivity pageActivity, String name, String value) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                300, 150);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(400, 150);
        TextView textView = new TextView(pageActivity);
        ImageView imageView = new ImageView(pageActivity);
        textView.setLayoutParams(vlp);//设置TextView的布局
        imageView.setLayoutParams(vlp1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        Glide.with(MyApplication.getContextObject()).load(value).into(imageView);
        imageView.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(imageView);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加line分界线
     *
     * @param pageActivity
     * @return
     */
    public static View addLineView(PageHistoryDetailActivity pageActivity) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 5);
        View view1 = new View(pageActivity);
        view1.setLayoutParams(vlp);
        view1.setPadding(10, 0, 10, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.shouye));
        view.addView(view1);//将TextView 添加到子View 中
        return view;
    }

    /**
     * 上传图片后显示
     *
     * @param pageActivity
     * @return
     */
    public static View addShowImageView(PageHistoryDetailActivity pageActivity) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(300, 300);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 300);
        ImageView imageView = new ImageView(pageActivity);
        ImageView imageView1 = new ImageView(pageActivity);
        imageView.setLayoutParams(vlp1);//设置TextView的布局
        imageView1.setLayoutParams(vlp);
        imageView.setVisibility(View.GONE);
        imageView1.setVisibility(View.GONE);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        view.addView(imageView);//将TextView 添加到子View 中
        view.addView(imageView1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    private static int calculateDpToPx(int padding_in_dp, PageHistoryDetailActivity pageActivity) {
        final float scale = pageActivity.getResources().getDisplayMetrics().density;
        return (int) (padding_in_dp * scale + 0.5f);
    }
}
