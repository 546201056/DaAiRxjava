package com.ziyouzhuhe.zhuanqianbao.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Administrator on 2017/8/23.
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}