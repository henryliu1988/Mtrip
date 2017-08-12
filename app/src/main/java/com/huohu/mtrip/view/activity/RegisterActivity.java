package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huohu.mtrip.R;
import com.huohu.mtrip.presenter.contract.RegisterContract;
import com.huohu.mtrip.presenter.presenter.RegisterPresenter;
import com.huohu.mtrip.view.wighet.MyCountTimer;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.View{



    private RegisterContract.Presenter mPresenter;
    private MyCountTimer mCountTimer;

    private String mConfirSmsCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        ButterKnife.bind(this);
        new RegisterPresenter(this);
        //mCountTimer = new MyCountTimer(confirmCodeGet,0xff658dff,0xff658dff);
    }

    private void tryToRegister() {
    }
    private void tryGetConfirmCode() {
    }

    @Override
    public void registerOK(String msg) {
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
