package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BaseView;

public class PersonalInfoContract {
    interface View extends BaseView {

    }

    interface Presenter {
        void uploadAvatar(String avatarPath);
    }
}
