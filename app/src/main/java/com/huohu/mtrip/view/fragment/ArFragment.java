package com.huohu.mtrip.view.fragment;

import android.graphics.PixelFormat;

import com.huohu.mtrip.R;
import com.unity3d.player.UnityPlayer;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ArFragment extends TitleFragment {
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    @Override
    protected void initData() {
        
    }


    @Override
    protected void afterViewCreate() {
        fullScreenContent(true);
        getActivity().getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

        mUnityPlayer = new UnityPlayer(getActivity());
        setContentLayout(mUnityPlayer);
        mUnityPlayer.requestFocus();


    }

    @Override
    public void refreshView() {

    }
}
