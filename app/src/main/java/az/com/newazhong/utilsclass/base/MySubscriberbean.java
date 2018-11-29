package az.com.newazhong.utilsclass.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import az.com.newazhong.utilsclass.utils.ApiErrorHelper;
import az.com.newazhong.utilsclass.utils.NetUtils;
import az.com.newazhong.utilsclass.utils.ProgressDialogUtil;
import az.com.newazhong.utilsclass.utils.RxApiManager;
import rx.Subscriber;

/**
 * Created by dell on 2017/4/24.
 */

public class MySubscriberbean<T> extends Subscriber<T> {
    private Context context;
    private String message;//加载中的提示信息
    private boolean isConnect;
    private BaseRequestBackLisenter<T> reslutSuccess;
    private BaseRequestBackLisenterSuccessFail<T> reslutLister;
    private String httpTag;//请求标志
    private boolean isHidden=false;//是否隐藏进度条
    private boolean isBackFail;//是否返回错误结果
    public MySubscriberbean() {
    }
    public MySubscriberbean(String httpTag, Context context, String message, BaseRequestBackLisenter<T> reslutSuccess) {
        this.context=context;
        this.message=message;
        this.reslutSuccess=reslutSuccess;
        this.httpTag=httpTag;
        isBackFail=false;
    }
    public MySubscriberbean(String httpTag, Context context, String message, boolean isHidden, BaseRequestBackLisenter<T> reslutSuccess) {
        this.context=context;
        this.message=message;
        this.reslutSuccess=reslutSuccess;
        this.httpTag=httpTag;
        this.isHidden=isHidden;
        isBackFail=false;
    }
    public MySubscriberbean(String httpTag, Context context, String message, boolean isHidden, BaseRequestBackLisenterSuccessFail<T> reslutSuccess) {
        this.context=context;
        this.message=message;
        this.reslutLister=reslutSuccess;
        this.httpTag=httpTag;
        this.isHidden=isHidden;
        isBackFail=true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!NetUtils.isNetworkAvailable(context)){
            isConnect=false;
            Toast.makeText(context,"网络不可用，请检查网络！",Toast.LENGTH_SHORT).show();
        }else{
            isConnect=true;
            RxApiManager.get().add(httpTag,this);
            if(isHidden==false){
                ProgressDialogUtil.startLoad(context,message);
            }
        }
    }

    @Override
    public void onCompleted() {
        ProgressDialogUtil.stopLoad();
        RxApiManager.get().remove(httpTag);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Tag","请求错误日志"+e.toString());
        if(isHidden==false){
            ProgressDialogUtil.stopLoad();
        }
        RxApiManager.get().remove(httpTag);
        if(isConnect){
            ApiErrorHelper.handleCommonError(context,e);
        }
    }

    @Override
    public void onNext(T t) {
        if(!isHidden){
            ProgressDialogUtil.stopLoad();
        }
        RxApiManager.get().remove(httpTag);
        reslutSuccess.success(t);
    }
//    @Override
//    public void onNext(T httpResultNew) {
//        if(!isHidden){
//            ProgressDialogUtil.stopLoad();
//        }
//        RxApiManager.get().remove(httpTag);
//        reslutSuccess.success((T) httpResultNew);
//        if(httpResultNew.getStateCode().equals("100")){
//            if(isBackFail){
//                if(null!=httpResultNew.getObj()){
//                    //SendOrderResult sendOrderResult= (SendOrderResult) httpResultNew.getObj();
//                    reslutLister.success(httpResultNew.getObj());
//                }else {
//                    reslutLister.success(null);
//                }
//            }else{
//                if(null!=httpResultNew.getObj()){
//                    reslutSuccess.success(httpResultNew.getObj());
//                }else {
//                    reslutSuccess.success(null);
//                }
//            }
//
//        }else if(httpResultNew.getStateCode().equals("104")){
//            //// TODO: 2017/8/2
//            context.startActivity(intent);
//            Toast.makeText(context,httpResultNew.getMsg(),Toast.LENGTH_SHORT).show();*/
//        }
//        else{
//            if(isBackFail){
//                if(reslutLister!=null){
//                    if(httpResultNew.getObj()==null){
//                    }else{
//                        reslutLister.fail(httpResultNew.getObj());
//                    }
//                }
//            }else{
//                reslutSuccess.fail(httpResultNew.getMsg());
//                //Toast.makeText(context,httpResultNew.getMsg(),Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
