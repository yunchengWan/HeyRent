package com.heyrent.app.renter.view.login;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.et_login_email)
    EditText mEmailEt;
    @BindView(R.id.et_login_password)
    EditText mPsdEt;
    @BindView(R.id.tv_login_btn)
    View mLoginBtn;
    @BindView(R.id.underline_et_email)
    View mEmailUnderline;
    @BindView(R.id.underline_et_password)
    View mPasswordUnderline;
    @BindView(R.id.iv_switch_password)
    View mSwitchPassword;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected int layout() {
        return R.layout.fragment_login;
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }

    @Override
    protected void initData() {
        mPresenter.takeView(this);
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void initView() {
        mEmailEt.addTextChangedListener(textWatcher);
        mEmailEt.setOnFocusChangeListener(onFocusChangeListener);
        mPsdEt.addTextChangedListener(textWatcher);
        mPsdEt.setOnFocusChangeListener(onFocusChangeListener);
    }

    private void checkLoginStatus() {
        //登录按钮状态
        String email = mEmailEt.getText().toString().trim();
        String password = mPsdEt.getText().toString().trim();
        mLoginBtn.setEnabled(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password));

        //更改密码显示按钮可见
        mSwitchPassword.setVisibility(TextUtils.isEmpty(password) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        if (getActivity() == null) {
            return;
        }
        showToast(R.string.login_success);
        getActivity().finish();
    }

    @Override
    public void loginFailed(String message) {
        //TODO nothing
        showToast(message);
    }

    @OnClick({R.id.tv_login_btn, R.id.iv_switch_password})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_btn:
                mPresenter.login(mEmailEt.getText().toString().trim(),
                        mPsdEt.getText().toString().trim());
                break;
            case R.id.iv_switch_password:
                if (mPsdEt.getTransformationMethod() instanceof HideReturnsTransformationMethod) {
                    mPsdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (mPsdEt.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    mPsdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if (!TextUtils.isEmpty(mPsdEt.getText().toString())) {
                    mPsdEt.setSelection(mPsdEt.getText().toString().length());
                }
                break;
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkLoginStatus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnFocusChangeListener onFocusChangeListener =
            (v, hasFocus) -> {
                if (getContext() == null) {
                    return;
                }
                switch (v.getId()) {
                    case R.id.et_login_email:
                        mEmailUnderline.setBackgroundColor(
                                hasFocus ?
                                        ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                        ContextCompat.getColor(getContext(), R.color.colorEditBg));
                        break;
                    case R.id.et_login_password:
                        mPasswordUnderline.setBackgroundColor(
                                hasFocus ?
                                        ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                        ContextCompat.getColor(getContext(), R.color.colorEditBg));
                        break;
                }
            };
}
