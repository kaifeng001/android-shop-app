package com.fengkai.zhouyang.yangyanghongkong.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.fengkai.zhouyang.yangyanghongkong.constant.Constant;

public class SharePreferenceUtils {
    private static SharePreferenceUtils mInstance;
    private SharedPreferences.Editor mEdit;
    private SharedPreferences mSharePreference;

    public SharePreferenceUtils() {
        Context context = MainAplication.getInstance().getApplicationContext();
        mSharePreference = context.getSharedPreferences(Constant.SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
        mEdit = mSharePreference.edit();
    }

    public static SharePreferenceUtils getInstance() {
        if (mInstance == null) {
            synchronized (SharePreferenceUtils.class) {
                if (mInstance == null) {
                    mInstance = new SharePreferenceUtils();
                }
            }
        }
        return mInstance;
    }

    public void putString(String key, String value) {
        mEdit.putString(key, value);
        mEdit.commit();
    }

    public String getString(String key) {
        return mSharePreference.getString(key, "");
    }
}
