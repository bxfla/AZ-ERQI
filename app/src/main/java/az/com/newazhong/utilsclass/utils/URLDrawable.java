package az.com.newazhong.utilsclass.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by dell on 2018/9/9.
 */

public class URLDrawable extends BitmapDrawable {
    protected Bitmap bitmap;
    @Override
    public void draw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, getPaint());
        }
    }
}
