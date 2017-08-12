package com.huohu.mtrip.presenter.presenter;


import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.model.refresh.RefreshWithData;
import com.huohu.mtrip.presenter.contract.RegisterContract;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class RegisterPresenter implements RegisterContract.Presenter,RefreshWithData {

    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
        start();
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }


    @Override
    public Observable<WebResponse> getConfirmCode(final String phoneNum){
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", phoneNum);
        params.put("type",2);
        return WebCall.getInstance().call(WebKey.func_checkMobile,params).flatMap(new Func1<WebResponse, Observable<WebResponse>>() {
            @Override
            public Observable<WebResponse> call(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(webResponse);
                if (status) {
                    WebResponse response = new WebResponse(1,"该手机号码已经注册,请直接登录","");
                    return Observable.just(response);
                } else {
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("mobile", phoneNum);
                    params.put("type", 2);
                    return WebCall.getInstance().call(WebKey.func_sendSms, params);
                }
            }
        });
    }

    @Override
    public void register(final HashMap<String, Object> params) {
        params.put("nickname","");
        params.put("type", "2");
        WebCall.getInstance().call(WebKey.func_register,params).subscribe(new BaseSubscriber<WebResponse>() {
            @Override
            public void onNext(WebResponse webResponse) {
                String data = webResponse.getData();
                boolean status = WebUtils.getWebStatus(webResponse);
                if (status){
                    MToast.showToast("注册成功");
                    String phoneNum = Utils.toString(params.get("mobile"));
                    String passWordMd5 = Utils.toString(params.get("password"));
                    RefreshManager.getInstance().addNewListener(RefreshKey.LOGIN_RESULT_BACK,RegisterPresenter.this);
                    UserData.getInstance().tryLoginManager(phoneNum,passWordMd5,mView.getContext());
                    mView.registerOK(Utils.toString(data));
                } else {
                    MToast.showToast(data);
                }

            }
        });
    }

    @Override
    public void onRefreshWithData(int key, Object data) {
        if (key == RefreshKey.LOGIN_RESULT_BACK) {
            Map<String, Object> dataMap = Utils.parseObjectToMapString(data);
            boolean status = Utils.toBoolean(dataMap.get("status"));
            String msg = Utils.toString(dataMap.get("msg"));
            if (status) {
                mView.registerOK("");
            } else {
                MToast.showToast("注册失败");
            }
        }
    }
}

