package com.huohu.mtrip.view.fragment;

import com.huohu.mtrip.R;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ArFragment extends TitleFragment {
    @Override
    protected void initData() {
        
    }


    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_ar);
        fullScreenContent(true);
    }

    @Override
    public void refreshView() {

    }
}
