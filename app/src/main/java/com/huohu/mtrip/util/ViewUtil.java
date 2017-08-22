package com.huohu.mtrip.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huohu.mtrip.app.MApplication;

/**
 * Created by admin on 2017/1/16.
 */
public class ViewUtil
{

    public static void setTVColor(String str, char ch1, char ch2, int color, TextView tv)
    {
        int a = str.indexOf(ch1); //从字符ch1的下标开始
        int b = str.indexOf(ch2) + 1; //到字符ch2的下标+1结束,因为SpannableStringBuilder的setSpan方法中区间为[ a,b )左闭右开
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), a, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }


    public static void setTvColor(String str, String changestr, int color, TextView tv)
    {
        int a = str.indexOf(changestr);
        if (TextUtils.isEmpty(changestr) || a < 0)
        {
            return;
        }
        if (TextUtils.isEmpty(str))
        {
            tv.setTextColor(color);
            return;
        }
        int b = a + changestr.length();
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), a, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }


    public static void setTvColor(String changestr, int color, TextView tv)
    {
        if (tv == null) {
            return;
        }
        String str = tv.getText().toString();
        int a = str.indexOf(changestr);
        if (TextUtils.isEmpty(changestr) || a < 0)
        {
            return;
        }
        if (TextUtils.isEmpty(str))
        {
            tv.setTextColor(color);
            return;
        }
        int b = a + changestr.length();
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), a, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }

    public static void setCornerViewDrawbleBg(View view, String strokeColor, String fillCorlor) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        int roundRadius = 5;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(strokeColor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setCornerViewDrawbleRoundBg(View view, String strokeColor, String fillCorlor, int round) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int roundRadius = round;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(strokeColor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(1, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setCornerViewDrawbleRoundBg(View view, String strokeColor, String fillCorlor, int strokWidth, int round) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int roundRadius = round;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(strokeColor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setCornerViewDrawbleBg(View view, String fillCorlor, String strokeColor, int strokWidth) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int roundRadius = 5;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(strokeColor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }

    public static void setCornerViewDrawbleBg(View view, String fillCorlor, String strokeColor, int strokWidth,int raduis) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int roundRadius = raduis;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(strokeColor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setCornerViewDrawbleBg(View view, String fillCorlor, int radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        int roundRadius = radius;     //
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }

    public static void setCornerViewDrawbleBg(View view, String fillCorlor, float[] radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        float[] roundRadius = radius;     // 5dp 圆角半径
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadii(roundRadius);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }

    public static void setLeftCornerViewDrawbleBg(View view, String fillCorlor, int radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        float[] rR = {0,0,0,0,0,0,0,0};
        rR[0] = radius;
        rR[1] = radius;
        rR[6] = radius;
        rR[7] = radius;
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadii(rR);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setRightCornerViewDrawbleBg(View view, String fillCorlor, int radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        float[] rR = {0,0,0,0,0,0,0,0};
        rR[2] = radius;
        rR[3] = radius;
        rR[4] = radius;
        rR[5] = radius;
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadii(rR);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setTopCornerViewDrawbleBg(View view, String fillCorlor, int radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        float[] rR = {0,0,0,0,0,0,0,0};
        rR[0]= radius;
        rR[1] = radius;
        rR[2] = radius;
        rR[3] = radius;
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadii(rR);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setBottomCornerViewDrawbleBg(View view, String fillCorlor, int radius) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        int strokeWidth = 1;     // 1dp 边框宽度
        float[] rR = {0,0,0,0,0,0,0,0};
        rR[4]= radius;
        rR[5] = radius;
        rR[6] = radius;
        rR[7] = radius;
        int strokeColorInt = Color.parseColor(fillCorlor);//边框颜色
        int fillColorInt = Color.parseColor(fillCorlor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setCornerRadii(rR);
        gd.setStroke(strokeWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }
    public static void setOverViewDrawbleBg(View view, String fillColor, String strokColor, int strokWidth) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setShape(GradientDrawable.OVAL);
        int strokeColorInt = Color.parseColor(strokColor);//边框颜色
        int fillColorInt = Color.parseColor(fillColor); //内部填充颜色
        gd.setColor(fillColorInt);
        gd.setStroke(strokWidth, strokeColorInt);
        view.setBackgroundDrawable(gd);
    }

    public static void setOverViewDrawbleBg(View view, String fillColor) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setShape(GradientDrawable.OVAL);
        int fillColorInt = Color.parseColor(fillColor); //内部填充颜色
        gd.setColor(fillColorInt);
        view.setBackgroundDrawable(gd);
    }


    public static void setDrawbleToTv(Context context, int drawbleId, TextView view) {

        if (drawbleId< 0) {
            view.setCompoundDrawables(null, null, null, null);//画在右边
        } else {
            Drawable drawable = context.getResources().getDrawable(drawbleId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            view.setCompoundDrawables(null, null, drawable, null);//画在右边
        }
    }

    public static void setVisible(View v)
    {
        v.setVisibility(View.VISIBLE);
    }

    public static void setInVisible(View v)
    {
        v.setVisibility(View.INVISIBLE);
    }

    public static void setGone(View v)
    {
        v.setVisibility(View.GONE);
    }

    public static void transVisibleAndGone(View v)
    {
        if (v.getVisibility() == View.VISIBLE)
        {
            v.setVisibility(View.GONE);
        } else
        {
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void transVisibleAndInViSible(View v)
    {
        if (v.getVisibility() == View.VISIBLE)
        {
            v.setVisibility(View.INVISIBLE);
        } else
        {
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void setBackgroundDrawable(View v, int id)
    {
        Resources resources = MApplication.getInstance().getContext().getResources();
        Drawable btnDrawable = resources.getDrawable(id);
        v.setBackgroundDrawable(btnDrawable);
    }

    public static void setImageSrc(ImageView v, int id)
    {
        Resources resources = MApplication.getInstance().getContext().getResources();
        Drawable btnDrawable = resources.getDrawable(id);
        v.setImageDrawable(btnDrawable);
    }
}
