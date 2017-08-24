package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.presenter.contract.ForgetPassWordContract;
import com.huohu.mtrip.presenter.presenter.ForgetPassWordPresenter;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.MToast;
import com.huohu.mtrip.view.wighet.MyCountTimer;
import com.huohu.mtrip.view.wighet.ViewHolder;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class ForgetPassWordActivity extends TitleActivity implements ForgetPassWordContract.View {

    private MyCountTimer mCountTimer;

    private String mConfirSmsCode;

    private ForgetPassWordContract.Presenter mPresenter;
    private ViewHolder mForViewHolder;
    private ViewHolder mResetViewHolder;
    @Override
    protected void initView() {
        mForViewHolder = new ViewHolder(this,null,R.layout.activity_forget_password,0);
        mResetViewHolder = new ViewHolder(this,null,R.layout.activity_reset_pwd,1);
        backEnable(true);
        toForgetView();

        new ForgetPassWordPresenter(this);
    }


    private void toForgetView() {
        setContentLayout(mForViewHolder.getConvertView());
        setTitle("忘记密码");
        mTitleBackIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView confirmGetTv = (TextView)  mForViewHolder.getView(R.id.confirm_code_get);
        ViewUtil.setCornerViewDrawbleBg(confirmGetTv,"#FFFFFF","#A0D92B",1,4);
        final EditText telTv  = (EditText)mForViewHolder.getView(R.id.phonenum_edit);
        final EditText confirmTv  = (EditText)mForViewHolder.getView(R.id.phonenum_edit);
        mCountTimer = new MyCountTimer(confirmGetTv, 0xffA0D92B, 0xffA0D92B);

        confirmGetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonum = telTv.getText().toString();
                if (TextUtils.isEmpty(phonum) ) {
                    MToast.showToast("请输入手机号");
                    return;
                }
                if(!Utils.isPhone(phonum)) {
                    MToast.showToast("请输入合法的手机号");
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.getConfirmCode(phonum).subscribe(new BaseSubscriber<WebResponse>(ForgetPassWordActivity.this, "") {
                        @Override
                        public void onNext(WebResponse webResponse) {
                            mConfirSmsCode = webResponse.getData();
                            MToast.showToast(mConfirSmsCode);
                            mCountTimer.start();
                        }
                    });
                }
            }
        });
        mForViewHolder.getView(R.id.net_step).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmCode = confirmTv.getText().toString();
                if (TextUtils.isEmpty(confirmCode)) {
                    MToast.showToast("请输入验证码");
                    return;
                }
                toResetView();
            }
        });
    }

    private void toResetView() {
        setContentLayout(mResetViewHolder.getConvertView());
        setTitle("重设密码");
        mTitleBackIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toForgetView();
            }
        });
        mResetViewHolder.getView(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void tryConfirmReset() {

    }

    @Override
    public void resetOk(String msg) {
    }

    @Override
    public void setPresenter(ForgetPassWordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }


}
