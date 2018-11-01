package com.heyrent.app.renter.view;

import android.content.Intent;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseActivity;
import com.heyrent.app.renter.view.login.LoginActivity;
import com.heyrent.app.renter.view.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected int layout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        setStatusBarDark(false);
    }

    @Override
    protected void initData() {
        mPresenter.takeView(this);
        mPresenter.startCountDown();
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
