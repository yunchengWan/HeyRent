package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BaseView;

public interface ModifyUserInfoContract {
    interface View extends BaseView {
        void modifySuccess();

        void modifyFailed(String message);
    }

    interface Presenter {
        void modify(String info);

        void setType(int type);
    }
}
