package com.huohu.mtrip.view.fragment;

import com.huohu.mtrip.model.key.FragKey;

/**
 * Created by admin on 2016/8/9.
 */
public class PagerFragmentFactory
{
    public static PageImpBaseFragment createFragment(int key)
    {
        switch (key){
            case FragKey.msg_cate_fragment:
                return new MsgCateFragment();
            case FragKey.introduce_fragment:
                return new IntroduceFragment();
            case FragKey.msg_list_fragment:
                return new MsgListFragment();
        }
      return null;
    }
}
