package com.example.ex_fengkai001.recyclerviewdemo.application;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }
}
