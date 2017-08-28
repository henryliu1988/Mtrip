package com.huohu.mtrip.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.huohu.mtrip.R;
import com.huohu.mtrip.app.MApplication;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.key.IntentKey;
import com.huohu.mtrip.presenter.ActivityResultView;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.ScreenUtils;
import com.huohu.mtrip.util.ViewUtil;
import com.huohu.mtrip.view.activity.PagerImpActivity;
import com.huohu.mtrip.view.wighet.MToast;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public abstract class PageImpBaseFragment extends TitleFragment
{

    public static final int SELECT_PICTURE = 1;
    public static final int SELECT_CAMER = 0;


    protected int getViewId()
    {
        Activity activity = getActivity();
        if (!(activity instanceof PagerImpActivity))
        {
            return -1;
        }
        return ((PagerImpActivity) activity).getFragmentViewId();
    }


    protected void gotoFragment(int key)
    {
        if (UserData.getInstance().needLogin(key,getActivity())) {
            return;
        }
        String tag = FragKey.FragMap.get(key);
        PageImpBaseFragment newFragment = PagerFragmentFactory.createFragment(key);
        if (!TextUtils.isEmpty(tag) && newFragment != null)
        {
            FragmentUtils.changeFragment(getActivity(), this, newFragment, tag, getViewId());
        }
    }
    /**
     * 收起软键盘
     */
    public static void closeKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) MApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    protected void gotoFragment(int key, String info)
    {
        if (UserData.getInstance().needLogin(key,getActivity())) {
            return;
        }
        String tag = FragKey.FragMap.get(key);
        PageImpBaseFragment newFragment = PagerFragmentFactory.createFragment(key);
        if (!TextUtils.isEmpty(tag) && newFragment != null)
        {
            Bundle bundle = new Bundle();
            bundle.putString(IntentKey.FRAG_INFO,info);
            newFragment.setArguments(bundle);
            FragmentUtils.changeFragment(getActivity(), this, newFragment, tag, getViewId());
        }
    }
    protected void back()
    {
        Activity activity = getActivity();
        if (!(activity instanceof PagerImpActivity))
        {
            return;
        }
        FragmentUtils.back((PagerImpActivity) activity);
    }

    protected void back(int step)
    {
        while (step > 0)
        {
            back();
            step--;
        }
    }

    protected void back(int step, int[] fragkey)
    {
        while (step > 0)
        {
            back();
            step--;
        }
        Activity activity = getActivity();
        if (!(activity instanceof PagerImpActivity))
        {
            return;
        }
        FragmentUtils.refreshFragments((PagerImpActivity) activity, fragkey);
    }

    protected void back(int[] fragkey)
    {
        Activity activity = getActivity();
        if (!(activity instanceof PagerImpActivity))
        {
            return;
        }
        FragmentUtils.back((PagerImpActivity) activity);
        FragmentUtils.refreshFragments((PagerImpActivity) activity, fragkey);
    }

    protected void addOnActivityResultView(ActivityResultView view)
    {
        if (!(getActivity() instanceof PagerImpActivity))
        {
            return;
        }
        PagerImpActivity activity = (PagerImpActivity) getActivity();
        activity.addOnActivityResultView(view);
    }


    protected void removeOnActivityResultView(ActivityResultView view)
    {
        if (!(getActivity() instanceof PagerImpActivity))
        {
            return;
        }
        PagerImpActivity activity = (PagerImpActivity) getActivity();
        activity.removeOnActivityResultView(view);
    }
    protected void selectImg()
    {
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.show();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.select_image_layout,null);
        ViewUtil.setCornerViewDrawbleBg(view,"#999999","#FFFFFF");
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setLayout(ScreenUtils.getScreenWidth()/4*3, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().findViewById(R.id.camera_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SELECT_PICTURE);
                } else
                {
                    toGetLocalImage();
                }
                dialog.dismiss();
            }
        });
        dialog.getWindow().findViewById(R.id.gallery_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, SELECT_CAMER);
                } else
                {
                    toGetCameraImage();
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 选择本地图片
     */
    public void toGetLocalImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (getActivity() != null)
        {
            getActivity().startActivityForResult(intent, SELECT_PICTURE);
        }

    }


    protected File mCameraPath = null;

    /**
     * 照相选择图片
     */
    public void toGetCameraImage()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        String time = DateUtil.getCurrent();
        File path = ImageUtils.getInstance().getAppImageFilePath(time + ".jpg");
        if (path == null)
        {
            MToast.showToast("创建路径失败");
        }
        mCameraPath = path;
        if (getActivity() != null)
        {
            Uri uri = Uri.fromFile(mCameraPath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            getActivity().startActivityForResult(intent, SELECT_CAMER);
        }
        // finish();
    }
}


