package com.heyrent.app.renter.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SlideViewPager extends ViewPager {

    private boolean canSlide = false;

    public SlideViewPager(@NonNull Context context) {
        this(context, null);
    }

    public SlideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canSlide && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canSlide && super.onTouchEvent(ev);
    }

    public void canSlide(boolean canSlide) {
        this.canSlide = canSlide;
    }
}
