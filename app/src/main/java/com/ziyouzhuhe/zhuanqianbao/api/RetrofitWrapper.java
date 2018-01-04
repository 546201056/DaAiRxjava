package com.ziyouzhuhe.zhuanqianbao.api;//package com.ziyouzhuhe.zhuanqianbao.net;



import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ziyouzhuhe.zhuanqianbao.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 使用Retrofit请求接口的封装类,为每个api请求封装基本参数
 * Created by jph on 2016/11/17.
 */
public class RetrofitWrapper {
    private APIService mAPIService;
    private static final int DEFAULT_TIMEOUT = 7676;

    public RetrofitWrapper() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("---API---", "log: "+message);
                    }
                });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor);


            clientBuilder.addInterceptor(new NetworkInterceptor());

        OkHttpClient okHttpClient = clientBuilder.build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        mAPIService = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(DataConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(APIService.class);
    }

    public APIService getAPIService() {
        return mAPIService;
    }

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

//    protected <T> ObservableTransformer<APIResponse<T>, T> getTransformer() {
//        return (ObservableTransformer<APIResponse<T>, T>) new ObservableTransformer() {
//            @Override
//            public ObservableSource apply(@NonNull Observable upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .flatMap(new Function<APIResponse, Observable>() {
//                            @Override
//                            public Observable apply(@NonNull APIResponse apiResponse) throws Exception {
//                                if (apiResponse.isSuccess()) {
//                                    if (apiResponse.getData() != null) {
//                                        return Observable.just(apiResponse.getData());
//                                    }
//                                } else {
//                                    return Observable.error(new APIException(apiResponse.getCode(),
//                                            apiResponse.getMsg()));
//                                }
//                                return Observable.empty();
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

    /**
     * 得到只返回服务器message的transformer，转换过程中不处理data
     *
     * @return
     */
//    protected ObservableTransformer<APIResponse, String> getMessageTransformer() {
//        return (ObservableTransformer<APIResponse, String>) new ObservableTransformer() {
//            @Override
//            public ObservableSource apply(@NonNull Observable upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .flatMap(new Function<APIResponse, Observable>() {
//                            @Override
//                            public Observable apply(@NonNull APIResponse apiResponse) throws Exception {
//                                if (apiResponse.isSuccess()) {
//                                    if (apiResponse.getMsg() != null) {
//                                        return Observable.just(apiResponse.getMsg());
//                                    }
//                                } else {
//                                    return Observable.error(new APIException(apiResponse.getCode(),
//                                            apiResponse.getMsg()));
//                                }
//                                return Observable.empty();
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//    protected Observable<APIResponse> noDataObservable(Observable<APIResponse> observable) {
//        observable.subscribeOn(Schedulers.io())
//                .flatMap(new Function<APIResponse, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(@NonNull APIResponse apiResponse) throws Exception {
//                        if (apiResponse.isSuccess()) {
//                            if (apiResponse.getData() == null) {
//                                return Observable.just(apiResponse);
//                            }
//                        } else {
//                            return Observable.error(new APIException(apiResponse.getCode(),
//                                    apiResponse.getMsg()));
//                        }
//                        return Observable.empty();
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//        return observable;
//
//    }


    private static boolean isDisposedObserver(Observer observer) {
        if (observer instanceof Disposable &&
                ((Disposable) observer).isDisposed()) {
            return true;
        }

        return false;
    }
}
