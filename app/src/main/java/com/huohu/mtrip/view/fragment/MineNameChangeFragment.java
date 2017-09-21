package com.huohu.mtrip.view.fragment;

import android.text.TextUtils;
import android.widget.EditText;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.view.wighet.MToast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MineNameChangeFragment extends PageImpBaseFragment {
    @BindView(R.id.name_edit)
    EditText nameEdit;

    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_mine_name_change);
        ButterKnife.bind(this,getContentLayout());
        backEnable(true);
        setTitle("昵称");
        setRightText("保存");
        String nickName = UserData.getInstance().getToken().getUser_nicename();
        if (!TextUtils.isEmpty(nickName)) {
            nameEdit.setHint(nickName);
        }
    }

    @Override
    protected void onRightClick() {
        String name = nameEdit.getText().toString();
        if (TextUtils.isEmpty(name)) {
            MToast.showToast("请输入昵称");
            return;
        }
        back();
    }

    @Override
    public void refreshView() {

    }


}
