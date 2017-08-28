package com.huohu.mtrip.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.ScreenUtils;
import com.huohu.mtrip.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/28.
 */

public class SetFragment extends PageImpBaseFragment {
    @BindView(R.id.cache_value)
    TextView cacheValue;
    @BindView(R.id.cache_layout)
    RelativeLayout cacheLayout;
    @BindView(R.id.logout_layout)
    RelativeLayout logoutLayout;

    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_set);
        ButterKnife.bind(this, getContentLayout());
        setTitle("设置");
        backEnable(true);
        cacheLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
                dialog.show();
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_logout,null);
                ViewUtil.setCornerViewDrawbleBg(view,"#FFFFFF","#FFFFFF",1,8);
                dialog.getWindow().setContentView(view);
                dialog.getWindow().setLayout(ScreenUtils.getScreenWidth()/4*3, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmLogOut();
                    }
                });
                dialog.getWindow().findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }
    private void confirmLogOut() {
        UserData.getInstance().logOut();
        ActivityUtils.showLogin(getActivity(), true);
    }
    @Override
    public void refreshView() {

    }


}
