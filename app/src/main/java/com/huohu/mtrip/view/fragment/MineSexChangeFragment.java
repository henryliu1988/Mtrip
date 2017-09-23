package com.huohu.mtrip.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.DicData;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.view.adapter.SingleSelectAdapter;
import com.huohu.mtrip.view.wighet.MToast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MineSexChangeFragment extends PageImpBaseFragment {
    @BindView(R.id.rc_view)
    RecyclerView rcView;

    private SingleSelectAdapter mAdapter;
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_sex_change_layout);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        setTitle("性别");
        setRightText("保存");
        rcView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SingleSelectAdapter(getContext());
        rcView.setAdapter(mAdapter);
        String sex = UserData.getInstance().getToken().getSex();
        mAdapter.initDatass(DicData.getInstance().getSex(),sex);
    }

    @Override
    protected void onRightClick() {
        String selId = mAdapter.getSelectListId();
        if (TextUtils.isEmpty(selId)) {
            MToast.showToast("请选择性别");
            return;
        }
        back();
        TokenInfo info = new TokenInfo(UserData.getInstance().getToken());
        info.setSex(selId);
        UserData.getInstance().updateUserInfo(info).subscribe(new BaseSubscriber<Boolean>(getContext(),"") {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    MToast.showToast("修改信息成功");
                    back();
                }else {
                    MToast.showToast("修改信息失败");

                }
            }
        });

    }

    @Override
    public void refreshView() {

    }

}
