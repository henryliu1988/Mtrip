package com.huohu.mtrip.view.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.MsgInfo;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class MsgItemAdapter extends ListViewAdapter<MsgInfo> {

    public MsgItemAdapter(Context context) {
        super(context, new ArrayList<MsgInfo>(), R.layout.adapter_msg_item);
    }
    public MsgItemAdapter(Context context, List<MsgInfo> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, MsgInfo info) {
        ( (TextView) holder.getView(R.id.title)).setText(info.getTitle());
        ( (TextView) holder.getView(R.id.time)).setText( DateUtil.getTimeDiffDayCurrent(Utils.toLong(info.getAddtime())));
        ( (TextView) holder.getView(R.id.content)).setText(Html.fromHtml(info.getContent()));
        ViewUtil.setTopCornerViewDrawbleBg(holder.getView(R.id.top_view),"#A0D92B",10);
        ViewUtil.setBottomCornerViewDrawbleBg(holder.getView(R.id.detail_tv),"#FFFFFF",10);
       // ViewUtil.setCornerViewDrawbleBg(holder.getView(R.id.view_layout),"#FFFFFF","#F4F4F4",1,60);

    }
}
