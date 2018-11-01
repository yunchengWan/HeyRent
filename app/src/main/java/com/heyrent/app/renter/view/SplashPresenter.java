package com.heyrent.app.renter.view;


import com.heyrent.app.renter.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private SplashContract.View mView;

    @Inject
    public SplashPresenter() {
    }

    @Override
    protected void takeView(SplashContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void startCountDown() {
        Disposable disposable = Single.just(1)
                .delay(3, TimeUnit.SECONDS)
                .subscribe(
                        integer -> {
                            if (mView != null) {
                                mView.gotoMainActivity();
                            }
                        },
                        error -> {
                        }
                );
    }
}
