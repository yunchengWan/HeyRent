package com.heyrent.app.renter.utils;

import android.text.TextUtils;

import com.heyrent.app.renter.model.UserInfoBean;
import com.heyrent.app.renter.model.UserLoginInfo;

public class LoginManager {

    private String mToken;
    private String mRefreshToken;
    private UserInfoBean mUserInfo;

    private SharedPreferenceManager mPreferenceManager;

    public LoginManager(SharedPreferenceManager mPreferenceManager) {
        this.mPreferenceManager = mPreferenceManager;
    }

    public void init() {
        mToken = mPreferenceManager.getString(Constant.S_KEY_TOKEN);
        mRefreshToken = mPreferenceManager.getString(Constant.S_KEY_REFRESH_TOKEN);
    }

    public void onLoginSuccess(UserLoginInfo userLoginInfo) {
        if (userLoginInfo.getData() == null) {
            LogUtil.e(Constant.LOGIN_TAG, "user info data is null");
            return;
        }
        mToken = userLoginInfo.getData().getValue();
        mRefreshToken = userLoginInfo.getData().getRefreshToken().getValue();

        saveTokenToLocal();
    }

    public void updateToken(String token, String refreshToken) {
        mToken = token;
        mRefreshToken = refreshToken;

        saveTokenToLocal();
    }

    private void saveTokenToLocal() {
        mPreferenceManager.putString(Constant.S_KEY_TOKEN, mToken);
        mPreferenceManager.putString(
                Constant.S_KEY_REFRESH_TOKEN,
                mRefreshToken
        );
    }

    public String getToken() {
        return mToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public UserInfoBean getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfoBean mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(mToken) && !TextUtils.isEmpty(mRefreshToken);
    }
}
