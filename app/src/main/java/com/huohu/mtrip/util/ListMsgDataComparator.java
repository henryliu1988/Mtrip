package com.huohu.mtrip.util;

import com.huohu.mtrip.model.entity.MsgInfo;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class ListMsgDataComparator implements Comparator<MsgInfo> {

    private Object defaultValue;

    public ListMsgDataComparator( Object defaultValue) {
        super();
        this.defaultValue = defaultValue;
    }

    @Override
    public int compare(MsgInfo lhs, MsgInfo rhs) {
        if (defaultValue == null) {
            return 0;
        }
        if (defaultValue instanceof Integer) {
            int lh = Utils.toInteger(lhs.getAddtime());
            int rh = Utils.toInteger(rhs.getAddtime());

            if (lh == rh) {
                return 0;
            } else if (lh > rh) {
                return -1;
            } else {
                return 1;
            }
        } else if (defaultValue instanceof Long) {
            long lh = Utils.toLong(lhs.getAddtime());
            long rh = Utils.toLong(rhs.getAddtime());
            if (lh == rh) {
                return 0;
            } else if (lh > rh) {
                return -1;
            } else {
                return 1;
            }

        } else if (defaultValue instanceof String) {
            String lh = Utils.toString(lhs.getAddtime());
            String rh = Utils.toString(rhs.getAddtime());
            return 0;

        }
        return 0;
    }

}
