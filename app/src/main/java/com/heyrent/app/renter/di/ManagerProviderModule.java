package com.heyrent.app.renter.di;


import android.content.Context;

import com.heyrent.app.renter.net.HeyRentApi;
import com.heyrent.app.renter.net.RetrofitManager;
import com.heyrent.app.renter.utils.LoginManager;
import com.heyrent.app.renter.utils.SharedPreferenceManager;
import com.heyrent.app.renter.utils.image.GlideLoader;
import com.heyrent.app.renter.utils.image.ImageLoader;
import com.heyrent.app.renter.utils.image.LoaderImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ManagerProviderModule {

    @Singleton
    @Provides
    public HeyRentApi provideHeyRentApi(LoginManager loginManager) {
        return new RetrofitManager(loginManager).newApi();
    }

    @Singleton
    @Provides
    public SharedPreferenceManager provideSharedPreferenceManager(Context context) {
        return new SharedPreferenceManager(context);
    }

    @Singleton
    @Provides
    public LoginManager provideLoginManager(SharedPreferenceManager sharedPreferenceManager) {
        return new LoginManager(sharedPreferenceManager);
    }

    @Provides
    public LoaderImp provideLoader(Context context) {
        //用glide
        return new GlideLoader(context);
    }

    @Singleton
    @Provides
    public ImageLoader provideImageLoader(LoaderImp loader) {
        return new ImageLoader(loader);
    }
}
