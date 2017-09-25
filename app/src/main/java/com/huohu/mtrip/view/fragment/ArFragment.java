package com.huohu.mtrip.view.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.huohu.mtrip.presenter.IOnFocusListenable;
import com.unity3d.player.UnityPlayer;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ArFragment extends TitleFragment implements IOnFocusListenable {
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
        if (mUnityPlayer == null) {
            mUnityPlayer = new UnityPlayer(getActivity());
            int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
            boolean trueColor8888 = false;
            mUnityPlayer.init(glesMode, trueColor8888);
            mUnityPlayer.requestFocus();
            View playerView = mUnityPlayer.getView();
            setContentLayout(playerView);
        }
    }

    @Override
    public void refreshView() {

    }


    @Override
       public void onResume() {
           super.onResume();
           mUnityPlayer.resume();

       }

       @Override
       public void onDestroy() {
           mUnityPlayer.quit();
           super.onDestroy();
       }

       @Override
       public void onPause() {
           super.onPause();
           mUnityPlayer.pause();
       }

       @Override
       public void onConfigurationChanged(Configuration newConfig) {
           super.onConfigurationChanged(newConfig);
           mUnityPlayer.configurationChanged(newConfig);
       }

       public void onWindowFocusChanged(boolean hasFocus) {
           mUnityPlayer.windowFocusChanged(hasFocus);
       }


}
