package com.huohu.mtrip.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huohu.mtrip.util.ActivityUtils;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.finishActivity(this);
        super.onDestroy();
    }
}
