package com.heyrent.app.renter.di;

import com.heyrent.app.renter.view.SplashActivity;
import com.heyrent.app.renter.view.login.LoginActivity;
import com.heyrent.app.renter.view.main.MainActivity;
import com.heyrent.app.renter.view.main.ModifyUserInfoActivity;
import com.heyrent.app.renter.view.main.PersonalInfoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector
    abstract PersonalInfoActivity personalInfoActivity();

    @ContributesAndroidInjector
    abstract ModifyUserInfoActivity modifyUserInfoActivity();

}
