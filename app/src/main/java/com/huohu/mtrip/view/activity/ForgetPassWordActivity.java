package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huohu.mtrip.R;
import com.huohu.mtrip.presenter.contract.ForgetPassWordContract;
import com.huohu.mtrip.presenter.presenter.ForgetPassWordPresenter;
import com.huohu.mtrip.view.wighet.MyCountTimer;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class ForgetPassWordActivity extends BaseActivity implements ForgetPassWordContract.View {


    private MyCountTimer mCountTimer;

    private String mConfirSmsCode;

    private ForgetPassWordContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        new ForgetPassWordPresenter(this);
       // mCountTimer = new MyCountTimer(confirmCodeGet, 0xff658dff, 0xff658dff);
    }

    private void tryGetConfirmCode() {
    }

    private void tryConfirmReset() {

    }

    @Override
    public void resetOk(String msg) {
    }

    @Override
    public void setPresenter(ForgetPassWordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
