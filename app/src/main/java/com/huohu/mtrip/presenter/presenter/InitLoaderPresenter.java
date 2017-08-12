package com.huohu.mtrip.presenter.presenter;

import android.text.TextUtils;

import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.data.UserData;
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
public class InitLoaderPresenter implements InitLoaderContract.Presenter,RefreshWithData {

    private InitLoaderContract.View mView;

    public InitLoaderPresenter(InitLoaderContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        start();
        RefreshManager.getInstance().addNewListener(RefreshKey.LOGIN_RESULT_BACK, this);

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
            UserData.getInstance().tryLoginManager(phoneNum, passoword, null);
        } else {
            if (mView != null) {
                mView.gotoMainTabs();
            }
        }
    }

    @Override
    public void finish() {
        RefreshManager.getInstance().removeListner(RefreshKey.LOGIN_RESULT_BACK, this);

    }

    @Override
    public void onRefreshWithData(int key, Object data) {
        if (key == RefreshKey.LOGIN_RESULT_BACK) {
            Map<String, Object> dataMap = Utils.parseObjectToMapString(data);
            boolean status = Utils.toBoolean(dataMap.get("status"));
            String msg = Utils.toString(dataMap.get("msg"));
            if (mView != null) {
                mView.gotoMainTabs();
            }
    }
    }
}
