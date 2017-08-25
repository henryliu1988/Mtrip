package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.PickViewData;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.view.wighet.MToast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
        back();
    }

    @Override
    public void refreshView() {

    }


}
