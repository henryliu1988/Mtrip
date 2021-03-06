package com.huohu.mtrip.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huohu.mtrip.R;
import com.huohu.mtrip.util.ViewUtil;


/**
 * Created by Administrator on 2017/8/3.
 */

public abstract class TitleActivity extends BaseActivity {
    protected TextView mCenterTv;
    protected ImageView mTitleBackIm;
    protected TextView mRightTv;
    protected FrameLayout mContentLayout;
    protected RelativeLayout mTitleLayout;

    protected View mDivView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        int titleColor = getResources().getColor(R.color.title_bg);
        StatusBarCompat.setFitsSystemWindows(getWindow(),true);
        StatusBarCompat.setStatusBarColor(this,titleColor );

        mCenterTv = (TextView)this.findViewById(R.id.title_name);
        mTitleBackIm = (ImageView)this.findViewById(R.id.title_back);
        mRightTv = (TextView)this.findViewById(R.id.right_tv);
        mTitleLayout = (RelativeLayout)this.findViewById(R.id.title_layout);
        mContentLayout = (FrameLayout)this.findViewById(R.id.content_layout);
        mDivView = this.findViewById(R.id.div);
        ViewUtil.setGone(mRightTv);
        ViewUtil.setGone(mTitleBackIm);
        initView();
    }

    protected  abstract void initView();
    protected View getContentLayout() {
        return mContentLayout;
    }


    protected  void fullScreenContent(boolean full) {
        if (full) {
            ViewUtil.setGone(mDivView);
            ViewUtil.setGone(mTitleLayout);
        } else {
            ViewUtil.setVisible(mTitleLayout);
            ViewUtil.setVisible(mDivView);
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
        mCenterTv.setText(title);

    }

    protected void onLeftBackClick() {
        finish();
    }
    protected void setContentLayout(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }
    protected  void setContentLayout(int layoutId) {
        mContentLayout.removeAllViews();
        LayoutInflater.from(this).inflate(layoutId,mContentLayout);
    }

}
