package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class HomeFragment extends TitleFragment {
    @BindView(R.id.banner_home)
    ConvenientBanner bannerHome;
    @BindView(R.id.introduce_detail_tv)
    TextView introduceDetailTv;
    @BindView(R.id.introduce_image)
    ImageView introduceImage;
    @BindView(R.id.introduce_layout)
    LinearLayout introduceLayout;
    @BindView(R.id.map_detail_tv)
    TextView mapDetailTv;
    @BindView(R.id.map_image)
    ImageView mapImage;
    @BindView(R.id.map_layout)
    LinearLayout mapLayout;

    @Override
    protected void initData() {

    }


    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_home);
        ButterKnife.bind(this, getContentLayout());
        setRightImageTips(R.mipmap.title_msg);
        setTitleImage(R.mipmap.home_title);

        introduceDetailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.introduce_fragment,null,false);
            }
        });
        ImageUtils.getInstance().dispalyFromAssets("map.jpg",introduceImage);
       // ViewUtil.setRightCornerViewDrawbleBg(introduceTitleTv,"#FFD000",60);
        mapDetailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).gotoTab(2);
            }
        });
        ImageUtils.getInstance().dispalyFromAssets("map.jpg",mapImage);
      //  ViewUtil.setRightCornerViewDrawbleBg(mapTitleTv,"#439FFF",60);
      //  mapTitleTv.setText("乐园地图");
    }

    @Override
    protected void onRightTipViewClick() {
        ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.msg_cate_fragment,null,false);
    }

    @Override
    public void refreshView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
