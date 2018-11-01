package com.heyrent.app.renter.di;

import com.heyrent.app.renter.view.login.LoginFragment;
import com.heyrent.app.renter.view.login.SignUpFragment;
import com.heyrent.app.renter.view.main.CollectionFragment;
import com.heyrent.app.renter.view.main.DiscoveryFragment;
import com.heyrent.app.renter.view.main.MeFragment;
import com.heyrent.app.renter.view.main.MessageFragment;
import com.heyrent.app.renter.view.main.WorkbenchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();

    @ContributesAndroidInjector
    abstract DiscoveryFragment discoveryFragment();

    @ContributesAndroidInjector
    abstract CollectionFragment collectionFragment();

    @ContributesAndroidInjector
    abstract WorkbenchFragment workbenchFragment();

    @ContributesAndroidInjector
    abstract MessageFragment messageFragment();

    @ContributesAndroidInjector
    abstract MeFragment meFragment();
}
