package com.huohu.mtrip.view.adapter;

import android.content.Context;

import com.huohu.mtrip.R;
import com.huohu.mtrip.view.wighet.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class MsgItemAdapter extends ListViewAdapter {

    public MsgItemAdapter(Context context) {
        super(context, new ArrayList(), R.layout.adapter_msg_item);
    }
    public MsgItemAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {

    }
}
