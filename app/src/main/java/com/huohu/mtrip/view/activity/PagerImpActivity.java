package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.key.IntentKey;
import com.huohu.mtrip.presenter.ActivityResultView;
import com.huohu.mtrip.presenter.contract.PageImpContract;
import com.huohu.mtrip.presenter.presenter.PageImpPresenter;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.view.fragment.FragmentUtils;
import com.huohu.mtrip.view.fragment.PagerFragmentFactory;
import com.huohu.mtrip.view.fragment.StatedFragment;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/8/9.
 */
public class PagerImpActivity extends TitleActivity implements PageImpContract.View {


    private PageImpContract.Presenter mPresenter;



    @Override
    protected void initView() {

        Bundle bundle = getIntent().getBundleExtra(IntentKey.INTENT_BUNDLE);
        int key = bundle.getInt(IntentKey.FRAG_KEY);
        if (!FragKey.FragMap.containsKey(key)) {
            finish();
            return;
        }

        new PageImpPresenter(this);
        setContentLayout(R.layout.activity_pager_imp);
        ButterKnife.bind(this);
        fullScreenContent(true);
        StatedFragment fragment = PagerFragmentFactory.createFragment(key);
        if (fragment == null) {
            return;
        }
        String fragInfo = bundle.getString(IntentKey.FRAG_INFO);
        if (!TextUtils.isEmpty(fragInfo)) {
            Bundle fragBundle = new Bundle();
            fragBundle.putString(IntentKey.FRAG_INFO, fragInfo);
            fragment.setArguments(fragBundle);
        }
        FragmentUtils.addNewFragment(this, fragment, FragKey.FragMap.get(key), R.id.fragment_content);

    }


    public int getFragmentViewId() {
        return R.id.fragment_content;
    }

    @Override
    public void onBackPressed() {
        FragmentUtils.back(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setPresenter(PageImpContract.Presenter presenter) {
        this.mPresenter = presenter;
    }



    @Override
    public Context getContext() {
        return null;
    }


    private List<ActivityResultView> mActivityResultViews = new ArrayList<>();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (ActivityResultView view : mActivityResultViews) {
            if (view != null) {
                view.onPermissionResult(requestCode, permissions, grantResults);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ActivityResultView view : mActivityResultViews) {
            if (view != null) {
                view.onActivityResult1(requestCode, resultCode, data);
            }
        }
      //  UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void addOnActivityResultView(ActivityResultView view) {
        if (!mActivityResultViews.contains(view)) {
            mActivityResultViews.add(view);
        }
    }

    public void removeOnActivityResultView(ActivityResultView view) {
        for (int i = 0; i < mActivityResultViews.size(); i++) {
            ActivityResultView v = mActivityResultViews.get(i);
            if (v == view) {
                mActivityResultViews.remove(i);
                break;
            }
        }
    }
}
