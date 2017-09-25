package com.huohu.mtrip.model.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/8/1.
 */
public class WebKey {
    public static final String WEBKEY_NAMESPACE = "http://www.mengzubuluo.com/";
    public static final String WEBKEY_URL = "http://www.mengzubuluo.com/index.php?s=Portal/App/index";

    public static final String WEBKEY_BASE = "http://www.mengzubuluo.com/";
    public static final String WEBKEY_URL_RES = "http://www.mengzubuluo.com/index.php?s=Portal/App/imgUpload";


    public static final String TYPE_KEY = "1";

    public static final int func_login = 1001;   //登录
    public static final int func_checkMobile = 1002;  //检查手机号是否被注册
    public static final int func_sendSms = 1003;  //发送验证码
    public static final int func_reset = 1004;  //重置密码
    public static final int func_register = 1005;  //注册
    public static final int func_getbanner = 1006;  //首页banner列表
    public static final int func_getshow = 1007;  //介绍
    public static final int func_getallmsglist = 1008;  //介绍

    public static final int func_getmsglist = 1009;  //介绍
    public static final int func_getwingoods = 1010;  //介绍

    public static final int func_updatemember = 1011;  //介绍

    public static final int func_addreadlog = 1012;  //介绍


    public static final Map<Integer, String> WEBKEY_FUNC_COMMON_MAP = new HashMap<Integer, String>();

    static {
        WEBKEY_FUNC_COMMON_MAP.put(func_register, "register");
        WEBKEY_FUNC_COMMON_MAP.put(func_login, "login");
        WEBKEY_FUNC_COMMON_MAP.put(func_getbanner, "getbanner");
        WEBKEY_FUNC_COMMON_MAP.put(func_getshow, "getshow");
        WEBKEY_FUNC_COMMON_MAP.put(func_getallmsglist, "getallmsglist");
        WEBKEY_FUNC_COMMON_MAP.put(func_getmsglist, "getmsglist");
        WEBKEY_FUNC_COMMON_MAP.put(func_getwingoods, "getwingoods");
        WEBKEY_FUNC_COMMON_MAP.put(func_updatemember, "updatemember");
        WEBKEY_FUNC_COMMON_MAP.put(func_addreadlog, "addreadlog");
        WEBKEY_FUNC_COMMON_MAP.put(func_sendSms, "sendmmsg");
        WEBKEY_FUNC_COMMON_MAP.put(func_reset, "repass");

    }




}
