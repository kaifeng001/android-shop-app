package com.fengkai.zhouyang.yangyanghongkong.application;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class MainAplication extends Application {
    public static MainAplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static MainAplication getInstance() {
        return sInstance;
    }
}
