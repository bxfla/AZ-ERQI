package az.com.newazhong.utilsclass.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import az.com.newazhong.utilsclass.base.Constant;
import az.com.newazhong.utilsclass.base.HttpResultNew;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dell on 2017/4/20.
 */

public class HttpUtilsNew {
    private static HttpUtilsNew instance;

    private Retrofit retrofit;

    private static final OkHttpClient client  = new OkHttpClient.Builder().connectTimeout(20000, TimeUnit.SECONDS)
            .readTimeout(20000,TimeUnit.SECONDS)
            .writeTimeout(20000,TimeUnit.SECONDS).build();


    private HttpUtilsNew() {
        instance = this;
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static HttpUtilsNew getInstance() {
        if (instance == null) {
            synchronized (HttpUtilsNew.class) {
                if (instance == null) {
                    return new HttpUtilsNew();
                }
            }
        }
        return instance;
    }

    public static <T> T getService(Class<T> c) {
        return getInstance().retrofit.create(c);
    }

    public static <T> void init(Observable<HttpResultNew<T>> observable, Subscriber<HttpResultNew<T>> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public static <T> void initlist(Observable<T> observable, Subscriber<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
