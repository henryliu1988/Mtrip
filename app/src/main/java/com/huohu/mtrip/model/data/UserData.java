package com.huohu.mtrip.model.data;

import android.app.Activity;
import android.text.TextUtils;

import com.huohu.mtrip.model.cache.SPUtils;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
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
        String paswordMd5 = "###" + MD5.GetMD5Code(MD5.GetMD5Code("7n6b8P4nMMyyC7N6lm" + password));
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
                  mToken = Utils.parseObjectToEntry(webResponse.getData(),TokenInfo.class);
                  AppDataManager.getInstance().initLoginSucData();
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
    }
    public boolean isLogin(){
        if (mToken == null || TextUtils.isEmpty(mToken.getId())) {
            return false;
        }
        return true;
    }







}
