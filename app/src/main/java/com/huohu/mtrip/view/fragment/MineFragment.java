package com.huohu.mtrip.view.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.model.refresh.RefreshWithKey;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class MineFragment extends TitleFragment {
    @BindView(R.id.photo_image)
    ImageView photoImage;
    @BindView(R.id.nickname_tv)
    TextView nicknameTv;
    @BindView(R.id.role_tv)
    TextView roleTv;
    @BindView(R.id.mine_info_img)
    ImageView mineInfoImg;
    @BindView(R.id.mine_info_layout)
    RelativeLayout mineInfoLayout;
    @BindView(R.id.mine_score_img)
    ImageView mineScoreImg;
    @BindView(R.id.mine_score_layout)
    RelativeLayout mineScoreLayout;
    @BindView(R.id.pet_img)
    ImageView petImg;
    @BindView(R.id.pet_layout)
    RelativeLayout petLayout;
    @BindView(R.id.mine_prize_img)
    ImageView minePrizeImg;
    @BindView(R.id.mine_prize_layout)
    RelativeLayout minePrizeLayout;

    @Override
    protected void initData() {
        TokenInfo info = UserData.getInstance().getToken();

        if (!TextUtils.isEmpty(info.getAvatar()) && info.getAvatar().length() >2) {
            ImageUtils.getInstance().displayFromRemoteOver(info.getAvatar(),photoImage);
        } else {
            photoImage.setImageResource(R.mipmap.photo);
        }
        nicknameTv.setText(info.getUser_nicename());
        roleTv.setText("普通游客");
        ViewUtil.setLeftCornerViewDrawbleBg(roleTv,"#B6DC65",11);

    }


    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_mine);
        ButterKnife.bind(this, getContentLayout());
        backEnable(false);
        setLeftText("设置");
        needDiv(false);
        setRightImageTips(R.mipmap.title_msg);
        RefreshManager.getInstance().addNewListener(RefreshKey.USER_INFO_UPDATE, new RefreshWithKey() {
            @Override
            public void onRefreshWithKey(int key) {
                if (key == RefreshKey.USER_INFO_UPDATE) {
                    refreshView();
                }
            }
        });

    }

    @Override
    protected void onLeftTvClick() {
        ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.set_fragment, null, false);
    }

    @Override
    protected void onRightTipViewClick() {
        ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.msg_cate_fragment, null, false);
    }

    @Override
    public void refreshView() {
        initData();
    }

    @OnClick({R.id.mine_info_layout, R.id.mine_score_layout, R.id.pet_layout, R.id.mine_prize_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_info_layout:
                ActivityUtils.transToFragPagerActivity(getActivity(),FragKey.mine_info_fragment,null,false);
                break;
            case R.id.mine_score_layout:
                ActivityUtils.transToFragPagerActivity(getActivity(),FragKey.mine_score_fragment,null,false);
                break;
            case R.id.pet_layout:
                ActivityUtils.transToFragPagerActivity(getActivity(),FragKey.mine_pet_fragment,null,false);
                break;
            case R.id.mine_prize_layout:
                ActivityUtils.transToFragPagerActivity(getActivity(),FragKey.mine_prize_fragment,null,false);
                break;
        }
    }
}
