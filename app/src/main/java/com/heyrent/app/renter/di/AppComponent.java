package com.heyrent.app.renter.di;

import com.heyrent.app.renter.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        ManagerProviderModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(android.app.Application application);

        AppComponent build();
    }
}
