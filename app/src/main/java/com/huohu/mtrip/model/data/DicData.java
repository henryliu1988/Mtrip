package com.huohu.mtrip.model.data;

import com.huohu.mtrip.model.entity.NormalItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class DicData {


    public static DicData instance = new DicData();

    public static DicData getInstance() {
        return instance;
    }



    public void init() {
    }

    public List<NormalItem> getSex() {
        List<NormalItem> list = new ArrayList<>();
        list.add(new NormalItem("1", "男"));
        list.add(new NormalItem("2", "女"));
        return list;
    }

    public NormalItem getSexById(String id) {
        List<NormalItem> list = getSex();
        for (NormalItem item : list) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return new NormalItem(id, id);
    }

}

