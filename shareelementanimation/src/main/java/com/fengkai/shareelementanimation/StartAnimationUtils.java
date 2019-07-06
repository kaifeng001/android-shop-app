package com.fengkai.shareelementanimation;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


public class StartAnimationUtils {
    private Rect mRect;
    private boolean mIsStateBar;
    private View mView;

    public StartAnimationUtils(@NonNull Rect rect, @NonNull View view, boolean isHadStateBar) {
        mRect = rect;
        mIsStateBar = isHadStateBar;
        mView = view;
    }

    public StartAnimationUtils(@NonNull Rect rect, @NonNull View view) {
        this(rect, view, false);
    }

    public void enterAnimation(Runnable runnable) {
        int bottom = mRect.bottom;
        int left = mRect.left;
        int right = mRect.right;
        int top = mRect.top;
        float width = right - left;
        float height = top - bottom;
        int viewWidth = mView.getWidth();
        int viewHeight = mView.getHeight();
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams((int) width, (int) height);
        if (mIsStateBar) {
            params.topMargin = getStateBarHeight(mView.getContext()) + top;
        } else {
            params.topMargin = top;
        }
        mView.setLayoutParams(params);
        float scaleX = viewWidth / width;
        float scaleY = viewHeight / height;
        float transX = (viewWidth - left - width) / 2;
        float transY = (viewHeight - top - height) / 2;
        mView.animate().setDuration(300)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .translationX(transX)
                .translationY(transY)
                .withEndAction(runnable)
                .start();
    }

    public void exitAnimation(Runnable runnable) {
        mView.animate().setDuration(300)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .withEndAction(runnable)
                .start();
    }

    public void enterAnimation() {
        this.enterAnimation(null);
    }

    public void exitAnimation() {
        this.exitAnimation(null);
    }

    public static int getStateBarHeight(Context context) {
        int result = dp2px(20, context);
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dp2px(int dp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;
        return (int) (dp * (dpi / 160f) + 0.5f);
    }
}
