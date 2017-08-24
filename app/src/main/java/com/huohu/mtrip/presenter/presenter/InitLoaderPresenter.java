package com.huohu.mtrip.presenter.presenter;

import android.text.TextUtils;

import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.model.refresh.RefreshWithData;
import com.huohu.mtrip.presenter.contract.InitLoaderContract;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class InitLoaderPresenter implements InitLoaderContract.Presenter {

    private InitLoaderContract.View mView;

    public InitLoaderPresenter(InitLoaderContract.View view) {
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
                    mView.gotoMainTabs();
                }

                @Override
                public void onError(Throwable e) {
                    mView.gotoMainTabs();
                }
            });
        } else {
            if (mView != null) {
                mView.gotoMainTabs();
            }
        }
    }

    @Override
    public void finish() {

    }


}
