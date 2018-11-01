package com.heyrent.app.renter.view.main;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseActivity;
import com.heyrent.app.renter.model.UserInfoBean;
import com.heyrent.app.renter.utils.Constant;
import com.heyrent.app.renter.utils.LoginManager;
import com.heyrent.app.renter.utils.NumberUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PersonalInfoActivity extends BaseActivity implements PersonalInfoContract.View {

    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int STORAGE_REQUEST_CODE = 0x1001;

    @BindView(R.id.tv_personal_info_first_name)
    TextView mFirstNameTv;
    @BindView(R.id.tv_personal_info_last_name)
    TextView mLastNameTv;
    @BindView(R.id.tv_personal_info_phone)
    TextView mPhoneTv;

    @Inject
    LoginManager mLoginManager;
    @Inject
    PersonalInfoPresenter mPresenter;

    @Override
    protected int layout() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initView() {
        setTitleText(R.string.personal_info_title);
        setStatusBarColor(R.color.colorWhite);

        setLeftIconListener(v -> finish());
        initInfo();
    }

    @Override
    protected void initData() {
        mPresenter.takeView(this);
        getLifecycle().addObserver(mPresenter);

    }

    @Override
    protected boolean needStatusBar() {
        return true;
    }

    @Override
    protected boolean needTitleBar() {
        return true;
    }

    private void initInfo() {
        UserInfoBean userInfo = mLoginManager.getUserInfo();
        if (userInfo == null) {
            return;
        }
        mFirstNameTv.setText(userInfo.getUserInfo().getFirstName());
        mLastNameTv.setText(userInfo.getUserInfo().getLastName());
        mPhoneTv.setText(NumberUtils.splitPhoneBy4Char(userInfo.getUserInfo().getPhone()));
    }

    private void startModifyActivity(int type) {
        Intent intent = new Intent(this, ModifyUserInfoActivity.class);
        intent.putExtra(Constant.K_MODIFY_USER_INFO_TYPE, type);
        startActivity(intent);
    }

    private void takePhone() {
        PictureSelector.create(PersonalInfoActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .enableCrop(true)
                .withAspectRatio(1, 1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() != 0) {
                        mPresenter.uploadAvatar(selectList.get(0).getCutPath());
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(STORAGE_REQUEST_CODE)
    private void onPermissionResult() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            takePhone();
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.choose_phone_permission_tip),
                    STORAGE_REQUEST_CODE,
                    permissions
            );
        }
    }

    @OnClick({R.id.cl_personal_info_first_name, R.id.cl_personal_info_last_name,
            R.id.cl_personal_info_phone, R.id.cl_personal_info_avatar})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.cl_personal_info_first_name:
                startModifyActivity(Constant.MODIFY_USER_INFO_TYPE_FIRST_NAME);
                break;
            case R.id.cl_personal_info_last_name:
                startModifyActivity(Constant.MODIFY_USER_INFO_TYPE_LAST_NAME);
                break;
            case R.id.cl_personal_info_phone:
                startModifyActivity(Constant.MODIFY_USER_INFO_TYPE_PHONE);
                break;
            case R.id.cl_personal_info_avatar:
                onPermissionResult();
                break;
        }
    }
}
