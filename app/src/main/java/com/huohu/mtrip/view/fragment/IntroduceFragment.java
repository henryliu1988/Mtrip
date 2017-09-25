package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class IntroduceFragment extends PageImpBaseFragment {
    Unbinder unbinder;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void initData() {
        WebCall.getInstance().call(WebKey.func_getshow,new HashMap<String, Object>()).subscribe(new BaseSubscriber<WebResponse>() {
            @Override
            public void onNext(WebResponse webResponse) {
                String url = webResponse.getData();
                if (url.startsWith("/")) {
                    url =  url.substring(1);
                }
                webView.loadUrl(WebKey.WEBKEY_BASE + url);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });

            }
        });
    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_introduce_layout);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        setTitle("乐园介绍");
        setRightImageTips(R.mipmap.title_msg);

    }


    @Override
    protected void onRightTipViewClick() {
        gotoFragment(FragKey.msg_cate_fragment);
    }

    @Override
    public void refreshView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
