package com.huohu.mtrip.view.adapter;

import android.content.Context;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class MsgItemAdapter<T> extends ListViewAdapter {

    public MsgItemAdapter(Context context) {
        super(context, new ArrayList<T>(), R.layout.adapter_msg_item);
    }
    public MsgItemAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        ( (TextView) holder.getView(R.id.title)).setText("系统消息");
        ( (TextView) holder.getView(R.id.time)).setText("2017-08-05 09:28");
        ( (TextView) holder.getView(R.id.content)).setText("萌族部落新版本发布");
        ViewUtil.setCornerViewDrawbleBg(holder.getView(R.id.view_layout),"#FFFFFF",100);

    }
}
