package com.huohu.mtrip.view.fragment;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class IntroduceFragment extends PageImpBaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        backEnable(true);
        setTitle("乐园介绍");
        setRightImageTips(R.mipmap.title_msg);

    }

    @Override
    protected void onRightTipViewClick() {
        gotoFragment(FragKey.msg_cate_fragment);
    }

    @Override
    public void refreshView() {

    }
}
