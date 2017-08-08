package com.huohu.mtrip.model.data;


import com.huohu.mtrip.app.MApplication;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.util.DataCleanManager;
import com.huohu.mtrip.util.ImageUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class AppDataManager {


    private static AppDataManager appdata;

    public AppDataManager() {
    }


    public static AppDataManager getInstance() {
        if (appdata == null) {
            appdata = new AppDataManager();
        }
        return appdata;
    }

    public void initData() {
        DicData.getInstance().init();
    }

    public void initLoginSucData() {
        MsgData.getInstance().loadData();
        UserData.getInstance().loadUserData();
    }
    public Observable<WebResponse> clearDataCache() {
        return Observable.create(new Observable.OnSubscribe<WebResponse>() {
            @Override
            public void call(Subscriber<? super WebResponse> subscriber) {
                DataCleanManager.cleanApplicationData(MApplication.getInstance().getContext());
                ImageUtils.getInstance().cleanDiskCache();
                WebResponse result = new WebResponse();
                result.setError(0);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
