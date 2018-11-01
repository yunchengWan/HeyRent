package com.heyrent.app.renter.view.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseFragment;
import com.heyrent.app.renter.utils.LoginManager;
import com.heyrent.app.renter.utils.image.ImageLoader;
import com.heyrent.app.renter.view.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment implements MeContract.View {

    @BindView(R.id.tv_user_name)
    TextView mNameTv;
    @BindView(R.id.iv_user_header)
    ImageView mAvatarIv;

    @Inject
    LoginManager mLoginManager;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    MePresenter mPresenter;

    @Override
    protected int layout() {
        return R.layout.fragment_me;
    }

    @Override
    protected boolean needStatusBar() {
        return true;
    }

    @Override
    protected void initData() {
        mPresenter.takeView(this);
        getLifecycle().addObserver(mPresenter);
        mPresenter.getUserInfo();
    }

    @Override
    protected void initView() {
        setStatusBarColor(R.color.colorWhite);

        //TODO 测试图片
        mImageLoader.loadAsCircle("http://www.qqkuyou.com/uploads/71f459727f5055a0/8.jpg", mAvatarIv);
    }

    @Override
    public void showName(String name) {
        mNameTv.setText(name);
    }

    @OnClick({R.id.cl_me_my_profile, R.id.iv_title_bar_right, R.id.tv_me_edit_personal_info})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.cl_me_my_profile:
                //TODO
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.iv_title_bar_right:
                break;
            case R.id.tv_me_edit_personal_info:
                startActivity(new Intent(getContext(), PersonalInfoActivity.class));
                break;
        }
    }
}
