package com.huohu.mtrip.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.MapParams;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.activity.MainActivity;
import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

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
        ImageUtils.getInstance().dispalyFromAssets("intro.jpg",introduceImage);
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
        getBannerImage();
    }

    private void getBannerImage() {

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(new MapParams().addParam("path","banner1.jpg").create() );
        list.add(new MapParams().addParam("path","banner2.jpg").create() );
        list.add(new MapParams().addParam("path","banner3.jpg").create() );
        Observable.just(list).subscribe(new BaseSubscriber<List<Map<String, Object>>>() {
            @Override
            public void onNext(List<Map<String, Object>> images) {
                bannerHome.setPages(
                        new CBViewHolderCreator<NetworkImageHolderView>() {
                            @Override
                            public NetworkImageHolderView createHolder() {
                                return new NetworkImageHolderView();
                            }
                        }, images).startTurning(3000)
                        .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});

            }
        });
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

    class NetworkImageHolderView implements Holder<Map<String, Object>> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Map<String, Object> data) {
            ImageUtils.getInstance().dispalyFromAssets(Utils.toString(data.get("path")), imageView);
        }
    }
}
