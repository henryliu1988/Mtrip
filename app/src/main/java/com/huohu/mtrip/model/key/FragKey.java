package com.huohu.mtrip.model.key;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/5 0005.
 */
public class FragKey {


    public static final int msg_cate_fragment = 0;
    public static final int introduce_fragment = 1;
    public static final int msg_list_fragment = 2;
    public static final int mine_pet_fragment = 3;
    public static final int mine_score_fragment = 4;
    public static final int mine_info_fragment = 5;
    public static final int mine_name_fragment = 6;
    public static final int mine_sex_fragment = 7;
    public static final int mine_birth_fragment = 8;
    public static final int mine_prize_fragment = 9;
    public static final int prize_detail_fragment = 10;


    public static final Map<Integer,String> FragMap = new HashMap<>();
    static
    {
        FragMap.put(msg_cate_fragment,"msg_cate_fragment");
        FragMap.put(introduce_fragment,"introduce_fragment");
        FragMap.put(msg_list_fragment,"msg_list_fragment");
        FragMap.put(mine_pet_fragment,"mine_pet_fragment");
        FragMap.put(mine_score_fragment,"mine_score_fragment");
        FragMap.put(mine_info_fragment,"mine_info_fragment");

        FragMap.put(mine_name_fragment,"mine_name_fragment");

        FragMap.put(mine_sex_fragment,"mine_sex_fragment");

        FragMap.put(mine_birth_fragment,"mine_birth_fragment");
        FragMap.put(mine_prize_fragment,"mine_prize_fragment");
        FragMap.put(prize_detail_fragment,"prize_detail_fragment");

    }
}
