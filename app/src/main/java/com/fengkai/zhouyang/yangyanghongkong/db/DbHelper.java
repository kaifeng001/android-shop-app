package com.fengkai.zhouyang.yangyanghongkong.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.home.adapter.RecommendAdapter;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {//1.新建类继承SQLiteOpenHelper

    private Context context;//上下文
    private static DbHelper mInstance;
    public static final String PRODUCT = "create table product ("
            + "productId integer primary key autoincrement," + "title text,"
            + "price text," + "num text," + "icon text)";

    public static final String CUSTOMER = "create table customer ("
            + "customerId integer primary key autoincrement," + "name text,"
            + "weixin text," + "address text," + "phone text)";

    public static final String ODERS = "create table orders ("
            + "customerId integer primary key autoincrement," + "title text,"
            + "price text," + "num text," + "icon text)";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(PRODUCT);
        sqLiteDatabase.execSQL(CUSTOMER);
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
