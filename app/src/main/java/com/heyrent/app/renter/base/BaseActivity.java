package com.heyrent.app.renter.base;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.utils.KeyboardChangeListener;
import com.heyrent.app.renter.utils.ScreenUtils;
import com.heyrent.app.renter.widget.toolbar.statusbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private LinearLayout mContainerLl;
    private ImmersionBar mImmersionBar;
    private Unbinder mUnbinder;

    private ImageView mLeftIv;
    private TextView mTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouy_base_container);

        initContainer();
        initStatusBar();

        //加载titleBar
        if (needTitleBar()) {
            LayoutInflater.from(this).inflate(R.layout.layout_titlebar, mContainerLl, true);
            mLeftIv = findViewById(R.id.iv_title_bar_left);
            mTitleTv = findViewById(R.id.tv_title_bar_title);
            mRightIv = findViewById(R.id.iv_title_bar_right);
            mRightTv = findViewById(R.id.tv_title_bar_right);
        }

        //加载内容布局（不包括statusBar和TitleBar）
        LayoutInflater.from(this).inflate(layout(), mContainerLl, true);

        mUnbinder = ButterKnife.bind(this);

        //键盘监听
        if (needListenerKeyboard()) {
            new KeyboardChangeListener(this)
                    .setKeyBoardListener(this::onKeyboardChanged);
        }

        initData();
        initView();
    }

    private void initContainer() {
        //设置status bar高度的margin
        mContainerLl = findViewById(R.id.ll_base_container);
        if (needStatusBar()) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0, ScreenUtils.getStatusHeight(this), 0, 0);
            mContainerLl.setLayoutParams(params);
        }
    }

    //初始化statusBar默认黑色主题
    private void initStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        //设置状态栏透明
        mImmersionBar.statusBarColor("#00000000");
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        mImmersionBar.destroy();
        super.onDestroy();
    }

    /**
     * 状态栏字体 白色 or 黑色
     */
    protected void setStatusBarDark(boolean isDark) {
        mImmersionBar.statusBarDarkFont(isDark);
        mImmersionBar.init();
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor(@ColorRes int color) {
        mImmersionBar.statusBarColorInt(ContextCompat.getColor(this, color));
        mImmersionBar.init();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void setTitleText(@StringRes int title) {
        mTitleTv.setText(title);
    }

    protected void setTitleText(String title) {
        mTitleTv.setText(title);
    }

    protected void setLeftIcon(@DrawableRes int drawable) {
        mLeftIv.setImageResource(drawable);
    }

    protected void setLeftIconListener(View.OnClickListener listener) {
        mLeftIv.setOnClickListener(listener);
    }

    protected void setRightIcon(@DrawableRes int drawable) {
        mRightIv.setImageResource(drawable);
    }

    protected void setRightIconListener(View.OnClickListener listener) {
        mRightIv.setOnClickListener(listener);
    }

    protected void setRightText(@StringRes int text) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(text);
    }

    protected void setRightText(String text) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(text);
    }

    protected void setRightTextListener(View.OnClickListener listener) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setOnClickListener(listener);
    }

    /**
     * 是否需要监听键盘状态
     */
    protected boolean needListenerKeyboard() {
        return false;
    }

    /**
     * 键盘状态变化时回调
     */
    protected void onKeyboardChanged(boolean isShow, int keyboardHeight) {

    }

    /**
     * 是否需要TitleBar
     */
    protected boolean needTitleBar() {
        return false;
    }


    @LayoutRes
    protected abstract int layout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract boolean needStatusBar();
}
