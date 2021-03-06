package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BaseView;

public interface MeContract {
    interface View extends BaseView {
        void showName(String name);
    }

    interface Presenter {
        void getUserInfo();
    }
}
