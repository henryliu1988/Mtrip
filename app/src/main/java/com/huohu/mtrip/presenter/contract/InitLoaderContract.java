package com.huohu.mtrip.presenter.contract;


import com.huohu.mtrip.presenter.BasePresenter;
import com.huohu.mtrip.presenter.BaseView;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public interface InitLoaderContract {
    interface View extends BaseView<Presenter> {
        void gotoMainTabs();
        void gotoInfoSubmit();
        void gotoLogIn();
    }

    interface Presenter extends BasePresenter {
    }
}
