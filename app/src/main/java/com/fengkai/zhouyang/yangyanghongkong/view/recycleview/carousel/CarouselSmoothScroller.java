package com.fengkai.zhouyang.yangyanghongkong.view.recycleview.carousel;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Custom implementation of {@link androidx.recyclerview.widget.LinearSmoothScroller} that can work only with {@link CarouselLayoutManager}.
 *
 * @see CarouselLayoutManager
 */
public abstract class CarouselSmoothScroller extends LinearSmoothScroller {

    protected CarouselSmoothScroller(final Context context) {
        super(context);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public int calculateDyToMakeVisible(final View view, final int snapPreference) {
        final CarouselLayoutManager layoutManager = (CarouselLayoutManager) getLayoutManager();
        if (null == layoutManager || !layoutManager.canScrollVertically()) {
            return 0;
        }

        return layoutManager.getOffsetForCurrentView(view);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public int calculateDxToMakeVisible(final View view, final int snapPreference) {
        final CarouselLayoutManager layoutManager = (CarouselLayoutManager) getLayoutManager();
        if (null == layoutManager || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        return layoutManager.getOffsetForCurrentView(view);
    }
}