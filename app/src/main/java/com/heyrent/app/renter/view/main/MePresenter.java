package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BasePresenter;
import com.heyrent.app.renter.model.UserInfoBean;
import com.heyrent.app.renter.model.UserRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MePresenter extends BasePresenter<MeContract.View> implements MeContract.Presenter {
    private MeContract.View mView;
    @Inject
    UserRepository mUserRepository;

    @Inject
    public MePresenter() {
    }

    @Override
    protected void takeView(MeContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void getUserInfo() {
        Disposable d = mUserRepository.getUserInfo()
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                UserInfoBean.UserInfo baseInfo = response.getData().getUserInfo();
                                mView.showName(baseInfo.getFirstName() + " " + baseInfo.getLastName());
                            }
                        },
                        Throwable::printStackTrace
                );
        addRequest(d);
    }
}
