package com.fengkai.zhouyang.yangyanghongkong.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;

public class DbHelper extends SQLiteOpenHelper {//1.新建类继承SQLiteOpenHelper

    private Context context;//上下文
    private static DbHelper mInstance;

    public static final String ODERS = "create table orders ("
            + "customerId integer primary key autoincrement," + "title text,"
            + "price text," + "num text," + "icon text)";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ODERS);
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public static DbHelper getInstance() {
        if (mInstance == null) {
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper(MainAplication.getInstance().getApplicationContext(), "yangyangProduct1.db", null, 1);
                }
            }
        }
        return mInstance;
    }

}
