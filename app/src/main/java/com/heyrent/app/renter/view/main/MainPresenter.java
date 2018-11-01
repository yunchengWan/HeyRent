package com.heyrent.app.renter.view.main;


import com.heyrent.app.renter.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{
    private MainContract.View mView;

    @Inject
    public MainPresenter() {
    }

    @Override
    protected void takeView(MainContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }
}
