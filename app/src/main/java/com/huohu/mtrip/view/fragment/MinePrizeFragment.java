package com.huohu.mtrip.view.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.PrizeInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.WebCall;
import com.huohu.mtrip.model.net.WebKey;
import com.huohu.mtrip.model.net.WebResponse;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.adapter.PrizeListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MinePrizeFragment extends PageImpBaseFragment {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.m_list)
    PullToRefreshListView mListView;
    @BindView(R.id.null_data_text)
    TextView nullDataText;
    @BindView(R.id.null_data_retrye)
    TextView nullDataRetrye;
    @BindView(R.id.null_data_layout)
    RelativeLayout nullDataLayout;
    private String[] mTitles = {"待领取", "历史"};
    private ArrayList<CustomTabEntity> tabs = new ArrayList<>();
    private List<PrizeInfo> mUndoList = new ArrayList<>();
    private List<PrizeInfo> mDoneList = new ArrayList<>();
    private PrizeListAdapter mAdapter;

    @Override
    protected void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            tabs.add(new TabEntity(mTitles[i]));
        }
        tabLayout.setTabData(tabs);
        tabLayout.setCurrentTab(0);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                updateAdapter();
            }

            @Override
            public void onTabReselect(int position) {
                updateAdapter();
            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();
            }
        });

        mAdapter.setListener(new PrizeListAdapter.Listener() {
            @Override
            public void onDui(PrizeInfo item) {
                gotoPrizeDetailFragment(item);
            }

            @Override
            public void onDetail(PrizeInfo item) {
                    gotoPrizeDetailFragment(item);

            }
        });
        loadData();
    }

    private void loadData() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> m = new HashMap<>();
        m.put("url","");
        m.put("prize_time","2017-08-20 15:35");
        m.put("duration","2017-08-20 15:35");
        m.put("status",0);
        m.put("code","464313745446");
        m.put("url","kele.jpg");
        Map<String,Object> m1 = new HashMap<>();
        m1.put("prize_time","2017-08-20 15:35");
        m1.put("dui_time","2017-08-20 15:35");
        m1.put("duration","2017-08-20 15:35");
        m1.put("status",1);
        m1.put("code","464313745446");
        m1.put("url","kele.jpg");
        Map<String,Object> m2 = new HashMap<>();
        m2.put("url","");
        m2.put("prize_time","2017-08-20 15:35");
        m2.put("dui_time","2017-08-20 15:35");
        m2.put("duration","2017-08-20 15:35");
        m2.put("status",2);
        m2.put("code","464313745446");
        m2.put("url","kele.jpg");
        list.add(m);
        list.add(m1);
        list.add(m2);


        HashMap<String,Object> p = new HashMap<>();
        p.put("memberid", UserData.getInstance().getUserId());
        WebCall.getInstance().call(WebKey.func_getwingoods,p).subscribe(new BaseSubscriber<WebResponse>(getContext(),"") {
            @Override
            public void onNext(WebResponse webResponse) {
                mListView.onRefreshComplete();
                mUndoList.clear();
                mDoneList.clear();

                List<PrizeInfo> infos = Utils.parseObjectToListEntry(webResponse.getData(), PrizeInfo.class);
                for (PrizeInfo info:infos) {

                    int status  = Utils.toInteger(info.getWin_status());
                    switch (status) {
                        case 1:
                            mUndoList.add(info);
                            break;
                        case 2:
                        case 3:
                            mDoneList.add(info);
                            break;
                    }
                }
                updateAdapter();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mListView.onRefreshComplete();
            }
        });

    }
    private void gotoPrizeDetailFragment(  PrizeInfo item ) {
        gotoFragment(FragKey.prize_detail_fragment, JSONObject.toJSONString(item));
    }
    private void updateAdapter() {
        int tab = tabLayout.getCurrentTab();
        List<PrizeInfo> list = new ArrayList<>();
        switch (tab) {
            case 0:
                list.addAll(mUndoList);
                break;
            case 1:
                list.addAll(mDoneList);
                break;

        }
        if (list == null || list.size() < 1) {
            nullDataLayout.setVisibility(View.VISIBLE);
            ViewUtil.setGone(mListView);
            nullDataRetrye.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshView();
                }
            });
        } else {
            nullDataLayout.setVisibility(View.GONE);
            ViewUtil.setVisible(mListView);
        }
        mAdapter.refreshData(list);
    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_prize_layout);
        ButterKnife.bind(this, getContentLayout());
        setTitle("我的奖品");
        backEnable(true);
        ViewUtil.setGone(nullDataLayout);
        mAdapter = new PrizeListAdapter(getActivity(), new ArrayList<PrizeInfo>());
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void refreshView() {

    }



    public class TabEntity implements CustomTabEntity {
        public String title;

        public TabEntity(String title) {
            this.title = title;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return R.mipmap.ic_page_indicator_focused;
        }

        @Override
        public int getTabUnselectedIcon() {
            return R.mipmap.ic_page_indicator;
        }
    }

}
