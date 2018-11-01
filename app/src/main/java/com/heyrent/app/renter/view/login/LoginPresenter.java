package com.heyrent.app.renter.view.login;

import com.heyrent.app.renter.base.BasePresenter;
import com.heyrent.app.renter.model.UserLoginInfo;
import com.heyrent.app.renter.model.UserRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private LoginContract.View mView;

    @Inject
    UserRepository mRepository;

    @Inject
    public LoginPresenter() {
    }

    @Override
    protected void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void login(String email, String password) {
        Disposable d = mRepository.login(email, password)
                .subscribe(
                        userLoginInfo -> {
                            if (userLoginInfo.isSuccess()) {
                                mView.loginSuccess();
                            } else {
                                mView.loginFailed(userLoginInfo.getMessage());
                            }
                        },
                        throwable -> {
                            mView.loginFailed(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
        mRequestList.add(d);
    }
}
