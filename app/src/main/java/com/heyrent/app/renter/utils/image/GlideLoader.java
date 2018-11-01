package com.heyrent.app.renter.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideLoader implements LoaderImp {
    private Context mContext;

    public GlideLoader(Context context) {
        this.mContext = context;
    }

    @Override
    public void load(String url, ImageView target) {
        Glide.with(mContext).load(url).into(target);
    }

    @Override
    public void loadAsCircle(String url, ImageView target) {
        Glide.with(mContext).load(url).apply(RequestOptions.circleCropTransform()).into(target);
    }

//      //设置图片圆角角度
//     RoundedCorners roundedCorners= new RoundedCorners(6);
//     //通过RequestOptions扩展功能
//     RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//
//     Glide.with(context).load(files.getFilePath()).apply(options).into(mUserPhoto);
}
