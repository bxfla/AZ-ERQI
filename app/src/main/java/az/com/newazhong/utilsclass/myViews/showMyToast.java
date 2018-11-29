package az.com.newazhong.utilsclass.myViews;

import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell on 2018/1/4.
 */

public class showMyToast {
    public void showToast(final Toast toast, final int cnt) {
        final Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        },0,3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }

}
