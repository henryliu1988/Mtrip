package com.huohu.mtrip.model.data;

import com.huohu.mtrip.model.entity.MsgInfo;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.util.Utils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class MsgData {

    private static MsgData instance;


    public  static final int NEW_MSG = 0;
    public  static final int ALL_MSG = 1;


    public MsgData() {
    }

    public static MsgData getInstance() {
        if (instance == null) {
            instance = new MsgData();
        }
        return instance;
    }

    public void loadData() {
    }

    public Observable<List<MsgInfo>> getUnreadMsgList() {
        HashMap<String,Object> p = new HashMap();
        p.put("memberid",UserData.getInstance().getUserId());
        return WebCall.getInstance().call(WebKey.func_getmsglist,p).map(new Func1<WebResponse, List<MsgInfo>>() {
            @Override
            public List<MsgInfo> call(WebResponse webResponse) {
                return Utils.parseObjectToListEntry(webResponse.getData(),MsgInfo.class);
            }
        });

    }



    public Observable<List<MsgInfo>> getMsgListByType(int type) {
        if (type == NEW_MSG) {
            return getUnreadMsgList();
        } else {
            return getAllMsgList();
        }
    }
    public Observable<List<MsgInfo>> getAllMsgList() {
        HashMap<String,Object> p = new HashMap();
        p.put("memberid",UserData.getInstance().getUserId());
        return WebCall.getInstance().call(WebKey.func_getallmsglist,p).map(new Func1<WebResponse, List<MsgInfo>>() {
            @Override
            public List<MsgInfo> call(WebResponse webResponse) {
                return Utils.parseObjectToListEntry(webResponse.getData(),MsgInfo.class);
            }
        });
    }


}
