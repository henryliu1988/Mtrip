package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.huohu.mtrip.view.activity.MainActivity;
import com.unity3d.player.UnityPlayer;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ArFragment extends TitleFragment  {
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    private static final String ARG_SECTION_NUMBER = "section_number";

       public static ArFragment newInstance(int sectionNumber) {
           ArFragment fragment = new ArFragment();
           Bundle args = new Bundle();
           args.putInt(ARG_SECTION_NUMBER, sectionNumber);
           fragment.setArguments(args);
           return fragment;
       }


    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        fullScreenContent(true);
        MainActivity activity =(MainActivity) getActivity();
        View view = activity.getUnitPlayView();
        setContentLayout(view);

    }

    @Override
    public void refreshView() {

    }





}
