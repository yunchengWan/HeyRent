package com.heyrent.app.renter.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T extends BaseView> implements LifecycleObserver {

    protected List<Disposable> mRequestList = new ArrayList<>();

    protected abstract void takeView(T view);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected abstract void dropView();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void cancelRequest() {
        for (Disposable d : mRequestList) {
            if (d != null && !d.isDisposed()) {
                d.dispose();
            }
        }
        mRequestList.clear();
    }

    protected void addRequest(Disposable d) {
        mRequestList.add(d);
    }
}
