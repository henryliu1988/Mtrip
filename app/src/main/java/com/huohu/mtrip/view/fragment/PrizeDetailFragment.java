package com.huohu.mtrip.view.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.PrizeInfo;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;

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


    private PrizeInfo info = new PrizeInfo();
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_prize_detail);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        info = Utils.parseObjectToEntry(mArgInfo,PrizeInfo.class);
        if (info== null) {
            return;
        }

        String url = info.getImg();
        ImageUtils.getInstance().displayFromRemote(url,image);

        ViewUtil.setCornerViewDrawbleBg(image,"#999999","#FFFFFF");
        int state = Utils.toInteger(info.getWin_status());

        String addTime = DateUtil.getFullTimeDiffDayCurrent(Utils.toLong(info.getAdd_time()));
        String duration = DateUtil.getFullTimeDiffDayCurrent(Utils.toLong(info.getAdd_time()));
        String duiTime = DateUtil.getFullTimeDiffDayCurrent(Utils.toLong(info.getDui_time()));
        prizeGetTime.setText("获奖时间：" + addTime);
        prizeDuration.setText("有效期：" +duration);
        if (state == 1) {
            setTitle("兑换");
            ViewUtil.setInVisible(statusTv);
            ViewUtil.setInVisible(prizeDuiTime);

        } else{
            setTitle("兑换详情");
            ViewUtil.setVisible(statusTv);
            if (state == 2) {
                ViewUtil.setVisible(prizeDuiTime);
                statusTv.setTextColor(Utils.getColorFormResource(R.color.title_bg));
                prizeDuiTime.setText("兑换时间：" +duiTime);
                statusTv.setText("兑换成功");
            } else {
                ViewUtil.setInVisible(prizeDuiTime);
                statusTv.setTextColor(Utils.getColorFormResource(R.color.red));
                statusTv.setText("有效期超时，兑换失败");
            }
        }

        String code = info.getMarks();
        ViewUtil.setCornerViewDrawbleBg(codeTv,"#FFFFFF","#999999",1,8);
        codeTv.setText(code);
    }

    @Override
    public void refreshView() {

    }


}
