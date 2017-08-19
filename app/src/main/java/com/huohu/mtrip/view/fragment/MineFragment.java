package com.huohu.mtrip.view.fragment;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.util.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class MineFragment extends TitleFragment {
    @Override
    protected void initData() {
        
    }


    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_mine);
        ButterKnife.bind(this,getContentLayout());
        backEnable(false);
        setLeftText("设置");
        needDiv(false);
        setRightImageTips(R.mipmap.title_msg);
    }

    @Override
    protected void onRightTipViewClick() {
        ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.msg_cate_fragment,null,false);
    }

    @Override
    public void refreshView() {

    }
}
