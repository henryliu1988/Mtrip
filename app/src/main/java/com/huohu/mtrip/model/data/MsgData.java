package com.huohu.mtrip.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

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

    public Observable<List<Map<String,Object>>> getUnreadMsgList() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> item = new HashMap<>();
        item.put("content","ddddd");
        item.put("addtime","07:46");
        list.add(item);
        return Observable.just(list);
    }



    public Observable<List<Map<String,Object>>> getMsgListByType(int type) {
        if (type == NEW_MSG) {
            return getUnreadMsgList();
        } else {
            return getAllMsgList();
        }
    }
    public Observable<List<Map<String,Object>>> getAllMsgList() {
        List<Map<String,Object>> list = new ArrayList<>();
        return Observable.just(list);
    }


}
