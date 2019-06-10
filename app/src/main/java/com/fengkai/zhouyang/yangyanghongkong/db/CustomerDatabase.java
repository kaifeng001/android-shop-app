package com.fengkai.zhouyang.yangyanghongkong.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.fengkai.zhouyang.yangyanghongkong.customer.model.Customer;

import java.util.ArrayList;

public class CustomerDatabase {
    public static void insertDb(Customer customer) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", customer.name);
        cv.put("weixin", customer.weixin);
        cv.put("address", customer.address);
        cv.put("phone", customer.phone);
        db.insert("customer", null, cv);
    }

    public static int deleteById(int id) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        return db.delete("customer", "customerId=?", new String[]{String.valueOf(id)});
    }

    public static int updateById(Customer customer, int id) {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name", customer.name);
        value.put("address", customer.address);
        value.put("weixin", customer.weixin);
        value.put("phone", customer.phone);
        return db.update("customer", value, "customerId=?", new String[]{String.valueOf(id)});
    }

    public static ArrayList<Customer> queryAllProduct() {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query("customer", null, null, null, null, null, null);
        ArrayList<Customer> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Customer customer = new Customer();
            customer.id = cursor.getInt(cursor.getColumnIndex("customerId"));
            customer.name = cursor.getString(cursor.getColumnIndex("name"));
            customer.address = cursor.getString(cursor.getColumnIndex("address"));
            customer.weixin = cursor.getString(cursor.getColumnIndex("weixin"));
            customer.phone = cursor.getString(cursor.getColumnIndex("phone"));
            list.add(customer);
        }
        return list;
    }

    public static int queryCount() {
        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query("customer", null, null, null, null, null, null);
        return cursor.getCount();
    }
}
