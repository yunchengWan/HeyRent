package com.heyrent.app.renter.view;

import com.heyrent.app.renter.base.BaseView;

public interface SplashContract {
    interface View extends BaseView {
        void gotoMainActivity();
    }

    interface Presenter {
        void startCountDown();
    }
}
