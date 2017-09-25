package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.MsgData;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.MsgInfo;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class MsgDetailFragment extends PageImpBaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void initData() {
       final MsgInfo info = Utils.parseObjectToEntry(mArgInfo,MsgInfo.class);
        if (info == null || TextUtils.isEmpty(info.getId())) {
            backFragment();
        }
        String id = info.getId();
        HashMap<String,Object> p = new HashMap<>();
        p.put("memberid", UserData.getInstance().getUserId());
        p.put("msgid",id);
        WebCall.getInstance().call(WebKey.func_addreadlog,p).subscribe(new BaseSubscriber<WebResponse>() {
            @Override
            public void onNext(WebResponse webResponse) {
                MsgData.getInstance().loadUnreadData();
                title.setText(info.getTitle());
                time.setText(DateUtil.getTimeDiffDayCurrent(Utils.toLong(info.getAddtime())));
                content.setText(Html.fromHtml(info.getContent()));

            }
        });

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_msg_detail);
        ButterKnife.bind(this,getContentLayout());
        setTitle("查看消息");
        backEnable(true);

    }

    @Override
    public void refreshView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
