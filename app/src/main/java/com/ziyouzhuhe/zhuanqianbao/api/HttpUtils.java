package com.ziyouzhuhe.zhuanqianbao.api;

import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/12/13.
 */

public class HttpUtils {
    Retrofit retrofit;

    public  HttpUtils() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Rxjava
                    .build();
    }

    /**
     * 请求返回的一般数据
     */
    public void requstToString() {

        DouApi douApi = retrofit.create(DouApi.class);
        Call<ResponseBody> responseBodyCall = douApi.getMainJson("ower");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 请求返回对应的moudel
     */
    public void requstToMoudel(){
        DouApi douApi = retrofit.create(DouApi.class);
       Call<MyMoudel>myMoudelCall =  douApi.getMyMyJson("mou");
        myMoudelCall.enqueue(new Callback<MyMoudel>() {
            @Override
            public void onResponse(Call<MyMoudel> call, Response<MyMoudel> response) {
                    MyMoudel myMoudel = response.body();
                Log.d(TAG, "onResponse: "+myMoudel.getMenu().getValue());
            }

            @Override
            public void onFailure(Call<MyMoudel> call, Throwable t) {

            }
        });
    }


    /**
     * RxJava
     * 请求返回对应的moudel
     */
    public void requstRToMoudel(){
        DouApi douApi = retrofit.create(DouApi.class);
        douApi.getRxMyJson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyMoudel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMoudel myMoudel) {
                        Log.d(TAG, "onNext: "+myMoudel.getMenu().getValue());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
