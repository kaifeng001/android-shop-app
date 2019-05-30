package com.fengkai.zhouyang.yangyanghongkong.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fengkai.zhouyang.yangyanghongkong.R;

public class BottomDialog extends Dialog {
    protected boolean mCancelable = true;
    protected View mView;
    protected Context mContext;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        mContext = context;
    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BottomDialog(Context context, View view, boolean isCancelable) {
        super(context, R.style.BottomDialogStyle);
        this.mContext = context;
        mView = view;
        mCancelable = isCancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        window.setWindowAnimations(R.style.BottomDialogAnimation);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            getWindow().setWindowAnimations(R.style.BottomDialogNullAnimation);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
                }
            },50);
        }
    }

}
