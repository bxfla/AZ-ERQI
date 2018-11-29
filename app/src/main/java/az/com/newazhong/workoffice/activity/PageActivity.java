package az.com.newazhong.workoffice.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import az.com.newazhong.R;
import az.com.newazhong.SharedPreferencesHelper;
import az.com.newazhong.utilsclass.base.BaseActivity;
import az.com.newazhong.utilsclass.base.BaseRequestBackTLisenter;
import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.MyApplication;
import az.com.newazhong.utilsclass.myViews.Header;
import az.com.newazhong.utilsclass.person.activity.LoginActivity;
import az.com.newazhong.utilsclass.utils.ListToJson;
import az.com.newazhong.utilsclass.utils.MyHttpURLConnection;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.utils.WXPhoto.ImagesSelectorActivity;
import az.com.newazhong.utilsclass.utils.WXPhoto.SelectorSettings;
import az.com.newazhong.utilsclass.utils.time_select.CustomDatePickerDay;
import az.com.newazhong.utilsclass.utils.time_select.CustomDatePickerTime;
import az.com.newazhong.workoffice.bean.SendPerson;
import az.com.newazhong.workoffice.bean.UpData;
import az.com.newazhong.workoffice.bean.WorkOfficeUrlList;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static az.com.newazhong.utilsclass.base.Constant.TAG_TWO;

public class PageActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.header)
    Header header;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    WorkOfficeUrlList officeBean;
    //    WorkOfficeUrlList.DataBean.ContentBean bean;
    List<WorkOfficeUrlList.DataBean.ContentBean.ApprovalItemsBean> beanList = new ArrayList<>();
    private static EditText editText, editText1, editText2, editText3, editText4, editText5,
            editMoreText, editMoreText1, editMoreText2, editMoreText3, editMoreText4, editMoreText5,
            editNum, editNum1, editNum2, editNum3, editNum4, editNum5, etReason, etMoney, etMoney1, etMoney2;
    private static TextView tvImage, tvData, etTime, etData, etTime1, etData1, tvMore, tvMore1;
    private static ImageView imageView, imageView1;
    static Bitmap newbitmap;
    //排序
    int sort = 1;
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
    //多选框个数
    //日期个数
    int tvMoreNum = 1;

    //发送的第几个数据
    int numText, numMoreText, numNumText, numMoneyText, numImageText, numTimeText, numDataText, numCheckText;

    //数据上传个数统计
    int sendNum = 0;
    //当前时间
    String now;
    File tmpDir;
    String path;
    String result = null;
    String messageT;
    @Bind(R.id.tvSend)
    TextView tvSend;
    @Bind(R.id.tvCopy)
    TextView tvCopy;
    @Bind(R.id.btn)
    Button btn;
    private CustomDatePickerTime customDatePicker1, customDatePicker11;
    private CustomDatePickerDay customDatePicker2, customDatePicker22;
    private static final int MY_PERMISSIONS_MY_UP_IMAGE = 1;
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<String> moreList = new ArrayList<>();
    private ArrayList<String> moreList1 = new ArrayList<>();
    private List<SendPerson> listPerson = new ArrayList<>();
    private List<SendPerson> listPerson1 = new ArrayList<>();
    String url0 = Constant.BASE_URL + "upload";
    File fileDate;
    String name = "";
    String CopyId = "";
    String Id;
    List<UpData.ApprovalGroupBean> listG = new ArrayList<>();
    List<UpData.ApprovalItemsBean> listB = new ArrayList<>();
    String tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        beanList.clear();
        Intent intent = getIntent();
        tvHeader = intent.getStringExtra("header");
        final WorkOfficeUrlList.DataBean.ContentBean bean = (WorkOfficeUrlList.DataBean.ContentBean) intent.getSerializableExtra("data");
        Id = String.valueOf(bean.getId());
        header.setTvTitle(tvHeader);
        if (bean.getApprovalItems().size() != 0) {
            for (int i = 0; i < bean.getApprovalItems().size(); i++) {
                if (bean.getApprovalItems().get(i).getSort() == sort) {
                    beanList.add(bean.getApprovalItems().get(i));
                    sort += 1;
                }
            }
            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(i).getType().equals("文本(单行)")) {
                    if (etTextNum == 1) {
                        linearLayout.addView(addTextView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    } else if (etTextNum == 2) {
                        linearLayout.addView(addTextView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    } else if (etTextNum == 3) {
                        linearLayout.addView(addTextView2(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    } else if (etTextNum == 4) {
                        linearLayout.addView(addTextView3(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    } else if (etTextNum == 5) {
                        linearLayout.addView(addTextView4(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    } else if (etTextNum == 6) {
                        linearLayout.addView(addTextView5(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextNum += 1;
                    }
                }
                if (beanList.get(i).getType().equals("文本(多行)")) {
                    if (etTextMoreNum == 1) {
                        linearLayout.addView(addMoreTextView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    } else if (etTextMoreNum == 2) {
                        linearLayout.addView(addMoreTextView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    } else if (etTextMoreNum == 3) {
                        linearLayout.addView(addMoreTextView2(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    } else if (etTextMoreNum == 4) {
                        linearLayout.addView(addMoreTextView3(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    } else if (etTextMoreNum == 5) {
                        linearLayout.addView(addMoreTextView4(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    } else if (etTextMoreNum == 6) {
                        linearLayout.addView(addMoreTextView5(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etTextMoreNum += 1;
                    }
                } else if (beanList.get(i).getType().equals("数字123")) {
                    if (etNumNum == 1) {
                        linearLayout.addView(addNumView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    } else if (etTextNum == 2) {
                        linearLayout.addView(addNumView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    } else if (etTextNum == 3) {
                        linearLayout.addView(addNumView2(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    } else if (etTextNum == 4) {
                        linearLayout.addView(addNumView3(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    } else if (etTextNum == 5) {
                        linearLayout.addView(addNumView4(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    } else if (etTextNum == 6) {
                        linearLayout.addView(addNumView5(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumNum += 1;
                    }
                } else if (beanList.get(i).getType().equals("金额")) {
                    if (etMoneyNum == 1) {
                        linearLayout.addView(addMoneyView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etMoneyNum += 1;
                    } else if (etMoneyNum == 2) {
                        linearLayout.addView(addMoneyView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etMoneyNum += 1;
                    } else if (etMoneyNum == 3) {
                        linearLayout.addView(addMoneyView2(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etMoneyNum += 1;
                    }
                } else if (beanList.get(i).getType().equals("时间(秒)")) {
                    if (etNumTime == 1) {
                        linearLayout.addView(addTimeView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumTime += 1;
                    } else if (etNumTime == 2) {
                        linearLayout.addView(addTimeView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumTime += 1;
                    }
                } else if (beanList.get(i).getType().equals("时间(天)")) {
                    if (etNumDate == 1) {
                        linearLayout.addView(addDateView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumDate += 1;
                    } else if (etNumDate == 2) {
                        linearLayout.addView(addDateView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        etNumDate += 1;
                    }

                } else if (beanList.get(i).getType().equals("附件")) {
                    linearLayout.addView(addImageView(this, beanList.get(i).getName() + ":"));
                    linearLayout.addView(addLineView(this));
                    linearLayout.addView(addShowImageView(this));
                    linearLayout.addView(addLineView(this));
                } else if (beanList.get(i).getType().equals("多选框")) {
                    if (tvMoreNum == 1) {
                        linearLayout.addView(addMoreCheckView(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        String data = String.valueOf(beanList.get(i).getMOptions());
                        String newString0 = data.toString().replace("[,", "");
                        String newString = newString0.toString().replace("[", "");
                        String newString1 = newString.toString().replace("]", "");
                        Pattern pen = Pattern.compile(",");
                        String[] temp = pen.split(newString1);
                        for (int k = 0; k < temp.length; k++) {
                            moreList.add(temp[k]);
                        }
                        tvMoreNum += 1;
                    } else if (tvMoreNum == 2) {
                        linearLayout.addView(addMoreCheckView1(this, beanList.get(i).getName() + ":"));
                        linearLayout.addView(addLineView(this));
                        tvMoreNum += 1;
                        String data = String.valueOf(beanList.get(i).getMOptions());
                        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                        String newString0 = data.toString().replace("[,", "");
                        String newString = newString0.toString().replace("[", "");
                        String newString1 = newString.toString().replace("]", "]");
                        Pattern pen = Pattern.compile("\\|");
                        String[] temp = pen.split(newString1);
                        for (int k = 0; k < temp.length; k++) {
                            moreList1.add(temp[k]);
                        }
                    }

                }
            }

//            linearLayout.addView(addSendView(this));
//            linearLayout.addView(addLineView(this));
//            linearLayout.addView(addCopyView(this));
//            linearLayout.addView(addLineView(this));
//            linearLayout.addView(addView(this));
//            linearLayout.addView(addButtonView(this));
            //选择时间
            initDatePicker();
            if (etTime != null) {
                etTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDatePicker1.show(now);
                    }
                });
            }
            if (etTime1 != null) {
                etTime1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDatePicker11.show(now);
                    }
                });
            }
            if (etData != null) {
                etData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDatePicker2.show(etData.getText().toString());
                    }
                });
            }

            if (etData1 != null) {
                etData1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDatePicker22.show(etData1.getText().toString());
                    }
                });
            }
            if (tvMore != null) {
                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PageActivity.this, MoreCheckActivity.class);
                        intent.putStringArrayListExtra("list", moreList);
                        intent.putExtra("type", "1");
                        startActivityForResult(intent, Constant.TAG_TWO);
                    }
                });
            }
            if (tvMore1 != null) {
                tvMore1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PageActivity.this, MoreCheckActivity.class);
                        intent.putStringArrayListExtra("list", moreList1);
                        intent.putExtra("type", "2");
                        startActivityForResult(intent, Constant.TAG_TWO);
                    }
                });
            }
            if (tvImage != null) {
                tvImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(PageActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                                || ContextCompat.checkSelfPermission(PageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(PageActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_MY_UP_IMAGE);
                        } else {
                            Intent intent1 = new Intent(PageActivity.this, ImagesSelectorActivity.class);
                            intent1.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
                            intent1.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
                            intent1.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                            startActivityForResult(intent1, MY_PERMISSIONS_MY_UP_IMAGE);
                        }
                    }
                });
            }


            if (btn != null) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        private static TextView tvImage, tvData, etTime, etData, etTime1, etData1;
                        if (editText != null && editText.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editText1 != null && editText1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editText2 != null && editText2.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editText3 != null && editText3.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editText4 != null && editText4.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editText5 != null && editText5.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText != null && editMoreText.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText1 != null && editMoreText1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText2 != null && editMoreText2.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText3 != null && editMoreText3.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText4 != null && editMoreText4.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editMoreText5 != null && editMoreText5.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum != null && editNum.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum1 != null && editNum1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum2 != null && editNum2.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum3 != null && editNum3.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum4 != null && editNum4.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (editNum5 != null && editNum5.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etReason != null && etReason.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etMoney != null && etMoney.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etMoney1 != null && etMoney1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etMoney2 != null && etMoney2.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etTime != null && etTime.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etTime1 != null && etTime1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etData != null && etData.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (etData1 != null && etData1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "请填写所有数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tvSend.getText().length() == 0 || tvCopy.getText().length() == 0) {
                            Toast.makeText(PageActivity.this, "请选择审核 抄送人", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tvMore != null && tvMore.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "More1", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tvMore1 != null && tvMore1.getText().toString().equals("")) {
                            Toast.makeText(PageActivity.this, "More2", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String Name = new SharedPreferencesHelper(PageActivity.this, "login")
                                    .getData(PageActivity.this, "trueName", "");
                            String Id = new SharedPreferencesHelper(PageActivity.this, "login")
                                    .getData(PageActivity.this, "userId", "");

                            int num = bean.getApprovalItems().size();
                            while (sendNum < num) {
                                UpData.ApprovalItemsBean beanB = new UpData.ApprovalItemsBean();
                                beanB.setSort(bean.getApprovalItems().get(sendNum).getSort());
                                beanB.setName(bean.getApprovalItems().get(sendNum).getName());
                                beanB.setType(bean.getApprovalItems().get(sendNum).getType());
                                beanB.setRequired(true);
                                beanB.setMOptions(bean.getApprovalItems().get(sendNum).getMOptions());

                                if (bean.getApprovalItems().get(sendNum).getType().equals("文本(单行)")) {
                                    if (numText == 0) {
                                        beanB.setValue(editText.getText().toString());
                                    } else if (numText == 1) {
                                        beanB.setValue(editText1.getText().toString());
                                    } else if (numText == 2) {
                                        beanB.setValue(editText2.getText().toString());
                                    } else if (numText == 3) {
                                        beanB.setValue(editText3.getText().toString());
                                    } else if (numText == 4) {
                                        beanB.setValue(editText4.getText().toString());
                                    } else if (numText == 5) {
                                        beanB.setValue(editText5.getText().toString());
                                    }
                                    numText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("文本(多行)")) {
                                    if (numMoreText == 0) {
                                        beanB.setValue(editMoreText.getText().toString());
                                    } else if (numMoreText == 1) {
                                        beanB.setValue(editMoreText1.getText().toString());
                                    } else if (numMoreText == 2) {
                                        beanB.setValue(editMoreText2.getText().toString());
                                    } else if (numMoreText == 3) {
                                        beanB.setValue(editMoreText3.getText().toString());
                                    } else if (numMoreText == 4) {
                                        beanB.setValue(editMoreText4.getText().toString());
                                    } else if (numMoreText == 5) {
                                        beanB.setValue(editMoreText5.getText().toString());
                                    }
                                    numMoreText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("数字123")) {
                                    if (numNumText == 0) {
                                        beanB.setValue(editNum.getText().toString());
                                    } else if (numNumText == 1) {
                                        beanB.setValue(editNum1.getText().toString());
                                    } else if (numNumText == 2) {
                                        beanB.setValue(editNum2.getText().toString());
                                    } else if (numNumText == 3) {
                                        beanB.setValue(editNum3.getText().toString());
                                    } else if (numNumText == 4) {
                                        beanB.setValue(editNum4.getText().toString());
                                    } else if (numNumText == 5) {
                                        beanB.setValue(editNum5.getText().toString());
                                    }
                                    numNumText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("金额")) {
                                    if (numMoneyText == 0) {
                                        beanB.setValue(etMoney.getText().toString());
                                    } else if (numMoneyText == 1) {
                                        beanB.setValue(etMoney1.getText().toString());
                                    } else if (numMoneyText == 2) {
                                        beanB.setValue(etMoney2.getText().toString());
                                    }
                                    numMoneyText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("附件")) {
                                    if (numImageText == 0) {
                                        beanB.setValue(path);
                                    } else if (numImageText == 1) {
                                        beanB.setValue(path);
                                    }
                                    numImageText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("多选框")) {
                                    if (numCheckText == 0) {
                                        beanB.setValue(tvMore.getText().toString());
                                    } else if (numTimeText == 1) {
                                        beanB.setValue(tvMore1.getText().toString());
                                    }
                                    numCheckText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("时间(秒)")) {
                                    if (numTimeText == 0) {
                                        beanB.setValue(etTime.getText().toString());
                                    } else if (numTimeText == 1) {
                                        beanB.setValue(etTime1.getText().toString());
                                    }
                                    numTimeText += 1;
                                } else if (bean.getApprovalItems().get(sendNum).getType().equals("时间(天)")) {
                                    if (numDataText == 0) {
                                        beanB.setValue(etData.getText().toString());
                                    } else if (numDataText == 1) {
                                        beanB.setValue(etData1.getText().toString());
                                    }
                                    numDataText += 1;
                                }
                                listB.add(beanB);
                                sendNum += 1;
                            }
                            if (listPerson.size() != 0) {
                                for (int i = 0; i < listPerson.size(); i++) {
                                    UpData.ApprovalGroupBean beanU = new UpData.ApprovalGroupBean();
                                    beanU.setAuditor(listPerson.get(i).getName());
                                    beanU.setUserId(Integer.parseInt(listPerson.get(i).getId()));
                                    beanU.setMemo("");
                                    beanU.setSort(i);
                                    beanU.setAdopt(null);
                                    listG.add(beanU);
                                }
                            }

                            for (int i = 0; i < listPerson1.size(); i++) {
                                if (i == 0) {
                                    CopyId = CopyId + listPerson1.get(i).getId();
                                } else {
                                    CopyId = CopyId + "," + listPerson1.get(i).getId();
                                }

                            }
                        }
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.put(CopyId);
                        ProgressDialogUtil.startLoad(PageActivity.this, "数据上传中");
                        String data = "{" + "\"approvalGroup\":" + ListToJson.ProLogListJson1(listG).toString() + "," +
                                " \"approvalItems\":" + ListToJson.ProLogListJson(listB).toString() + "," +
                                "\"ccUserId\": " + jsonArray + "}";
                        final String parm = data;
                        //final String newStr = parm.replace("\"[]\"","[]");
                        String parm1 = parm.toString().replace(":\"[", ":[\"");
                        final String parm2 = parm1.toString().replace("]\"},", "\"]},");
                        final String parm3 = parm2.toString().replace(": [\"", ":[\"");
                        final String parm4 = parm3.toString().replace("\"]}", "\"]}");
                        final String parm5 = parm4.toString().replace("ccUserId\":[\"", "ccUserId\":[");
                        final String parm6 = parm5.toString().replace("\"]}", "]}");
                        final String parm7 = parm6.toString().replace("[\"]},", "[\"\"]},");
                        final String parm8 = parm7.toString().replace("]\"}],", "\"]}],");
                        final String parm9 = parm8.toString().replace("]},{", "\"]},{");
                        final String parm10 = parm9.toString().replace(":[\"\"\"]", ":[\"\"]");
                        final String Url = Constant.BASE_URL + "approval/" + Id + "/apply/add";
                        final String Session = new SharedPreferencesHelper(PageActivity.this, "login").
                                getData(PageActivity.this, "session", "");
                        if (Session != null && !Session.equals("")) {
                            final Message message = new Message();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    MyHttpURLConnection.postData(Url, Session, parm10, new BaseRequestBackTLisenter() {
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
                            Intent intent1 = new Intent(PageActivity.this, LoginActivity.class);
                            new SharedPreferencesHelper(PageActivity.this, "login").removeData(MyApplication.getContextObject());
                            startActivity(intent1);
                        }
                        Log.e("XXX", "数据封装" + ListToJson.ProLogListJson1(listG).toString() + ","
                                + ListToJson.ProLogListJson(listB).toString() + ","
                                + jsonArray.toString());
                    }
                });
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    Toast.makeText(PageActivity.this, "数据上传成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    finish();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(PageActivity.this, "数据上传失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_THREE:
                    ProgressDialogUtil.stopLoad();
                    new SharedPreferencesHelper(PageActivity.this, "login").removeData(PageActivity.this);
                    Intent intent = new Intent(PageActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_page;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        Intent intent = new Intent(PageActivity.this, PageHistoryActivity.class);
        intent.putExtra("header", tvHeader);
        intent.putExtra("Id", Id);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(PageActivity.this, ImagesSelectorActivity.class);
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
                    newbitmap = rotaingImageView(degree, cbitmap);
                    //imageViewAdd01.setImageBitmap(newbitmap);
                    saveBitmap(newbitmap, "temp");
                }
                break;
            case Constant.TAG_FOUR:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    String img_path = actualimagecursor.getString(actual_image_column_index);
                    fileDate = new File(img_path);
                    ProgressDialogUtil.startLoad(PageActivity.this, "文件上传中");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            uploadDate(fileDate, url0);
                        }
                    }).start();
                    Toast.makeText(PageActivity.this, fileDate.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case Constant.TAG_FIVE:
                if (data == null){
                    return;
                }
                name = "";
                listPerson = (List<SendPerson>) data.getSerializableExtra("name");
                if (listPerson.size() != 0) {
                    for (int i = 0; i < listPerson.size(); i++) {
                        if (i != listPerson.size() - 1) {
                            if (tvSend.getText().toString().length() != 0) {
                                if (name.indexOf(listPerson.get(i).getName()) != -1) {
                                    if (i != listPerson.size() - 1) {
                                        name = name + listPerson.get(i).getName() + "--->";
                                    } else {
                                        name = name + listPerson.get(i).getName() + "--->";
                                    }
                                }
                            } else {
                                name = name + listPerson.get(i).getName() + "--->";
                            }
                        } else {
                            if (tvSend.getText().toString().length() != 0) {
                                if (tvSend.getText().toString().indexOf(listPerson.get(i).getName()) == -1) {
                                    name = name + listPerson.get(i).getName();
                                }
                            } else {
                                name = name + listPerson.get(i).getName();
                            }
                        }
                    }
                }
                String data2 = tvSend.getText().toString();
                if (data2 != null && data2.length() != 0) {
                    if (!name.equals("")&&data2.indexOf(name) == -1) {
                        tvSend.setText(data2 + "--->" + name);
                    }
                } else {
                    tvSend.setText(name);
                }
//                if (resultCode == Constant.TAG_FIVE) {
//                    name = "";
//                    listPerson = (List<SendPerson>) data.getSerializableExtra("name");
//                    if (listPerson.size() != 0) {
//                        for (int i = 0; i < listPerson.size(); i++) {
//                            if (i != listPerson.size() - 1) {
//                                name = name + listPerson.get(i).getName() + "--->";
//                            } else {
//                                name = name + listPerson.get(i).getName();
//                            }
//                        }
//                    }
//                    String data1 = tvSend.getText().toString();
//                    if (data1 != null && data1.length() != 0) {
//                        if (!name.equals("")) {
//                            tvSend.setText(data1 + "--->" + name);
//                        }
//                    } else {
//                        tvSend.setText(name);
//                    }
//                }
//                if (resultCode == Constant.TAG_SIX) {
////                    name = "";
////                    listPerson1 = (List<SendPerson>) data.getSerializableExtra("name");
////                    if (listPerson1.size() != 0) {
////                        for (int i = 0; i < listPerson1.size(); i++) {
////                            if (i != listPerson1.size() - 1) {
////                                if (tvCopy.getText().toString().length() != 0) {
////                                    if (name.indexOf(listPerson1.get(i).getName()) != -1) {
////                                        if (i != listPerson1.size() - 1) {
////                                            name = name + listPerson1.get(i).getName() + ",";
////                                        } else {
////                                            name = name + listPerson1.get(i).getName() + ",";
////                                        }
////                                    }
////                                } else {
////                                    name = name + listPerson1.get(i).getName() + ",";
////                                }
////                            } else {
////                                if (tvCopy.getText().toString().length() != 0) {
////                                    if (name.indexOf(listPerson1.get(i).getName()) != -1) {
////                                        name = name + listPerson1.get(i).getName();
////                                    }
////                                } else {
////                                    name = name + listPerson1.get(i).getName();
////                                }
////                            }
////                        }
////                    }
//                    name = "";
//                    listPerson1 = (List<SendPerson>) data.getSerializableExtra("name");
//                    if (listPerson1.size() != 0) {
//                        for (int i = 0; i < listPerson1.size(); i++) {
//                            if (i != listPerson1.size() - 1) {
//                                name = name + listPerson1.get(i).getName() + ",";
//                            } else {
//                                name = name + listPerson1.get(i).getName();
//                            }
//                        }
//                    }
//                    String data1 = tvCopy.getText().toString();
//                    if (data1 != null && data1.length() != 0) {
//                        if (!name.equals("")) {
//                            tvCopy.setText(data1 + "," + name);
//                        }
//                    } else {
//                        tvCopy.setText(name);
//                    }
//                }
                break;
            case Constant.TAG_SIX:
                if (data == null){
                    return;
                }
                name = "";
                listPerson1 = (List<SendPerson>) data.getSerializableExtra("name");
                if (listPerson1.size() != 0) {
                    for (int i = 0; i < listPerson1.size(); i++) {
                        if (i != listPerson1.size() - 1) {
                            if (tvCopy.getText().toString().length() != 0) {
                                if (name.indexOf(listPerson1.get(i).getName()) != -1) {
                                    if (i != listPerson1.size() - 1) {
                                        name = name + listPerson1.get(i).getName() + ",";
                                    } else {
                                        name = name + listPerson1.get(i).getName() + ",";
                                    }
                                }
                            } else {
                                name = name + listPerson1.get(i).getName() + ",";
                            }
                        } else {
                            if (tvCopy.getText().toString().length() != 0) {
                                if (tvCopy.getText().toString().indexOf(listPerson1.get(i).getName()) == -1) {
                                    name = name + listPerson1.get(i).getName();
                                }
                            } else {
                                name = name + listPerson1.get(i).getName();
                            }
                        }
                    }
                }
                String data1 = tvCopy.getText().toString();
                if (data1 != null && data1.length() != 0) {
                    if (!name.equals("")&&data1.indexOf(name) == -1) {
                        tvCopy.setText(data1 + "," + name);
                    }
                } else {
                    tvCopy.setText(name);
                }
                break;
            case Constant.TAG_TWO:
                if (resultCode == Constant.TAG_ONE) {
                    String result = data.getExtras().getString("result");
                    tvMore.setText(result);
                } else if (resultCode == Constant.TAG_TWO) {
                    String result = data.getExtras().getString("result");
                    tvMore1.setText(result);
                }
                break;
        }
    }

    /**
     * 上传附件
     */
    public void uploadDate(File tmpDir, String url) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String session = new SharedPreferencesHelper(PageActivity.this, "login").getData(this, "session", "");
        post.addHeader("Cookie", session);
        MultipartEntity muti = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
                Charset.forName("UTF-8"));
        // MultipartEntity muti=new MultipartEntity();
        if (tmpDir != null) {
            File filAbs = new File(tmpDir.getPath(), "");
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
                handler1.sendMessage(message);
            } else if (response.getStatusLine().getStatusCode() == 201) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    path = jsonObjectData.getString("accessPath");
                    Message message = new Message();
                    message.what = Constant.TAG_THREE;
                    handler1.sendMessage(message);
                }
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    messageT = jsonObject.getString("message");
                }
                Message message = new Message();
                message.what = Constant.TAG_FOUR;
                handler1.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
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
            //发送图片到服务器
            ProgressDialogUtil.startLoad(PageActivity.this, "图片上传中");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    uploadFile(tmpDir, url0);
                }
            }).start();
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

    public void uploadFile(File tmpDir, String url) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String session = new SharedPreferencesHelper(PageActivity.this, "login").getData(this, "session", "");
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
                handler1.sendMessage(message);
            } else if (response.getStatusLine().getStatusCode() == 201) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    path = jsonObjectData.getString("accessPath");
                    Message message = new Message();
                    message.what = Constant.TAG_THREE;
                    handler1.sendMessage(message);
                }
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                    JSONObject jsonObject = new JSONObject(result);
                    messageT = jsonObject.getString("message");
                }
                Message message = new Message();
                message.what = Constant.TAG_FOUR;
                handler1.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 选择时间
     */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        customDatePicker1 = new CustomDatePickerTime(this, new CustomDatePickerTime.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
            }
        }, now, "2030-01-01 00:00", etTime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker11 = new CustomDatePickerTime(this, new CustomDatePickerTime.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
            }
        }, now, "2030-01-01 00:00", etTime1);
        customDatePicker1.showSpecificDate(true);
        customDatePicker1.showSpecificTime(true);
        customDatePicker1.setIsLoop(false);
        customDatePicker11.showSpecificDate(true);
        customDatePicker11.showSpecificTime(true);
        customDatePicker11.setIsLoop(false);

        customDatePicker2 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
            }
        }, now, "2030-01-01 00:00", etData); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker22 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
            }
        }, now, "2030-01-01 00:00", etData1);
        customDatePicker2.showSpecificDate(true);
        customDatePicker2.showSpecificTime(false);
        customDatePicker2.setIsLoop(false);
        customDatePicker22.showSpecificDate(true);
        customDatePicker22.showSpecificTime(false);
        customDatePicker22.setIsLoop(false);
        if (etTime != null) {
            etTime.setText(now);
        }
        if (etTime1 != null) {
            etTime1.setText(now);
        }
        if (etData != null) {
            etData.setText(now.split(" ")[0]);
        }
        if (etData1 != null) {
            etData1.setText(now.split(" ")[0]);
        }
    }

    /**
     * 添加textView和Edittext
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addTextView(PageActivity pageActivity, String name) {
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
        editText = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText.setHint("");
        editText.setBackground(null);
        editText.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView1(PageActivity pageActivity, String name) {
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
        editText1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText1.setHint("");
        editText1.setBackground(null);
        editText1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView2(PageActivity pageActivity, String name) {
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
        editText2 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText2.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText2.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText2.setHint("");
        editText2.setBackground(null);
        editText2.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText2);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView3(PageActivity pageActivity, String name) {
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
        editText3 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText3.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText3.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText3.setHint("");
        editText3.setBackground(null);
        editText3.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText3);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView4(PageActivity pageActivity, String name) {
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
        editText4 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText4.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText4.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText4.setHint("");
        editText4.setBackground(null);
        editText4.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText4);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTextView5(PageActivity pageActivity, String name) {
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
        editText5 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editText5.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editText5.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editText5.setHint("");
        editText5.setBackground(null);
        editText5.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editText5);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }


    /**
     * 多行文本
     */
    public static View addMoreTextView(PageActivity pageActivity, String name) {
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
        editMoreText = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText.setHint("");
        editMoreText.setBackground(null);
        editMoreText.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView1(PageActivity pageActivity, String name) {
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
        editMoreText1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText1.setHint("");
        editMoreText1.setBackground(null);
        editMoreText1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView2(PageActivity pageActivity, String name) {
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
        editMoreText2 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText2.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText2.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText2.setHint("");
        editMoreText2.setBackground(null);
        editMoreText2.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText2);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView3(PageActivity pageActivity, String name) {
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
        editMoreText3 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText3.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText3.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText3.setHint("");
        editMoreText3.setBackground(null);
        editMoreText3.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText3);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView4(PageActivity pageActivity, String name) {
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
        editMoreText4 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText4.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText4.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText4.setHint("");
        editMoreText4.setBackground(null);
        editMoreText4.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText4);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreTextView5(PageActivity pageActivity, String name) {
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
        editMoreText5 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editMoreText5.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editMoreText5.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editMoreText5.setHint("");
        editMoreText5.setBackground(null);
        editMoreText5.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editMoreText5);//将TextView 添加到子View 中
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
    public static View addNumView(PageActivity pageActivity, String name) {
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
        editNum = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum.setHint("");
        editNum.setBackground(null);
        editNum.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView1(PageActivity pageActivity, String name) {
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
        editNum1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum1.setHint("");
        editNum1.setBackground(null);
        editNum1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView2(PageActivity pageActivity, String name) {
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
        editNum2 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum2.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum2.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum2.setHint("");
        editNum2.setBackground(null);
        editNum2.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum2);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView3(PageActivity pageActivity, String name) {
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
        editNum3 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum3.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum3.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum3.setHint("");
        editNum3.setBackground(null);
        editNum3.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum3);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView4(PageActivity pageActivity, String name) {
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
        editNum4 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum4.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum4.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum4.setHint("");
        editNum4.setBackground(null);
        editNum4.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum4);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addNumView5(PageActivity pageActivity, String name) {
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
        editNum5 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        editNum5.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        editNum5.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        editNum5.setHint("");
        editNum5.setBackground(null);
        editNum5.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        editNum5.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(editNum5);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加金额
     */
    public static View addMoneyView(PageActivity pageActivity, String name) {
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
        etMoney = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etMoney.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        etMoney.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etMoney.setHint("");
        etMoney.setBackground(null);
        etMoney.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etMoney.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etMoney);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoneyView1(PageActivity pageActivity, String name) {
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
        etMoney1 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etMoney1.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        etMoney1.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etMoney1.setHint("");
        etMoney1.setBackground(null);
        etMoney1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etMoney1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etMoney1);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoneyView2(PageActivity pageActivity, String name) {
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
        etMoney2 = new EditText(pageActivity);
        View view1 = new View(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etMoney2.setLayoutParams(vlp);
        view1.setLayoutParams(vlp2);
        textView.setTextSize(15);
        etMoney2.setTextSize(15);
        view1.setPadding(2, 0, 0, 0);
        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etMoney2.setHint("");
        etMoney2.setBackground(null);
        etMoney2.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etMoney2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etMoney2);//将TextView 添加到子View 中
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
    public static View addTimeView(final PageActivity pageActivity, String name) {
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
        etTime = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etTime.setLayoutParams(vlp);
        textView.setTextSize(15);
        etTime.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etTime.setHint(R.string.workoffice_time);
        etTime.setBackground(null);
        etTime.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etTime.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etTime);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addTimeView1(final PageActivity pageActivity, String name) {
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
        etTime1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etTime1.setLayoutParams(vlp);
        textView.setTextSize(15);
        etTime1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etTime1.setHint(R.string.workoffice_time);
        etTime1.setBackground(null);
        etTime1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etTime1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etTime1);
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
    public static View addDateView(PageActivity pageActivity, String name) {
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
        etData = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etData.setLayoutParams(vlp);
        etData.setOnClickListener(pageActivity);
        textView.setTextSize(15);
        etData.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etData.setHint(R.string.workoffice_date);
        etData.setBackground(null);
        etData.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etData.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etData);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addDateView1(PageActivity pageActivity, String name) {
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
        etData1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp1);//设置TextView的布局
        etData1.setLayoutParams(vlp);
        etData1.setOnClickListener(pageActivity);
        textView.setTextSize(15);
        etData1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        etData1.setHint(R.string.workoffice_date);
        etData1.setBackground(null);
        etData1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        etData1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(etData1);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加多选框
     *
     * @param pageActivity
     * @param name
     * @return
     */
    public static View addMoreCheckView(PageActivity pageActivity, String name) {
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
        tvMore = new TextView(pageActivity);
        textView.setLayoutParams(vlp);//设置TextView的布局
        tvMore.setLayoutParams(vlp1);
        textView.setTextSize(15);
        tvMore.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        tvMore.setHint("请选择数据");
        tvMore.setBackground(null);
        tvMore.setTop(10);
        tvMore.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(tvMore);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    public static View addMoreCheckView1(PageActivity pageActivity, String name) {
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
        tvMore1 = new TextView(pageActivity);
        textView.setLayoutParams(vlp);//设置TextView的布局
        tvMore1.setLayoutParams(vlp1);
        textView.setTextSize(15);
        tvMore1.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        tvMore1.setHint("请选择数据");
        tvMore1.setBackground(null);
        tvMore1.setTop(10);
        tvMore1.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(tvMore1);
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
    public static View addImageView(PageActivity pageActivity, String name) {
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
        tvImage = new TextView(pageActivity);
        textView.setLayoutParams(vlp);//设置TextView的布局
        tvImage.setLayoutParams(vlp1);
        textView.setTextSize(15);
        tvImage.setTextSize(15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(name);
        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
        tvImage.setText(R.string.workoffice_image);
        tvImage.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
        view.addView(textView);//将TextView 添加到子View 中
        view.addView(tvImage);
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

    /**
     * 添加line分界线
     *
     * @param pageActivity
     * @return
     */
    public static View addLineView(PageActivity pageActivity) {
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


    //    /**
//     * 添加textView和Edittext
//     *
//     * @param pageActivity
//     * @return
//     */
//    public static View addSendView(PageActivity pageActivity) {
//        // TODO 动态添加布局(java方式)
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout view = new LinearLayout(pageActivity);
//        view.setLayoutParams(lp);//设置布局参数
//        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
//        //定义子View中两个元素的布局
//        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, 150);
//        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
//        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
//        TextView textView = new TextView(pageActivity);
//        EditText editText = new EditText(pageActivity);
//        View view1 = new View(pageActivity);
//        textView.setLayoutParams(vlp1);//设置TextView的布局
//        editText.setLayoutParams(vlp);
//        view1.setLayoutParams(vlp2);
//        textView.setTextSize(15);
//        editText.setTextSize(15);
//        view1.setPadding(2, 0, 0, 0);
//        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//        textView.setText("接收人:");
//        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
//        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
//        editText.setHint(R.string.workoffice_send);
//        editText.setBackground(null);
//        editText.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
//        view.addView(textView);//将TextView 添加到子View 中
//        view.addView(editText);//将TextView 添加到子View 中
//        view.setBackgroundResource(R.color.milkwhite);
//        return view;
//    }
//
//    /**
//     * 添加textView和Edittext
//     *
//     * @param pageActivity
//     * @return
//     */
//    public static View addCopyView(PageActivity pageActivity) {
//        // TODO 动态添加布局(java方式)
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout view = new LinearLayout(pageActivity);
//        view.setLayoutParams(lp);//设置布局参数
//        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
//        //定义子View中两个元素的布局
//        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, 150);
//        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 150);
//        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(2, 100);
//        TextView textView = new TextView(pageActivity);
//        EditText editText = new EditText(pageActivity);
//        View view1 = new View(pageActivity);
//        textView.setLayoutParams(vlp1);//设置TextView的布局
//        editText.setLayoutParams(vlp);
//        view1.setLayoutParams(vlp2);
//        textView.setTextSize(15);
//        editText.setTextSize(15);
//        view1.setPadding(2, 0, 0, 0);
//        view1.setBackgroundColor(pageActivity.getResources().getColor(R.color.beige));
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//        textView.setText("抄送人:");
//        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
//        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
//        editText.setHint(R.string.workoffice_copy);
//        editText.setBackground(null);
//        editText.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
//        view.addView(textView);//将TextView 添加到子View 中
//        view.addView(editText);//将TextView 添加到子View 中
//        view.setBackgroundResource(R.color.milkwhite);
//        return view;
//    }
//    /**
//     * 添加附件
//     *
//     * @param pageActivity
//     * @param name
//     * @return
//     */
//    public static View addAttachView(PageActivity pageActivity, String name) {
//        // TODO 动态添加布局(java方式)
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout view = new LinearLayout(pageActivity);
//        view.setLayoutParams(lp);//设置布局参数
//        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
//        //定义子View中两个元素的布局
//        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
//                300, 150);
//        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(400, 150);
//        TextView textView = new TextView(pageActivity);
//        tvData = new TextView(pageActivity);
//        textView.setLayoutParams(vlp);//设置TextView的布局
//        tvData.setLayoutParams(vlp1);
//        textView.setTextSize(15);
//        tvData.setTextSize(15);
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//        textView.setText(name);
//        textView.setTextColor(pageActivity.getResources().getColor(R.color.black));
//        textView.setPadding(calculateDpToPx(20, pageActivity), 0, 0, 0);//设置边距
//        tvData.setText(R.string.workoffice_attach);
//        tvData.setPadding(calculateDpToPx(10, pageActivity), 0, 0, 0);//设置边距
//        view.addView(textView);//将TextView 添加到子View 中
//        view.addView(tvData);
//        view.setBackgroundResource(R.color.milkwhite);
//        return view;
//    }

    /**
     * 上传图片后显示
     *
     * @param pageActivity
     * @return
     */
    public static View addShowImageView(PageActivity pageActivity) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(300, 300);
        ViewGroup.LayoutParams vlp1 = new ViewGroup.LayoutParams(300, 300);
        imageView = new ImageView(pageActivity);
        imageView1 = new ImageView(pageActivity);
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

    public static View addView(PageActivity pageActivity) {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, 500);
        LinearLayout view = new LinearLayout(pageActivity);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(500, 300);
        TextView textView = new TextView(pageActivity);
        textView.setLayoutParams(vlp);
        view.addView(textView);//将TextView 添加到子View 中
        view.setBackgroundResource(R.color.milkwhite);
        return view;
    }

//    /**
//     * 添加button
//     *
//     * @param pageActivity
//     * @return
//     */
//    public static View addButtonView(PageActivity pageActivity) {
//        // TODO 动态添加布局(java方式)
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 150);
//        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        RelativeLayout view = new RelativeLayout(pageActivity);
//        view.setPadding(calculateDpToPx(35, pageActivity), 0, 35, 0);
//        view.setLayoutParams(lp);//设置布局参数
//        //定义子View中两个元素的布局
//        RelativeLayout.LayoutParams vlp = new RelativeLayout.LayoutParams(600, 150);
//        btn = new Button(pageActivity);
//        btn.setLayoutParams(vlp);
//        btn.setTextSize(17);
//        //setMargins(btn,50,50,50,15);
//        btn.setPadding(calculateDpToPx(35, pageActivity), 35, 35, 35);//设置边距
//        btn.setBackgroundResource(R.color.color_bg_selected);
//        btn.setText("提交");
//        btn.setTextColor(pageActivity.getResources().getColor(R.color.white));
//        btn.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        view.addView(btn, lp);//将TextView 添加到子View 中
//        view.setBackgroundResource(R.color.milkwhite);
//        return view;
//    }

    private static int calculateDpToPx(int padding_in_dp, PageActivity pageActivity) {
        final float scale = pageActivity.getResources().getDisplayMetrics().density;
        return (int) (padding_in_dp * scale + 0.5f);
    }

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.TAG_ONE:
                    Toast.makeText(PageActivity.this, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PageActivity.this, LoginActivity.class);
                    intent.putExtra("tag", "false");
                    new SharedPreferencesHelper(PageActivity.this, "login").removeData(PageActivity.this);
                    startActivity(intent);
                    ProgressDialogUtil.stopLoad();
                    break;
                case TAG_TWO:
                    if (messageT.equals("null")) {
                        ProgressDialogUtil.stopLoad();
                        finish();
                    } else {
                        Toast.makeText(PageActivity.this, messageT, Toast.LENGTH_SHORT).show();
                        ProgressDialogUtil.stopLoad();
                    }
                    break;
                case Constant.TAG_THREE:
                    Toast.makeText(PageActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    if (imageView != null && imageView1 != null) {
                        imageView.setVisibility(View.VISIBLE);
                        imageView1.setVisibility(View.VISIBLE);
                        if (imageView.getDrawable() == null) {
                            imageView.setImageBitmap(newbitmap);
                        } else if (imageView.getDrawable() != null && imageView1.getDrawable() == null) {
                            imageView1.setImageBitmap(newbitmap);
                            tvData.setEnabled(false);
                        }
                    }
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_FOUR:
                    Toast.makeText(PageActivity.this, messageT, Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    @OnClick({R.id.tvSend, R.id.tvCopy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSend:
                Intent intent = new Intent(this, SendPersonActivity.class);
                intent.putExtra("send", "审核人");
                startActivityForResult(intent, Constant.TAG_FIVE);
                break;
            case R.id.tvCopy:
                Intent intent1 = new Intent(this, SendPersonActivity.class);
                intent1.putExtra("send", "抄送人");
                startActivityForResult(intent1, Constant.TAG_SIX);
                break;
        }
    }
}