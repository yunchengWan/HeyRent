package com.heyrent.app.renter.view.login;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment implements SignUpContract.View {

    @BindView(R.id.et_sign_up_first_name)
    EditText mFirstNameEt;
    @BindView(R.id.underline_et_first_name)
    View mFirstNameUnderline;
    @BindView(R.id.et_sign_up_last_name)
    EditText mLastNameEt;
    @BindView(R.id.underline_et_last_name)
    View mLastNameUnderline;
    @BindView(R.id.et_sign_up_email)
    EditText mEmailEt;
    @BindView(R.id.underline_sign_up_et_email)
    View mEmailUnderline;
    @BindView(R.id.et_sign_up_password)
    EditText mPasswordEt;
    @BindView(R.id.underline_sign_up_et_password)
    View mPasswordUnderline;
    @BindView(R.id.tv_sign_up_btn)
    View mSignupBtn;
    @BindView(R.id.iv_sign_up_switch_password)
    View mPasswordSwitch;

    @Inject
    SignUpPresenter mPresenter;


    @Override
    protected int layout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }

    @Override
    protected void initData() {
        getLifecycle().addObserver(mPresenter);
        mPresenter.takeView(this);
    }

    @Override
    protected void initView() {
        mFirstNameEt.setOnFocusChangeListener(onFocusChangeListener);
        mFirstNameEt.addTextChangedListener(textWatcher);
        mLastNameEt.setOnFocusChangeListener(onFocusChangeListener);
        mLastNameEt.addTextChangedListener(textWatcher);
        mEmailEt.setOnFocusChangeListener(onFocusChangeListener);
        mEmailEt.addTextChangedListener(textWatcher);
        mPasswordEt.setOnFocusChangeListener(onFocusChangeListener);
        mPasswordEt.addTextChangedListener(textWatcher);
    }

    private void checkStatus() {
        String firstName = mFirstNameEt.getText().toString().trim();
        String lastName = mLastNameEt.getText().toString().trim();
        String email = mEmailEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();

        //设置注册按钮状态
        mSignupBtn.setEnabled(!TextUtils.isEmpty(firstName) &&
                !TextUtils.isEmpty(lastName) &&
                !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(password));

        mPasswordSwitch.setVisibility(TextUtils.isEmpty(password) ? View.GONE : View.VISIBLE);
    }

    private void signUp() {
        String firstName = mFirstNameEt.getText().toString().trim();
        String lastName = mLastNameEt.getText().toString().trim();
        String email = mEmailEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();

        mPresenter.signUp(email, password, firstName, lastName);
    }

    @Override
    public void onSignupSuccess() {
        if (getActivity() == null) {
            return;
        }
        showToast(R.string.login_success);
        getActivity().finish();
    }

    @Override
    public void onSignupFailed(String message) {
        //TODO nothing
        showToast(message);
    }

    @OnClick({R.id.tv_sign_up_btn, R.id.iv_sign_up_switch_password})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_up_btn:
                //sign up
                signUp();
                break;
            case R.id.iv_sign_up_switch_password:
                if (mPasswordEt.getTransformationMethod() instanceof HideReturnsTransformationMethod) {
                    mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (mPasswordEt.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if (!TextUtils.isEmpty(mPasswordEt.getText().toString())) {
                    mPasswordEt.setSelection(mPasswordEt.getText().toString().length());
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
            checkStatus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnFocusChangeListener onFocusChangeListener = (v, hasFocus) -> {
        if (getContext() == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.et_sign_up_first_name:
                mFirstNameUnderline.setBackgroundColor(
                        hasFocus ?
                                ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                ContextCompat.getColor(getContext(), R.color.colorEditBg));
                break;
            case R.id.et_sign_up_last_name:
                mLastNameUnderline.setBackgroundColor(
                        hasFocus ?
                                ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                ContextCompat.getColor(getContext(), R.color.colorEditBg));
                break;
            case R.id.et_sign_up_email:
                mEmailUnderline.setBackgroundColor(
                        hasFocus ?
                                ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                ContextCompat.getColor(getContext(), R.color.colorEditBg));
                break;
            case R.id.et_sign_up_password:
                mPasswordUnderline.setBackgroundColor(
                        hasFocus ?
                                ContextCompat.getColor(getContext(), R.color.colorPrimaryBlue) :
                                ContextCompat.getColor(getContext(), R.color.colorEditBg));
                break;
        }

    };
}
