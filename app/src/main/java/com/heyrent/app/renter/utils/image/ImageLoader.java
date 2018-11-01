package com.heyrent.app.renter.utils.image;

import android.widget.ImageView;

public class ImageLoader implements LoaderImp {
    private LoaderImp mLoader;

    public ImageLoader(LoaderImp loader) {
        this.mLoader = loader;
    }

    @Override
    public void load(String url, ImageView target) {
        mLoader.load(url, target);
    }

    @Override
    public void loadAsCircle(String url, ImageView target) {
        mLoader.loadAsCircle(url, target);
    }
}
