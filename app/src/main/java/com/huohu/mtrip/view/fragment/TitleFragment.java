package com.huohu.mtrip.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.ImageTipsView;


/**
 * Created by Administrator on 2017/7/17.
 */

public abstract class TitleFragment extends StatedFragment {
    protected TextView mCenterTv;
    protected ImageView mTitleBackIm;
    protected TextView mRightTv;
    protected FrameLayout mContentLayout;
    protected RelativeLayout mTitleLayout;
    protected ImageTipsView mTipView;
    protected View mDivView;
    protected ImageView mCenterImage;
    protected  TextView mLeftTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(R.layout.fragment_title, container, false);
            mCenterTv = (TextView)mRootView.findViewById(R.id.title_name);
            mTitleBackIm = (ImageView)mRootView.findViewById(R.id.title_back);
            mRightTv = (TextView)mRootView.findViewById(R.id.right_tv);
            mTitleLayout = (RelativeLayout)mRootView.findViewById(R.id.title_layout);
            mContentLayout = (FrameLayout)mRootView.findViewById(R.id.content_layout);
            mTipView = (ImageTipsView)mRootView.findViewById(R.id.right_tips);
            mDivView = mRootView.findViewById(R.id.div_line);
            mCenterImage =(ImageView) mRootView.findViewById(R.id.title_image);
            mLeftTv = (TextView)mRootView.findViewById(R.id.left_tv);
            ViewUtil.setGone(mRightTv);
            ViewUtil.setGone(mTitleBackIm);
            ViewUtil.setGone(mTipView);
            ViewUtil.setGone(mCenterTv);
            ViewUtil.setGone(mCenterImage);
            ViewUtil.setGone(mLeftTv);
        }

        afterViewCreate();
        initData();
        return mRootView;
    }

    protected  void needDiv(boolean need) {
        if(need) {
            ViewUtil.setVisible(mDivView);
        } else {
            ViewUtil.setGone(mDivView);
        }

    }
    protected FrameLayout getContentLayout() {
        return mContentLayout;
    }
    protected  void fullScreenContent(boolean full) {
        if (full) {
            ViewUtil.setGone(mTitleLayout);
        } else {
            ViewUtil.setVisible(mTitleLayout);
        }
    }
    protected void backEnable(boolean enable) {

        if (enable) {
            ViewUtil.setVisible(mTitleBackIm);
            mTitleBackIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLeftBackClick();
                }
            });
        } else {
            ViewUtil.setGone(mTitleBackIm);
        }
    }
    protected  void setLeftText(String text) {
        ViewUtil.setVisible(mLeftTv);
        mLeftTv.setText(text);
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftTvClick();
            }
        });

    }
    protected void onLeftTvClick() {

    }
    protected void setRightText(String text) {
        ViewUtil.setVisible(mRightTv);
        mRightTv.setText(text);
        mRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightClick();
            }
        });
    }


    protected void onRightClick() {

    }

    protected  void setTitle(String title) {
        ViewUtil.setVisible(mCenterTv);
        mCenterTv.setText(title);
    }

    protected  void setTitleImage(int recId) {
        ViewUtil.setVisible(mCenterImage);
        mCenterImage.setImageResource(recId);
    }

    protected void setRightImageTips(int recId) {
        ViewUtil.setVisible(mTipView);
        mTipView.setImageResource(recId);
        mTipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightTipViewClick();
            }
        });
    }

    protected  void onRightTipViewClick() {

    }
    protected  void setTipsCount(String count) {
        ViewUtil.setVisible(mTipView);
        mTipView.setTipText(count);
    }


    protected void onLeftBackClick() {
        backFragment();
    }
    protected void backFragment() {
        Activity activity = getActivity();
        if (!(activity instanceof FragmentActivity))
        {
            return;
        }
        FragmentUtils.back((FragmentActivity) activity);

    }
    protected void setContentLayout(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }
    protected  void setContentLayout(int layoutId) {
        mContentLayout.removeAllViews();
        LayoutInflater.from(getContext()).inflate(layoutId,mContentLayout);
    }

}
