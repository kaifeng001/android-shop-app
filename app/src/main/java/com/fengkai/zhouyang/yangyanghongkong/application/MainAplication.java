package com.fengkai.zhouyang.yangyanghongkong.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.speedystone.greendaodemo.db.DaoMaster;
import com.speedystone.greendaodemo.db.DaoSession;

import cn.jpush.android.api.JPushInterface;

public class MainAplication extends Application {
    private static MainAplication sInstance;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initJPush();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "yangyangProduct.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static MainAplication getInstance() {
        return sInstance;
    }

    public DaoSession getDaosession() {
        return mDaoSession;
    }
}
