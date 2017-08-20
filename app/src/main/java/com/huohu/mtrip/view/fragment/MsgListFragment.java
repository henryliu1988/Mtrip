package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.MsgData;
import com.huohu.mtrip.model.key.IntentKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.adapter.MsgItemAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class MsgListFragment extends PageImpBaseFragment {

    @BindView(R.id.m_list)
    PullToRefreshListView mList;
    @BindView(R.id.null_data_text)
    TextView nullDataText;
    @BindView(R.id.null_data_retrye)
    TextView nullDataRetrye;
    @BindView(R.id.null_data_layout)
    RelativeLayout nullDataLayout;


    protected  Map<String,Object> info = new HashMap<>();

    public int type = MsgData.ALL_MSG;

    private MsgItemAdapter mAdapter;
    @Override
    protected void initData() {
        refreshView();
    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_msg_list);
        ButterKnife.bind(this, getContentLayout());
        ViewUtil.setGone(nullDataLayout);
        backEnable(true);
        String arginfo = getArguments().getString(IntentKey.FRAG_INFO);
        info = Utils.parseObjectToMapString(arginfo);
        String title = Utils.toString(info.get("title"));
        type = Utils.toInteger(info.get("type"));
        setTitle(title);
        mAdapter = new MsgItemAdapter(getContext());
        mList.setAdapter(mAdapter);
        refreshView();
    }



    @Override
    public void refreshView() {
        MsgData.getInstance().getMsgListByType(type).subscribe(new BaseSubscriber<List<Map<String, Object>>>() {
            @Override
            public void onNext(List<Map<String, Object>> maps) {
                mAdapter.refreshData(maps);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
