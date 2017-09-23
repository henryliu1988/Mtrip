package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.MsgInfo;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.Utils;

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

        MsgInfo info = Utils.parseObjectToEntry(mArgInfo,MsgInfo.class);
        if (info == null) {
            backFragment();
        }
        title.setText(info.getTitle());
        time.setText(DateUtil.getTimeDiffDayCurrent(Utils.toLong(info.getAddtime())));
        content.setText(Html.fromHtml(info.getContent()));
        String id = info.getId();

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
