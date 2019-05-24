package com.fengkai.zhouyang.yangyanghongkong.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;

import java.util.ArrayList;

public class ProductDbHelper extends SQLiteOpenHelper {//1.新建类继承SQLiteOpenHelper

    private Context context;//上下文
    private static ProductDbHelper mInstance;
    public static final String PRODUCT = "create table product ("
            + "id integer primary key autoincrement," + "title text,"
            + "price text," + "num text," + "icon text)";

    public ProductDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //int version-当前数据库的版本号，可用于对数据库进行升级操作
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PRODUCT);//执行建表语句，创建数据库
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static ProductDbHelper getInstance() {
        if (mInstance == null) {
            synchronized (ProductDbHelper.class) {
                if (mInstance == null) {
                    mInstance = new ProductDbHelper(MainAplication.getInstance().getApplicationContext(), "yangyangProduct.db", null, 1);
                }
            }
        }
        return mInstance;
    }

    public void insertDb(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", product.title);
        cv.put("price", product.price);
        cv.put("num", product.num);
        cv.put("icon", product.icon);
        db.insert("product", null, cv);
    }

    public int deleteById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("product", "_id=?", new String[] { String.valueOf(id) });
    }

    public int updateById(Product pro,int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("title", pro.title);
        value.put("price", pro.price);
        value.put("num", pro.num);
        value.put("icon", pro.icon);
        return db.update("product", value, "_id=?", new String[] { String.valueOf(id) });
    }

    public ArrayList<Product> queryAllProduct() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        ArrayList<Product> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.id = cursor.getInt(cursor.getColumnIndex("id"));
            product.title = cursor.getString(cursor.getColumnIndex("title"));
            product.price = cursor.getString(cursor.getColumnIndex("price"));
            product.num = cursor.getString(cursor.getColumnIndex("num"));
            product.icon = cursor.getString(cursor.getColumnIndex("icon"));
            list.add(product);
        }
        return list;
    }

    public int queryCount() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        return cursor.getCount();
    }
}
