package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.presenter.contract.ForgetPassWordContract;
import com.huohu.mtrip.presenter.presenter.ForgetPassWordPresenter;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.MD5;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.MToast;
import com.huohu.mtrip.view.wighet.MyCountTimer;
import com.huohu.mtrip.view.wighet.ViewHolder;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class ForgetPassWordActivity extends TitleActivity  {

    private MyCountTimer mCountTimer;

    private String mConfirSmsCode;

    private ViewHolder mForViewHolder;
    private ViewHolder mResetViewHolder;
    @Override
    protected void initView() {
        mForViewHolder = new ViewHolder(this,null,R.layout.activity_forget_password,0);
        mResetViewHolder = new ViewHolder(this,null,R.layout.activity_reset_pwd,1);
        backEnable(true);
        toForgetView();

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
        final EditText confirmTv  = (EditText)mForViewHolder.getView(R.id.confirm_code_edit);
        mCountTimer = new MyCountTimer((TextView) mForViewHolder.getView(R.id.confirm_code_get), 0xffA0D92B, 0xffA0D92B);

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
                HashMap<String, Object> params = new HashMap<>();
                params.put("mobile", phonum);
                params.put("type", 2);
                WebCall.getInstance().call(WebKey.func_sendSms, params).subscribe(new BaseSubscriber<WebResponse>(ForgetPassWordActivity.this, "") {
                    @Override
                    public void onNext(WebResponse webResponse) {
                        mConfirSmsCode = webResponse.getData();
                        MToast.showToast(mConfirSmsCode);
                        mCountTimer.start();
                    }
                });

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
                tryConfirmReset();
            }
        });
    }

    private void tryConfirmReset() {
        EditText pwEdit1 = (EditText)mResetViewHolder.getConvertView().findViewById(R.id.pwd_edit_first);
        EditText pwEdit2 = (EditText)mResetViewHolder.getConvertView().findViewById(R.id.pwd_edit_second);

        EditText phoneEdit = (EditText)mForViewHolder.getConvertView().findViewById(R.id.phonenum_edit);
        EditText confirmEdit =  (EditText)mForViewHolder.getConvertView().findViewById(R.id.confirm_code_edit);

        if (pwEdit1 == null || pwEdit2 == null || phoneEdit == null || confirmEdit == null) {
            return;
        }
        String pass1 = pwEdit1.getText().toString();
        String pass2 = pwEdit2.getText().toString();

        if (TextUtils.isEmpty(pass1)) {
            MToast.showToast("请输入重设的密码");
            return;
        }
        if (TextUtils.isEmpty(pass2)) {
            MToast.showToast("请输入重设的确认密码");
            return;
        }
        if(!pass1.equals(pass2)) {
            MToast.showToast("两次输入密码不一致，请重新输入");
            return;
        }

        if (pass1.length() < 6) {
            MToast.showToast("密码长度太短");
            return;
        }
        if (pass1.length() > 19) {
            MToast.showToast("密码长度太长");
            return;
        }

        String paswordMd5 = "###" + MD5.GetMD5Code(MD5.GetMD5Code("7n6b8P4nMMyyC7N6lm" + pass1));


        HashMap<String, Object> param = new HashMap<>();
        param.put("mobile", phoneEdit.getText().toString());
        param.put("user_pass", paswordMd5);
        param.put("type", 2);
        param.put("number", confirmEdit.getEditableText().toString());

        WebCall.getInstance().call(WebKey.func_reset,param).subscribe(new BaseSubscriber<WebResponse>(this,"请稍后，正在提交信息") {
            @Override
            public void onNext(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(webResponse);
                if(!status) {
                    MToast.showToast(webResponse.getData());
                }else {
                    MToast.showToast("重置密码成功");
                    ActivityUtils.transActivity(ForgetPassWordActivity.this, LoginActivity.class, true);
                }
            }
        });
    }



}
