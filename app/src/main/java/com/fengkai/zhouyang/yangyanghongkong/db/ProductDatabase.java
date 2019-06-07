package com.fengkai.zhouyang.yangyanghongkong.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.home.adapter.RecommendAdapter;

import java.util.ArrayList;

public class ProductDatabase {
    public static void insertDb(Product product) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", product.title);
        cv.put("price", product.price);
        cv.put("num", product.num);
        cv.put("icon", product.icon);
        db.insert("product", null, cv);
    }

    public static int deleteById(int id) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        return db.delete("product", "productId=?", new String[]{String.valueOf(id)});
    }

    public static int updateById(Product pro, int id) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("title", pro.title);
        value.put("price", pro.price);
        value.put("num", pro.num);
        value.put("icon", pro.icon);
        return db.update("product", value, "productId=?", new String[]{String.valueOf(id)});
    }

    public static ArrayList<Product> queryAllProduct() {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        ArrayList<Product> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.id = cursor.getInt(cursor.getColumnIndex("productId"));
            product.title = cursor.getString(cursor.getColumnIndex("title"));
            product.price = cursor.getString(cursor.getColumnIndex("price"));
            product.num = cursor.getString(cursor.getColumnIndex("num"));
            product.icon = cursor.getString(cursor.getColumnIndex("icon"));
            product.type = RecommendAdapter.PRODUCT_TYPE;
            list.add(product);
        }
        return list;
    }

    public static int queryCount() {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        return cursor.getCount();
    }
}
