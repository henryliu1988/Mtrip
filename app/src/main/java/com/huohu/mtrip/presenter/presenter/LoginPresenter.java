package com.huohu.mtrip.presenter.presenter;

import android.text.TextUtils;

import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.presenter.contract.LoginContract;
import com.huohu.mtrip.util.Utils;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        start();
        mView.setPresenter(this);

    }

    @Override
    public void start() {
        loadPrefrence();
    }

    @Override
    public void finish() {

    }

    @Override
    public void tryLogin(final String phoneNum, final String password) {
        UserData.getInstance().tryLoginManager(phoneNum, password).subscribe(new BaseSubscriber<Map<String, Object>>() {
            @Override
            public void onNext(Map<String, Object> info) {
                boolean status = Utils.toBoolean(info.get("status"));
                String msg = Utils.toString(info.get("msg"));
                if (status) {
                    mView.onLoginSucess();
                } else {
                    mView.onLoginFail();
                }
            }
        });
    }





    private void loadPrefrence() {
        String phoneNum = Utils.toString(SPUtils.get("login_phoneNum", ""));
        String passoword = Utils.toString(SPUtils.get("login_password", ""));
        if (!TextUtils.isEmpty(phoneNum) || !TextUtils.isEmpty(passoword)) {
            mView.initPreferenceInfo(phoneNum, passoword);
        }
    }

}

