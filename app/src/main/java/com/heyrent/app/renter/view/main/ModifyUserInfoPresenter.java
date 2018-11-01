package com.heyrent.app.renter.view.main;

import com.heyrent.app.renter.base.BasePresenter;
import com.heyrent.app.renter.model.UserRepository;
import com.heyrent.app.renter.utils.Constant;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ModifyUserInfoPresenter extends BasePresenter<ModifyUserInfoContract.View>
        implements ModifyUserInfoContract.Presenter {
    private ModifyUserInfoContract.View mView;

    @Inject
    UserRepository mRepository;
    private int mType = Constant.MODIFY_USER_INFO_TYPE_FIRST_NAME;

    @Inject
    public ModifyUserInfoPresenter() {
    }

    @Override
    protected void takeView(ModifyUserInfoContract.View view) {
        mView = view;
    }

    @Override
    protected void dropView() {
        mView = null;
    }

    @Override
    public void modify(String info) {
        String firstName = mType == Constant.MODIFY_USER_INFO_TYPE_FIRST_NAME ? info : "";
        String lastName = mType == Constant.MODIFY_USER_INFO_TYPE_LAST_NAME ? info : "";
        String phone = mType == Constant.MODIFY_USER_INFO_TYPE_PHONE ? info : "";
        Disposable d = mRepository.modifyUserInfo(firstName, lastName, phone, "")
                .subscribe(
                        s -> {
                            if (s.isSuccess()) {
                                mView.modifySuccess();
                            } else {
                                mView.modifyFailed(s.getMessage());
                            }
                        },
                        throwable -> mView.modifyFailed(throwable.getMessage())
                );
        addRequest(d);
    }

    @Override
    public void setType(int type) {
        mType = type;
    }
}
