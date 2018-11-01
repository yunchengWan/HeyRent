package com.heyrent.app.renter.view.main;

import android.text.TextUtils;
import android.widget.EditText;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseActivity;
import com.heyrent.app.renter.utils.Constant;

import javax.inject.Inject;

import butterknife.BindView;

public class ModifyUserInfoActivity extends BaseActivity implements ModifyUserInfoContract.View {

    @BindView(R.id.et_modify_userinfo)
    EditText mEditText;

    @Inject
    ModifyUserInfoPresenter mPresenter;

    @Override
    protected int layout() {
        return R.layout.activity_modify_userinfo;
    }

    @Override
    protected void initView() {
        setStatusBarColor(R.color.colorWhite);
        setTitleText("Modify");
        setRightText("OK");
        setRightTextListener(
                v -> {
                    if (!TextUtils.isEmpty(mEditText.getText().toString())) {
                        mPresenter.modify(mEditText.getText().toString());
                    }
                }
        );
    }

    @Override
    protected void initData() {
        mPresenter.takeView(this);
        getLifecycle().addObserver(mPresenter);

        mPresenter.setType(getIntent().getIntExtra(
                Constant.K_MODIFY_USER_INFO_TYPE,
                Constant.MODIFY_USER_INFO_TYPE_FIRST_NAME
        ));
    }

    @Override
    protected boolean needStatusBar() {
        return true;
    }

    @Override
    protected boolean needTitleBar() {
        return true;
    }

    @Override
    public void modifySuccess() {
        showToast(R.string.personal_info_modify_success);
        finish();
    }

    @Override
    public void modifyFailed(String message) {
        showToast(message);
    }
}
