package com.huohu.mtrip.model.key;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/25.
 */

public class SelectKey {

    public static final Map<Integer,String> PRIZR_STATUS_MAP = new HashMap<>();

  static   {
        PRIZR_STATUS_MAP.put(1,"未兑换");
        PRIZR_STATUS_MAP.put(2,"兑换成功");
        PRIZR_STATUS_MAP.put(3,"兑换失效");
    }
}
