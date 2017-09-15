package com.huohu.mtrip.view.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.SelectKey;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25.
 */

public class PrizeDetailFragment extends PageImpBaseFragment {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.status_tv)
    TextView statusTv;
    @BindView(R.id.prize_get_time)
    TextView prizeGetTime;
    @BindView(R.id.prize_duration)
    TextView prizeDuration;
    @BindView(R.id.prize_dui_time)
    TextView prizeDuiTime;
    @BindView(R.id.code)
    TextView codeTv;


    private Map<String,Object> mItemData = new HashMap<>();
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_prize_detail);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        mItemData = Utils.parseObjectToMapString(mArgInfo);
        if (Utils.isEmptyObject(mItemData)) {
            return;
        }

        String url = Utils.toString(mItemData.get("url"));
        ImageUtils.getInstance().dispalyFromAssets(url,image);

        ViewUtil.setCornerViewDrawbleBg(image,"#999999","#FFFFFF");
        int state = Utils.toInteger(mItemData.get("status"));
        prizeGetTime.setText("获奖时间：" + Utils.toString(mItemData.get("prize_time")));
        prizeDuration.setText("有效期：" + Utils.toString(mItemData.get("duration")));
        if (state == 0) {
            setTitle("兑换");
            ViewUtil.setInVisible(statusTv);
            ViewUtil.setInVisible(prizeDuiTime);

        } else{
            setTitle("兑换详情");
            ViewUtil.setVisible(statusTv);
            if (state == 1) {
                ViewUtil.setVisible(prizeDuiTime);
                statusTv.setTextColor(Utils.getColorFormResource(R.color.title_bg));
                prizeDuiTime.setText(Utils.toString("兑换时间：" +mItemData.get("dui_time")));
                statusTv.setText("兑换成功");
            } else {
                ViewUtil.setInVisible(prizeDuiTime);
                statusTv.setTextColor(Utils.getColorFormResource(R.color.red));
                statusTv.setText("有效期超时，兑换失败");

            }
        }

        String code = Utils.toString(mItemData.get("code"));
        ViewUtil.setCornerViewDrawbleBg(codeTv,"#FFFFFF","#999999",1,8);
        codeTv.setText(code);
    }

    @Override
    public void refreshView() {

    }


}
