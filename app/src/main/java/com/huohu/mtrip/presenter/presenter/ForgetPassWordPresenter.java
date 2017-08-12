package com.huohu.mtrip.presenter.presenter;


import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.presenter.contract.ForgetPassWordContract;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class ForgetPassWordPresenter implements ForgetPassWordContract.Presenter {

    private ForgetPassWordContract.View mView;

    public ForgetPassWordPresenter(ForgetPassWordContract.View view) {
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
    public Observable<WebResponse> getConfirmCode(String phoneNum) {
        HashMap<String,Object> params  = new HashMap<>();
        params.put("mobile",phoneNum);
        params.put("type",2);
        return WebCall.getInstance().call(WebKey.func_sendSms,params);
    }
    @Override
    public void resetPassWord(HashMap<String, Object> params) {
        WebCall.getInstance().call(WebKey.func_reset,params).subscribe(new BaseSubscriber<WebResponse>(mView.getContext(),"请稍后，正在提交数据") {
            @Override
            public void onNext(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(webResponse);
                if (status) {
                    String msg = webResponse.getData();
                    mView.resetOk(msg);
                } else {
                    MToast.showToast(WebUtils.getWebMsg(webResponse));
                }
            }
        });
    }
}

