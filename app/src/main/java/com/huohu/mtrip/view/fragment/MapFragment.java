package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class MapFragment extends TitleFragment {
    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.scaleAdd)
    ImageView scaleAdd;
    @BindView(R.id.scaleMul)
    ImageView scaleMul;

    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_map_layout);
        ButterKnife.bind(this, getContentLayout());
        setTitle("乐园地图");
        backEnable(false);
        setRightImageTips(R.mipmap.title_msg);
        needDiv(false);
        photoView.enable();
        photoView.enableRotate();
        photoView.setScaleType(ImageView.ScaleType.CENTER);
        ImageUtils.getInstance().dispalyFromAssets("map.jpg", photoView);
        ViewUtil.setLeftCornerViewDrawbleBg(scaleAdd,"#89000000",60);
        ViewUtil.setRightCornerViewDrawbleBg(scaleMul,"#89000000",60);
        scaleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = photoView.getScaleX();
                float y =photoView.getScaleY();
                x+=0.2;
                y+=0.2;
                photoView.setScaleX(x);
                photoView.setScaleY(y);
            }
        });
        scaleMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = photoView.getScaleX();
                float y =photoView.getScaleY();
                if (x > 0.2) {
                    x-=0.2;
                }
                if (y >0.2) {
                    y-=0.2;
                }
                photoView.setScaleX(x);
                photoView.setScaleY(y);
            }
        });

    }

    @Override
    protected void onRightTipViewClick() {
        ActivityUtils.transToFragPagerActivity(getActivity(), FragKey.msg_cate_fragment, null, false);
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
