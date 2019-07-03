package com.fengkai.zhouyang.yangyanghongkong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fengkai.zhouyang.yangyanghongkong.R;

public class AddSettingView extends LinearLayout {
    private EditText mEditText;

    public AddSettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_setting, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.addSettingView);
        String hint = typedArray.getString(R.styleable.addSettingView_hint);
        boolean isDivider = typedArray.getBoolean(R.styleable.addSettingView_isDivider, true);
        String title = typedArray.getString(R.styleable.addSettingView_title);
        int inputType = typedArray.getInt(R.styleable.addSettingView_inputType, InputType.TYPE_CLASS_TEXT);

        mEditText = inflate.findViewById(R.id.setting_edit);
        mEditText.setInputType(inputType);
        mEditText.setHint(hint);
        inflate.findViewById(R.id.setting_line).setVisibility(isDivider ? View.VISIBLE : View.GONE);
        TextView titleText = inflate.findViewById(R.id.setting_title);
        titleText.setText(title);
    }

    public String getEditHint(){
        return mEditText.getHint().toString();
    }

    public String getEditText(){
        return mEditText.getText().toString();
    }
}
