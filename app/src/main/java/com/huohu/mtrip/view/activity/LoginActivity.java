package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.model.refresh.RefreshWithData;
import com.huohu.mtrip.presenter.contract.LoginContract;
import com.huohu.mtrip.presenter.presenter.LoginPresenter;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.view.wighet.MToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class LoginActivity extends TitleActivity implements LoginContract.View {


    @BindView(R.id.phonenum_edit)
    EditText phonenumEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.input_layout)
    LinearLayout inputLayout;
    @BindView(R.id.forget_psw_tv)
    TextView forgetPswTv;
    @BindView(R.id.btnLogin)
    TextView btnLogin;
    @BindView(R.id.rigister_now)
    TextView rigisterNow;

    private LoginContract.Presenter mPresenter;


    @Override
    protected void initView() {
        setContentLayout(R.layout.activity_login);
        ButterKnife.bind(this,getContentLayout());
        fullScreenContent(true);
        backEnable(true);
        rigisterNow.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        setTitle("登录");
        new LoginPresenter(this);

    }

    @OnClick({ R.id.forget_psw_tv, R.id.btnLogin, R.id.rigister_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_psw_tv:
                ActivityUtils.transActivity(LoginActivity.this,ForgetPassWordActivity.class,false);
                break;
            case R.id.btnLogin:
                tryLogin();
                break;
            case R.id.rigister_now:
                ActivityUtils.transActivity(LoginActivity.this,RegisterActivity.class,false);
                break;
        }
    }

    private void tryLogin() {
        String name = phonenumEdit.getText().toString();
        String psw = passwordEdit.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            MToast.showToast("用户名不能为空!");
            return;
        }
        if (TextUtils.isEmpty(psw))
        {
            MToast.showToast("密码不能为空!");
            return;
        }
        mPresenter.tryLogin(name, psw);

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onLoginSucess() {
        ActivityUtils.transActivity(LoginActivity.this,MainActivity.class,true);
    }

    @Override
    public void onLoginFail() {
        MToast.showToast("登录失败");
        //ActivityUtils.transActivity(LoginActivity.this,MainActivity.class,true);
    }

    @Override
    public void initPreferenceInfo(String phone, String password) {
        phonenumEdit.setText(phone);
        passwordEdit.setText(password);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }



}
