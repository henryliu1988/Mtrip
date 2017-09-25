package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.model.net.WebUtils;
import com.huohu.mtrip.presenter.contract.RegisterContract;
import com.huohu.mtrip.presenter.presenter.RegisterPresenter;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.MD5;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.wighet.MToast;
import com.huohu.mtrip.view.wighet.MyCountTimer;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class RegisterActivity extends TitleActivity {


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
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToRegister();
            }
        });
        confirmCodeGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetConfirmCode();
            }
        });
        mCountTimer = new MyCountTimer(confirmCodeGet, 0xff658dff, 0xff658dff);
    }

    private void tryToRegister() {
        String phoneNum = phonenumEdit.getText().toString();
        if (TextUtils.isEmpty(phoneNum)) {
            MToast.showToast("请输入手机号");
            return;
        }
        if (!Utils.isPhone(phoneNum)) {
            MToast.showToast("请输入正确的手机号");
            return;
        }
        String confirmCode = confirmCodeEdit.getText().toString();
        if (TextUtils.isEmpty(confirmCode)) {
            MToast.showToast("请输入验证码");
            return;
        }
        String passoword = passwordEdit.getText().toString();
        if (TextUtils.isEmpty(passoword)) {
            MToast.showToast("请输入密码");
            return;
        }
        if (passoword.length() < 6) {
            MToast.showToast("密码长度太短");
            return;
        }
        if (passoword.length() > 19) {
            MToast.showToast("密码长度太长");
            return;
        }
        String paswordMd5 = "###" + MD5.GetMD5Code(MD5.GetMD5Code("7n6b8P4nMMyyC7N6lm" + passoword));
        HashMap<String, Object> param = new HashMap<>();
        param.put("mobile", phoneNum);
        param.put("user_pass",paswordMd5);
        param.put("number", confirmCode);

        WebCall.getInstance().call(WebKey.func_register,param).subscribe(new BaseSubscriber<WebResponse>() {
            @Override
            public void onNext(WebResponse webResponse) {
                MToast.showToast("注册成功");
                ActivityUtils.transActivity(RegisterActivity.this, LoginActivity.class, true);
            }
        });
    }

    private void tryGetConfirmCode() {
       final  String phoneNum = phonenumEdit.getText().toString();
        if (TextUtils.isEmpty(phoneNum)) {
            MToast.showToast("请输入手机号");
            return;
        }
        if (!Utils.isPhone(phoneNum)) {
            MToast.showToast("请输入正确的手机号");
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", phoneNum);
        params.put("type",1);
      WebCall.getInstance().call(WebKey.func_checkMobile,params).flatMap(new Func1<WebResponse, Observable<WebResponse>>() {
            @Override
            public Observable<WebResponse> call(WebResponse webResponse) {
                boolean status = WebUtils.getWebStatus(webResponse);
                if (status) {
                    WebResponse response = new WebResponse(1,"该手机号码已经注册,请直接登录","");
                    return Observable.just(response);
                } else {
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("mobile", phoneNum);
                    params.put("type", 1);
                    return WebCall.getInstance().call(WebKey.func_sendSms, params);
                }
            }
        }).subscribe(new BaseSubscriber<WebResponse>(this,"") {
          @Override
          public void onNext(WebResponse webResponse) {
              int errot = webResponse.getError();
              if (errot == 0) {
                  mConfirSmsCode = webResponse.getData();
                  mCountTimer.start();
              } else {
                  MToast.showToast(webResponse.getInfo());
              }
          }
      });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
