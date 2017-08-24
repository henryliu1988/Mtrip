package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.presenter.contract.RegisterContract;
import com.huohu.mtrip.presenter.presenter.RegisterPresenter;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.MyCountTimer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class RegisterActivity extends TitleActivity implements RegisterContract.View {


    @BindView(R.id.phonenum_edit)
    EditText phonenumEdit;
    @BindView(R.id.confirm_code_edit)
    EditText confirmCodeEdit;
    @BindView(R.id.confirm_code_get)
    TextView confirmCodeGet;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.input_layout)
    LinearLayout inputLayout;
    @BindView(R.id.register_now)
    TextView registerNow;
    @BindView(R.id.legend)
    TextView legend;
    private RegisterContract.Presenter mPresenter;
    private MyCountTimer mCountTimer;

    private String mConfirSmsCode;

    @Override
    protected void initView() {
        setContentLayout(R.layout.activity_rigister);
        ButterKnife.bind(this, getContentLayout());
        setTitle("注册");
        backEnable(true);
        ViewUtil.setCornerViewDrawbleBg(confirmCodeGet,"#FFFFFF","#A0D92B",1,4);
        new RegisterPresenter(this);

    }

    private void tryToRegister() {
    }

    private void tryGetConfirmCode() {
    }

    @Override
    public void registerOK(String msg) {
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
