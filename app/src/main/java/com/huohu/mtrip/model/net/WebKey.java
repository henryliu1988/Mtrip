package com.huohu.mtrip.model.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/8/1.
 */
public class WebKey {
    public static final String WEBKEY_NAMESPACE = "http://www.mengzubuluo.com/";
    public static final String WEBKEY_URL = "http://www.mengzubuluo.com/index.php?s=Portal/App/index";

    public static final String WEBKEY_BASE = "";
    public static final String WEBKEY_URL_RES = "http://59.110.24.36/yiduyi/index.php?s=App/Common/imgUpload";


    public static final String TYPE_KEY = "1";

    public static final int func_login = 1001;   //登录
    public static final int func_checkMobile = 1002;  //检查手机号是否被注册
    public static final int func_sendSms = 1003;  //发送验证码
    public static final int func_reset = 1004;  //重置密码
    public static final int func_register = 1005;  //注册
    public static final int func_banner = 1006;  //首页banner列表
    public static final int func_getNews = 1007;  //首页资讯
    public static final int func_getNewsList = 1008;  //资讯列表

    public static final int func_getRecommendZhuan = 1009;  //首页推荐专家
    public static final int func_getExpertsList = 1010;  //专家列表
    public static final int func_getExpert = 1011;  //专家详情
    public static final int func_getNewsById = 1012;  //资讯详情
    public static final int func_getCommentList = 1013;  //根据专家id获取留言列表
    public static final int func_searchExpertsList = 1014;  //搜索专家列表
    public static final int func_searchNewsList = 1015;  //搜索资讯列表
    public static final int func_collectExpert = 1016;  //收藏专家
    public static final int func_getPatientList = 1017;  //获取登录人的患者列表
    public static final int func_getPatientById = 1018;  //获取患者病例详情
    public static final int func_addPatient = 1019;  //添加患者
    public static final int func_updatePatient = 1020;  //修改患者
    public static final int func_patient = 1021;  //返回该用户是否已经提交认证信息
    public static final int func_collectNews = 1022;  //返回该用户是否已经提交认证信息
    public static final int func_getoffice = 1023;  //科室字典
    public static final int func_getbusiness = 1024;  //职称字典
    public static final int func_getHospital = 1025;  //医院字典
    public static final int func_getpro = 1026;  //医院字典
    public static final int func_getCity = 1027;  //医院字典
    public static final int func_getQu = 1028;  //医院字典
    public static final int func_getCollectExpert = 1031;  //医院字典
    public static final int func_getCollectNews = 1032;  //医院字典
    public static final int func_updateHuan = 1033;  //医院字典
    public static final int func_getOrders = 1034;  //全部订单
    public static final int func_getOrdersById = 1035;  //订单详情
    public static final int func_getOrdersMsg = 1036;  //订单详情
    public static final int func_updateOrdersMsg = 1037;  //订单详情
    public static final int func_cancelCollectExpert = 1038;  //订单详情
    public static final int func_cancelCollectNews = 1039;  //订单详情
    public static final int func_addComment = 1040;  //订单详情
    public static final int func_updatePassword = 1041;  //修改登录密码
    public static final int func_addPayPass = 1042;  //修改支付密码
    public static final int func_makeOrder = 1043;  //预约专家
    public static final int func_getNewCommentList = 1044;  //预约专家
    public static final int func_getComment = 1045;  //预约专家
    public static final int func_cancelOrder = 1046;  //预约专家
    public static final int func_updateMobile = 1047;  //修改手机号
    public static final int func_updateMember = 1048;  //
    public static final int func_getCancelReason = 1049;  //
    public static final int func_searchCollectExpert = 1050;  //
    public static final int func_searchCollectNews = 1051;  //
    public static final int func_getRecommend = 1052;  //
    public static final int func_updateCommentStatus = 1053;  //
    public static final int func_getSysMsg = 1054;  //
    public static final int func_addidear = 1055;  //
    public static final int func_getalipayresult = 1056;  //
    public static final int func_getsafecom  = 1057;  //
    public static final int func_backOrder = 1058;  //



    public static final Map<Integer, String> WEBKEY_FUNC_COMMON_MAP = new HashMap<Integer, String>();

    static {
        WEBKEY_FUNC_COMMON_MAP.put(func_login, "login");




    }




}
