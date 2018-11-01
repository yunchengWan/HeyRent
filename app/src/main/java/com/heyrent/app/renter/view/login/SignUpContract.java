package com.heyrent.app.renter.view.login;

import com.heyrent.app.renter.base.BaseView;

public interface SignUpContract {
    interface View extends BaseView {
        void onSignupSuccess();

        void onSignupFailed(String message);
    }

    interface Presenter {

        void signUp(String email, String password, String firstName, String lastName);
    }
}
