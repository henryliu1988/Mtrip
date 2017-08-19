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


    public static final Map<Integer,String> FragMap = new HashMap<>();
    static
    {
        FragMap.put(msg_cate_fragment,"msg_cate_fragment");
        FragMap.put(introduce_fragment,"introduce_fragment");
        FragMap.put(msg_list_fragment,"msg_list_fragment");

    }
}
