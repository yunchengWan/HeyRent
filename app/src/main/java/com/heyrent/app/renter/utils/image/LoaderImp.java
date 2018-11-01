package com.heyrent.app.renter.utils.image;

import android.widget.ImageView;

public interface LoaderImp {

    void load(String url, ImageView target);

    void loadAsCircle(String url, ImageView target);
}
