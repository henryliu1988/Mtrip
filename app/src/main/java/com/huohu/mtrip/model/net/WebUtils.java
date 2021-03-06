package com.huohu.mtrip.model.net;



import com.huohu.mtrip.util.Utils;

import java.util.Map;

/**
 * Created by admin on 2016/12/5.
 */
public class WebUtils {


    public static boolean getWebStatus(WebResponse response) {
        String returnData = response.getReturnData();
        Map<String, Object> map = Utils.parseObjectToMapString(returnData);
        return Utils.toBoolean(map.get("status"));
    }

    public static String getWebMsg(WebResponse response) {
        String returnData = response.getReturnData();
        Map<String, Object> map = Utils.parseObjectToMapString(returnData);
        return Utils.toString(map.get("msg"));

    }
}
