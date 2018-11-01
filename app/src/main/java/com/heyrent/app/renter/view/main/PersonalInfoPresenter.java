package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BasePresenter;
import com.heyrent.app.renter.model.UserRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class PersonalInfoPresenter extends BasePresenter<PersonalInfoContract.View>
        implements PersonalInfoContract.Presenter {
    private PersonalInfoContract.View mView;

    @Inject
    UserRepository mRepository;

    @Inject
    public PersonalInfoPresenter() {
    }

    @Override
    protected void takeView(PersonalInfoContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void uploadAvatar(String avatarPath) {
//        Disposable d = mRepository.getImageUploadUrl()
//                .subscribe(
//                        response -> {
//
//                        },
//                        throwable -> {
//
//                        }
//                );
//        addRequest(d);
        mRepository.uploadAvatar(avatarPath);
    }
}
