package com.heyrent.app.renter.view.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseActivity;
import com.heyrent.app.renter.base.BaseFragment;
import com.heyrent.app.renter.widget.SlideViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_login)
    TextView mLoginTv;
    @BindView(R.id.tv_sign_up)
    TextView mSignUpTv;
    @BindView(R.id.vp_login)
    SlideViewPager mViewPager;
    @BindView(R.id.cl_login_logo)
    View mLogoRootView;
    @BindView(R.id.cl_login_root)
    View mRootView;

    private List<BaseFragment> mFragmentList;

    public static void startActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("data", bundle);
        context.startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setStatusBarColor(R.color.colorPrimaryBlue);
        setStatusBarDark(false);
        updateTextStatus(true);

        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new LoginFragment());
        mFragmentList.add(new SignUpFragment());
    }

    @Override
    protected boolean needStatusBar() {
        return true;
    }

    @Override
    protected boolean needListenerKeyboard() {
        return true;
    }

    @Override
    protected void onKeyboardChanged(boolean isShow, int keyboardHeight) {
        showTopLogo(isShow);
    }

    private void updateTextStatus(boolean isLogin) {
        if (isLogin) {
            mLoginTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            mLoginTv.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
            mSignUpTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            mSignUpTv.setTextColor(ContextCompat.getColor(this, R.color.colorWhite30));
        } else {
            mLoginTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            mLoginTv.setTextColor(ContextCompat.getColor(this, R.color.colorWhite30));
            mSignUpTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            mSignUpTv.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        }
    }

    public void showTopLogo(boolean isShow) {
        if (isShow) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRootView.getTranslationY(), -mLogoRootView.getHeight())
                    .setDuration(300);
            valueAnimator.addUpdateListener(
                    animation -> mRootView.setTranslationY((Float) animation.getAnimatedValue())
            );
            valueAnimator.start();
        } else {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRootView.getTranslationY(), 0)
                    .setDuration(300);
            valueAnimator.addUpdateListener(
                    animation -> mRootView.setTranslationY((Float) animation.getAnimatedValue())
            );
            valueAnimator.start();
        }
    }

    @OnClick({R.id.tv_login, R.id.tv_sign_up})
    void handlerClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                mViewPager.setCurrentItem(0);
                updateTextStatus(true);
                break;
            case R.id.tv_sign_up:
                mViewPager.setCurrentItem(1);
                updateTextStatus(false);
                break;
        }
    }

    private FragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mFragmentList == null ? null : mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList == null ? 0 : mFragmentList.size();
        }
    };
}
