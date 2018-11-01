package com.heyrent.app.renter.view.login;

import com.heyrent.app.renter.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess();

        void loginFailed(String message);
    }

    interface Presenter {
        void login(String email, String password);
    }
}
