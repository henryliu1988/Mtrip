package com.huohu.mtrip.model.data;

import android.content.Context;
import android.text.TextUtils;


import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.util.MD5;
import com.huohu.mtrip.util.Utils;

import java.util.HashMap;
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
        if (token != null && token.size() > 0) {
            TokenInfo info = new TokenInfo();
            Map<String, Object> headImg = Utils.parseObjectToMapString(token.get("head_img"));
            setToken(info);
            AppDataManager.getInstance().initLoginSucData();
        }
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
