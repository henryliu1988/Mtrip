package com.huohu.mtrip.presenter.presenter;

import com.huohu.mtrip.presenter.contract.MainContract;

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

    }

    @Override
    public void finish() {

    }
}
