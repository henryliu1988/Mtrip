package com.huohu.mtrip.view.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MineBirthChangeFragment extends PageImpBaseFragment {
    @BindView(R.id.name_edit)
    TextView nameEdit;


    TimePickerView pickView ;

    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_birth_change_layout);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        setTitle("性别");
        setRightText("保存");
        pickView  = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH_DAY);
        pickView.setCancelable(true);
        pickView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener()
        {
            @Override
            public void onTimeSelect(Date d)
            {
                String date = DateUtil.dateToString(d, DateUtil.LONG_DATE_FORMAT);
                nameEdit.setText(date);
            }
        });
        nameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickView.show();
            }
        });

    }

    @Override
    protected void onRightClick() {
        String date = nameEdit.getText().toString();
        if (TextUtils.isEmpty(date)) {
            MToast.showToast("请选择您的出生日期");
            return;
        }
        Date date1 = DateUtil.stringtoDate(date,DateUtil.LONG_DATE_FORMAT);
        String strDate = DateUtil.dateToString(date1,DateUtil.LONG_DATE_FORMAT2);
        TokenInfo info = new TokenInfo(UserData.getInstance().getToken());
        info.setBirthday(strDate);
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
