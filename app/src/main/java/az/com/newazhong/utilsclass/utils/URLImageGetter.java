package az.com.newazhong.utilsclass.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import az.com.newazhong.utilsclass.base.MyApplication;

/**
 * Created by Administrator on 2018/9/28.
 */

public class URLImageGetter implements Html.ImageGetter{
    private String shopDeString;
    private TextView textView;
    Context context;
    int width;
    int height;
    private DisplayImageOptions options;
    public URLImageGetter(String shopDeString,Context context,TextView textView,DisplayImageOptions options,int width,int height) {
        this.shopDeString = shopDeString;
        this.context = context;
        this.textView = textView;
        this.options = options;
        this.width = width;
        this.height = height;
    }
    @Override
    public Drawable getDrawable(String source) {
        //解决UIL加载同一个URL图片时，导致下一个task被cancel掉的问题
        final URLDrawable urlDrawable = new URLDrawable();
        ImageSize imageSize = new ImageSize(380,380);
        ImageView imageView = new ImageView(MyApplication.getContextObject());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        NonViewAware nonViewAware = new NonViewAware(imageSize, ViewScaleType.fromImageView(imageView));
        ImageLoader.getInstance().displayImage(source,nonViewAware,options,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);



                Bitmap mBitmap = loadedImage.copy(Bitmap.Config.RGB_565 , true);
                int he = loadedImage.getHeight();
                int wi = loadedImage.getWidth();
                double size = Double.valueOf(loadedImage.getWidth())/Double.valueOf(width);
                mBitmap.setWidth(width);
                mBitmap.setHeight((int) (he*size));
                urlDrawable.bitmap = mBitmap;
                urlDrawable.setBounds(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                textView.invalidate();
                textView.setText(textView.getText()); // 解决图文重叠
            }
        });
        return urlDrawable;
    }
}
