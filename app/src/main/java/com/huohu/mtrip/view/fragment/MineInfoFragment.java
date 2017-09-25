package com.huohu.mtrip.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.DicData;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.model.entity.NormalItem;
import com.huohu.mtrip.model.entity.TokenInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.model.net.FileUpLoad;
import com.huohu.mtrip.model.refresh.RefreshKey;
import com.huohu.mtrip.model.refresh.RefreshManager;
import com.huohu.mtrip.model.refresh.RefreshWithKey;
import com.huohu.mtrip.presenter.ActivityResultView;
import com.huohu.mtrip.util.ImageUtils;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewKey;
import com.huohu.mtrip.view.wighet.MToast;
import com.huohu.mtrip.view.wighet.MapTextView;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MineInfoFragment extends PageImpBaseFragment implements  ActivityResultView {
    @BindView(R.id.real_name)
    TextView realName;
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.user_layout)
    RelativeLayout userLayout;
    @BindView(R.id.user_name_title)
    TextView userNameTitle;
    @BindView(R.id.user_name_value)
    TextView userNameValue;
    @BindView(R.id.user_sex_title)
    TextView userSexTitle;
    @BindView(R.id.user_sex_value)
    MapTextView userSexValue;
    @BindView(R.id.user_birth_title)
    TextView userDateTitle;
    @BindView(R.id.user_birth_value)
    TextView userDateValue;

    @Override
    protected void initData() {
        TokenInfo info = UserData.getInstance().getToken();
        String realNameText = info.getUser_nicename();
        //realName.setText(realNameText);
        userNameValue.setText(Utils.toString(realNameText));
        int sex = Utils.toInteger(info.getSex());
        if (sex > 0) {
            NormalItem item = DicData.getInstance().getSexById(sex + "");
            userSexValue.setMap(item.getId(), item.getName());
        }

        String photoPath = info.getAvatar();
        if (!TextUtils.isEmpty(photoPath) && photoPath.length()>1) {
            ImageUtils.getInstance().displayFromRemoteOver(photoPath, userPhoto);
        }
        userDateValue.setText(info.getBirthday());
    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_mine_info);
        ButterKnife.bind(this,getContentLayout());
        backEnable(true);
        setTitle("个人资料");
        addOnActivityResultView(this);
        RefreshManager.getInstance().addNewListener(RefreshKey.USER_INFO_UPDATE, new RefreshWithKey() {
            @Override
            public void onRefreshWithKey(int key) {
                if (key == RefreshKey.USER_INFO_UPDATE) {
                    refreshView();
                }
            }
        });

    }

    @Override
    public void refreshView() {
        initData();
    }

    @OnClick({R.id.user_photo, R.id.user_name_value, R.id.user_sex_value, R.id.user_birth_value})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_photo:
                selectImg();
                break;
            case R.id.user_name_value:
                gotoFragment(FragKey.mine_name_fragment);
                break;
            case R.id.user_sex_value:
                gotoFragment(FragKey.mine_sex_fragment);
                break;
            case R.id.user_birth_value:
                gotoFragment(FragKey.mine_birth_fragment);
                break;
        }

    }


    @Override
    public void onActivityResult1(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从相册选择
            case SELECT_PICTURE:
                if (data != null) {
                    Uri uri = data.getData();
                    String path = Utils.getPath(uri);
                    //ImageUtils.getInstance().displayFromRemote(path,userPhoto);
                    beginCrop(uri);
                    // mPresenter.updateMemberPhoto(path);
                }
                break;
            //拍照添加图片
            case SELECT_CAMER:
                if (mCameraPath != null) {
                    String p = mCameraPath.toString();
                    Uri uri = Uri.fromFile(new File(p));
                    beginCrop(uri);
                }
                break;
            case Crop.REQUEST_CROP:
                handleCrop(resultCode,data);
            default:
                break;
        }
    }
    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), "temp.jpg"));
        Crop.of(source, destination).asSquare().start(getActivity());
    }
    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == getActivity().RESULT_OK) {
            Uri uri =   Crop.getOutput(result);
            String path =  Utils.getPath(uri);
            updateMemberPhoto(path);
        } else if (resultCode == Crop.RESULT_ERROR) {
            MToast.showToast(Crop.getError(result).getMessage());
        }
    }

    private void updateMemberPhoto(String path) {
        List<Map<String, Object>> files = new ArrayList<>();
        final Map<String, Object> file = new HashMap<>();
        file.put(ViewKey.FILE_KEY_TYPE, ViewKey.TYPE_FILE_PATH);
        file.put(ViewKey.FILE_KEY_URL, path);
        files.add(file);
        FileUpLoad.uploadFiles(files).flatMap(new Func1<List<Map<String, Object>>, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(List<Map<String, Object>> files) {
                if (files.size() < 1) {
                    return Observable.just(false);
                } else {
                    String url = Utils.toString(files.get(0).get("url"));
                    TokenInfo info = new TokenInfo(UserData.getInstance().getToken());
                    info.setAvatar(url);
                    return UserData.getInstance().updateUserInfo(info);
                }
            }
        }).subscribe(new BaseSubscriber<Boolean>(getContext(), "正在上传头像") {
            @Override
            public void onNext(Boolean result) {
                if (result) {
                    TokenInfo info = UserData.getInstance().getToken();
                    String photoPath = info.getAvatar();
                    if (!TextUtils.isEmpty(photoPath)) {
                        ImageUtils.getInstance().displayFromRemoteOver(photoPath, userPhoto);
                    }
                } else {
                    MToast.showToast("上传失败");
                }
            }
        });

    }
    @Override
    public void onPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> permissionList = Arrays.asList(permissions);
        if (permissionList.contains(Manifest.permission.CAMERA)) {
            toGetCameraImage();
        } else if (permissionList.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            toGetLocalImage();
        }

    }

    @Override
    public void onDestroy() {
        removeOnActivityResultView(this);
        super.onDestroy();
    }


}
