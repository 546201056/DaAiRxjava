package com.ziyouzhuhe.zhuanqianbao.api;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ziyouzhuhe.zhuanqianbao.utils.LogUtil;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
        okHttpClientBuilder.addNetworkInterceptor(new NetworkInterceptor());//拦截器
        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Rxjava
                    .client(okHttpClient)
                    .build();
    }

    /**
     * 自定义网络拦截器
     */
    public class NetworkInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            //=========发送===========
            Request request = chain.request();
            HttpUrl requestUrl=request.url();
            Connection requestConnection=chain.connection();
            Headers requestHeaders=request.headers();
            //打印发送信息
            LogUtil.e("===NetworkInterceptor===发送==requestUrl="+requestUrl);
            LogUtil.e("===NetworkInterceptor===发送==requestConnection="+requestConnection);
            LogUtil.e("===NetworkInterceptor===发送==requestHeaders="+requestHeaders);
            return null;
        }
    }

    /**
     * 请求返回的一般数据
     */
    public void requstToString() {

        APIService APIService = retrofit.create(APIService.class);
        Call<ResponseBody> responseBodyCall = APIService.getMainJson("ower");
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
        APIService APIService = retrofit.create(APIService.class);
       Call<MyMoudel>myMoudelCall =  APIService.getMyMyJson("mou");
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
        APIService APIService = retrofit.create(APIService.class);
        APIService.getRxMyJson()
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
