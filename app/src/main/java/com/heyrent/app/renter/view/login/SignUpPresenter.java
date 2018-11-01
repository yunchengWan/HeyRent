package com.heyrent.app.renter.view.login;

import com.heyrent.app.renter.base.BasePresenter;
import com.heyrent.app.renter.model.UserRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class SignUpPresenter extends BasePresenter<SignUpContract.View> implements SignUpContract.Presenter {
    private SignUpContract.View mView;
    @Inject
    UserRepository mRepository;

    @Inject
    public SignUpPresenter() {
    }

    @Override
    protected void takeView(SignUpContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void signUp(String email, String password, String firstName, String lastName) {
        Disposable d = mRepository.signUp(email, password, firstName, lastName)
                .subscribe(
                        userLoginInfo -> {
                            if (userLoginInfo.isSuccess()) {
                                mView.onSignupSuccess();
                            } else {
                                mView.onSignupFailed(userLoginInfo.getMessage());
                            }
                        },
                        throwable -> {
                            mView.onSignupFailed(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
        mRequestList.add(d);
    }
}
