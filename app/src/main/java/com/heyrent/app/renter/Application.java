package com.heyrent.app.renter;

import com.heyrent.app.renter.di.DaggerAppComponent;
import com.heyrent.app.renter.utils.LoginManager;
import com.heyrent.app.renter.utils.SharedPreferenceManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class Application extends DaggerApplication {
    @Inject
    SharedPreferenceManager sharedPreferenceManager;
    @Inject
    LoginManager mLoginManager;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLoginManager.init();
    }
}
