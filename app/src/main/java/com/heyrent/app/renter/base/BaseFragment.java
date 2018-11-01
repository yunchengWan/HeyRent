package com.heyrent.app.renter.base;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.utils.ScreenUtils;
import com.heyrent.app.renter.widget.toolbar.statusbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private LinearLayout mContainerLl;
    private Unbinder mUnbinder;
    private View mStatusBarHolderView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layouy_base_container, container, false);
        initContainer(root);
        //加载内容布局（不包括statusBar和TitleBar）
        inflater.inflate(layout(), mContainerLl, true);
        mUnbinder = ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
    }

    private void initContainer(View root) {
        //设置status bar高度的margin
        mContainerLl = root.findViewById(R.id.ll_base_container);
        if (needStatusBar() && getContext() != null) {

            //设置statusBarHolderView
            mStatusBarHolderView = new View(getContext());
            mStatusBarHolderView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusHeight(getContext())));
            ((LinearLayout) root).addView(mStatusBarHolderView);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0, ScreenUtils.getStatusHeight(getContext()), 0, 0);
            mContainerLl.setLayoutParams(params);
        }
    }

    protected void setStatusBarColor(@ColorRes int color) {
        if (getContext() == null) {
            return;
        }
        mStatusBarHolderView.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int layout();

    protected abstract boolean needStatusBar();

    protected abstract void initData();

    protected abstract void initView();

}
