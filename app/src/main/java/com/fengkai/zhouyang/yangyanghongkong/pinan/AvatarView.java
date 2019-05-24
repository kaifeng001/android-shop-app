package com.fengkai.zhouyang.yangyanghongkong.pinan;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.fengkai.zhouyang.yangyanghongkong.R;

public class AvatarView extends ViewGroup {
    private float mOffset;

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = getContext().obtainStyledAttributes(attrs,
                R.styleable.avatar);
        mOffset = t.getDimension(R.styleable.avatar_offset, 18);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            if (child.getVisibility() == View.GONE) {
                width += mOffset;
                continue;
            }
            if (i == count - 1) {
                width += childWidth;
            } else {
                width += childWidth - mOffset;
            }
        }
        setMeasuredDimension(width, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView;
        int offsetWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            if (childView.getVisibility() == GONE) continue;
            int occupyWidth = childView.getMeasuredWidth();
            int occupyHeight = childView.getMeasuredHeight();
            int left = offsetWidth;
            int right = left + occupyWidth;
            left = offsetWidth;
            offsetWidth += occupyWidth - mOffset;
            childView.layout(left, 0, right, occupyHeight);

        }
    }

    public void setmOffset(float offset){
        mOffset = offset;
        invalidate();
    }
}
