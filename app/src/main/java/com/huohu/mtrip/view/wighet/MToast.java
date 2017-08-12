
package com.huohu.mtrip.view.wighet;

import android.widget.Toast;

import com.huohu.mtrip.app.MApplication;


/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class MToast {

    public static void showToast(String text)
    {
        Toast.makeText(MApplication.getInstance().getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
