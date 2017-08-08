package com.huohu.mtrip.app;

import android.app.Application;
import android.content.Context;

import com.huohu.mtrip.model.data.AppDataManager;
import com.huohu.mtrip.util.ImageUtils;

/**
 * Created by Administrator on 2017/8/8.
 */

public class MApplication extends Application {

    private static MApplication instance;

    public static MApplication getInstance() {
        return instance;
    }

    public Context getContext() {
        return this.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    private void init() {
        instance = this;
        ImageUtils.getInstance().initImageLoader();
        AppDataManager.getInstance().initData();
    }


}
