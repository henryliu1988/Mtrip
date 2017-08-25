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
            case FragKey.mine_pet_fragment:
                return new MinePetFragment();
            case FragKey.mine_score_fragment:
                return new MineScroeFragment();
            case FragKey.mine_info_fragment:
                return new MineInfoFragment();
            case FragKey.mine_birth_fragment:
                return new MineBirthChangeFragment();
            case FragKey.mine_name_fragment:
                return new MineNameChangeFragment();
            case FragKey.mine_sex_fragment:
                return new MineSexChangeFragment();
            case FragKey.mine_prize_fragment:
                return new MinePrizeFragment();
            case FragKey.prize_detail_fragment:
                return new PrizeDetailFragment();

        }
      return null;
    }
}
