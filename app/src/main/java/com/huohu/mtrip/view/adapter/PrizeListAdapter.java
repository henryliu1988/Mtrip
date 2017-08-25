package com.huohu.mtrip.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.SelectKey;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.ViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/25.
 */

public class PrizeListAdapter extends ListViewAdapter<Map<String,Object>> {


    public PrizeListAdapter(Context context, List<Map<String, Object>> datas) {
        super(context, datas, R.layout.prize_list_item);
    }

    @Override
    public void convert(ViewHolder holder,final  Map<String, Object> data) {
        String url = Utils.toString(data.get("url"));
        String prizeTime = Utils.toString("获奖时间：" + data.get("prize_time"));
        int status = Utils.toInteger(data.get("status"));
        ImageView image = (ImageView)holder.getView(R.id.image);
        TextView prizeTimeTv = (TextView)holder.getView(R.id.prize_get_time);
        TextView prizeduiTv = (TextView)holder.getView(R.id.prize_dui_time);
        TextView prizeoptTv = (TextView)holder.getView(R.id.dui_opt);
        TextView durationTv = (TextView)holder.getView(R.id.prize_duration);
        TextView prizeStatus  = (TextView)holder.getView(R.id.status_tv);
        ImageView moreImage = (ImageView)holder.getView(R.id.item_more);

        ViewUtil.setCornerViewDrawbleBg(image,"#999999","#FFFFFF");
        ImageUtils.getInstance().displayFromRemote(url,image);
        prizeTimeTv.setText(prizeTime);
        if (status == 0) {
            ViewUtil.setVisible(image);
            ViewUtil.setVisible(prizeTimeTv);
            ViewUtil.setVisible(durationTv);
            ViewUtil.setVisible(prizeoptTv);

            durationTv.setText(Utils.toString("有效期：" +data.get("duration")));
            ViewUtil.setCornerViewDrawbleBg(prizeoptTv,"#A0D92B","#A0D92B",15);
            prizeoptTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null ) {
                        listener.onDui(data);
                    }
                }
            });


            ViewUtil.setGone(prizeduiTv);
            ViewUtil.setGone(prizeStatus);

            ViewUtil.setInVisible(moreImage);
            holder.getConvertView().setOnClickListener(null);

        } else if (status == 1 || status == 2) {
            ViewUtil.setVisible(image);
            ViewUtil.setVisible(prizeTimeTv);
            ViewUtil.setVisible(prizeStatus);
            String statusStr = SelectKey.PRIZR_STATUS_MAP.get(status);
            prizeStatus.setText(statusStr);
            if (status == 1) {
                ViewUtil.setVisible(prizeduiTv);
                prizeduiTv.setText("兑换时间：" +Utils.toString(data.get("dui_time")));
                prizeStatus.setTextColor(Utils.getColorFormResource(R.color.title_bg));
            } else {
                ViewUtil.setGone(prizeduiTv);
                prizeStatus.setTextColor(Utils.getColorFormResource(R.color.red));
            }


            ViewUtil.setGone(durationTv);
            ViewUtil.setGone(prizeoptTv);
            ViewUtil.setVisible(moreImage);

            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null ) {
                        listener.onDetail(data);
                    }
                }
            });
        }
    }


    private Listener listener;
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface  Listener{
        void onDui(Map<String,Object> map);
        void onDetail(Map<String,Object> detail);
    }
}
