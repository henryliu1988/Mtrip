package com.huohu.mtrip.presenter.contract;


import com.huohu.mtrip.presenter.BasePresenter;
import com.huohu.mtrip.presenter.BaseView;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public interface MainContract {

    interface View extends BaseView<Presenter>
    {
    }

    interface Presenter extends BasePresenter
    {

    }
}
