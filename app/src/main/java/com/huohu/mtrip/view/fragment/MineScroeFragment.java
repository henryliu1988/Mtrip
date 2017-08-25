package com.huohu.mtrip.view.fragment;

import com.huohu.mtrip.R;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MineScroeFragment extends PageImpBaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_mine_pet);
        backEnable(true);
        setTitle("我的积分");

    }

    @Override
    public void refreshView() {

    }
}
