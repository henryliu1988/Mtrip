package com.huohu.mtrip.model.data;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;


import com.alibaba.fastjson.JSONObject;
import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.MD5;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/12/18 0018.
 */
public class UserData {
    private static UserData userData = new UserData();
    private TokenInfo mToken;

    private WebResponse mIdentifyWeb;
    public UserData() {
    }

    public static UserData getInstance() {
        if (userData == null) {
            userData = new UserData();
        }
        return userData;
    }

    public Observable<Map<String,Object>> tryLoginManager(final String phoneNum, final String password) {
        HashMap<String, Object> map = new HashMap<>();
        String paswordMd5 = MD5.GetMD5Code(password);
        map.put("mobile", phoneNum);
        map.put("password", paswordMd5);


      return  WebCall.getInstance().call(WebKey.func_login, map).map(new Func1<WebResponse, Map<String, Object>>() {
          @Override
          public Map<String, Object> call(WebResponse webResponse) {
              boolean status = WebUtils.getWebStatus(webResponse);
              status = true;
              Map<String, Object> info = new HashMap<String, Object>();
              info.put("status", status);
              if (status) {
                  String data = webResponse.getData();
                  saveLogInfo(phoneNum, password);
                  saveTokenInfo(data);
              } else {
                  String msg = WebUtils.getWebMsg(webResponse);
                  info.put("msg", msg);
              }
              return info;
          }
      });



    }

    private void saveLogInfo(String phoneNum, String password) {
        SPUtils.put("login_phoneNum", phoneNum);
        SPUtils.put("login_password", password);
        SPUtils.put("login_auto", true);
    }

    private void saveTokenInfo(Object tokenOb) {
        Map<String, Object> token = Utils.parseObjectToMapString(tokenOb);
        token.put("id","11");
        token.put("nickName","游客123");
        if (token != null && token.size() > 0) {
            TokenInfo info = new TokenInfo();
            info.setId(Utils.toString(token.get("id")));
            info.setNickName(Utils.toString(token.get("nickName")));
            info.setMobile(Utils.toString(token.get("mobile")));
            info.setPassoword(Utils.toString(token.get("password")));
            Map<String, Object> headImg = Utils.parseObjectToMapString(token.get("head_img"));
            info.setPhotoId(Utils.toString(headImg.get("id")));
            info.setPhotoUrl(Utils.toString(headImg.get("path")));
            info.setSex(Utils.toString(token.get("sex")));

            setToken(info);
            AppDataManager.getInstance().initLoginSucData();
        }
    }

    public boolean needLogin(int fragkey, Activity activity) {
        List<Integer> noUserFragList = Arrays.asList(FragKey.NO_NEED_USER_FRAG);
        if (!noUserFragList.contains(fragkey) && !UserData.getInstance().isLogin())
        {
            ActivityUtils.showLogin(activity, false);
            MToast.showToast("请先登录");
            return true;
        }
        return false;

    }


    public  String getUserId() {
        if (mToken == null) {
            mToken = new TokenInfo();
        }
        return mToken.getId();
    }
    public void setToken(TokenInfo token) {
        mToken = token;
    }

    public TokenInfo getToken() {
        if (mToken == null) {
            return new TokenInfo();
        }
        return mToken;
    }


    public void logOut() {
        SPUtils.put("login_auto", false);
        mToken = null;
        mIdentifyWeb = null;
    }
    public boolean isLogin(){
        if (mToken == null || TextUtils.isEmpty(mToken.getId())) {
            return false;
        }
        return true;
    }



    public void loadUserData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", UserData.getInstance().getToken().getId());
        WebCall.getInstance().call(WebKey.func_patient, params).subscribe(new BaseSubscriber<WebResponse>() {
            @Override
            public void onNext(WebResponse webResponse) {
                mIdentifyWeb = webResponse;
                RefreshManager.getInstance().refreshData(RefreshKey.IDENTIFY_MSG_UPDATE);

            }
        });
    }

    public Observable<Integer> getIdentifyState() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", getToken().getId());
        return WebCall.getInstance().callCache(WebKey.func_patient, params,mIdentifyWeb).map(new Func1<WebResponse, Integer>() {
            @Override
            public Integer call(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(mIdentifyWeb);
                if (status) {
                    Map<String, Object> identifyMsg = Utils.parseObjectToMapString(mIdentifyWeb.getData());
                    int msgInt = Utils.toInteger(identifyMsg.get("msg"));
                    return msgInt;
                }
                return -1;
            }
        });
    }
    public Observable<Map<String,Object>> getIdentifyMsg() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", getToken().getId());
        return WebCall.getInstance().callCache(WebKey.func_patient, params,mIdentifyWeb).map(new Func1<WebResponse, Map<String,Object>>() {
            @Override
            public Map<String,Object> call(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(mIdentifyWeb);
                if (status) {
                    Map<String, Object> identifyMsg = Utils.parseObjectToMapString(mIdentifyWeb.getData());
                    return identifyMsg;
                }
                return new HashMap<String, Object>();
            }
        });
    }

}
