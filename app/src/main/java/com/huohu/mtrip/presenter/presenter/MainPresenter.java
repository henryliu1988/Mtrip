package com.huohu.mtrip.presenter.presenter;

import android.text.TextUtils;

import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.presenter.contract.MainContract;
import com.huohu.mtrip.util.Utils;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    public MainPresenter(MainContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        start();
    }
    @Override
    public void start() {
        tryLogInBackGroud();
    }


    public void tryLogInBackGroud() {
        String phoneNum = Utils.toString(SPUtils.get("login_phoneNum", ""));
        String passoword = Utils.toString(SPUtils.get("login_password", ""));
        boolean autoLogin = Utils.toBoolean(SPUtils.get("login_auto", false));
        if (!TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(passoword) && autoLogin) {
            UserData.getInstance().tryLoginManager(phoneNum, passoword).subscribe(new BaseSubscriber<Map<String, Object>>() {
                @Override
                public void onNext(Map<String, Object> info) {
                    boolean status = Utils.toBoolean(info.get("status"));
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } else {
            if (mView != null) {
            }
        }

    }

    @Override
    public void finish() {

    }
}
