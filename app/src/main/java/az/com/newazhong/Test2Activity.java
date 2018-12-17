package az.com.newazhong;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.utils.UritoFile;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test2Activity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.activity_test2)
    RelativeLayout activityTest2;

    String sPath = "";
    Uri uri;
    File valueFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
//        FastUtils.getFastData(url, new BaseFastJSONRequestBackLisenter() {
//            @Override
//            public void success(String o) {
//                Bean resultBeanData = JSON.parseObject(o,Bean.class);
//                String name = resultBeanData.getMsg();
//                Log.d("XXX",name);
//            }
//
//            @Override
//            public void fail(String message) {
//                Log.e("XXX","解析成功失败");
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constant.TAG_ONE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                    try {
                        startActivityForResult(Intent.createChooser(intent, "Choose File"), Constant.TAG_ONE);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, "亲，木有文件管理器啊-_-!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "权限被拒绝，请手动开启", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    // 文件选择完之后，自动调用此函数
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.TAG_ONE) {
                uri = data.getData();
                sPath = UritoFile.getPath(Test2Activity.this,uri);
                valueFile = new File(sPath);
                Toast.makeText(this, sPath, Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("XXX", "onActivityResult() error, resultCode: " + resultCode);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        if (ContextCompat.checkSelfPermission(Test2Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(Test2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Test2Activity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    Constant.TAG_ONE);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            try {
                startActivityForResult(Intent.createChooser(intent, "Choose File"), Constant.TAG_ONE);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "亲，木有文件管理器啊-_-!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
