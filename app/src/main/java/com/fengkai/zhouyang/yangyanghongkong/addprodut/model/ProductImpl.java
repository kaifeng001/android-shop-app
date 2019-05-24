package com.fengkai.zhouyang.yangyanghongkong.addprodut.model;

import com.fengkai.zhouyang.yangyanghongkong.db.ProductDbHelper;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.IProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProductModel {
    @Override
    public void insertData(Product product) {
        ProductDbHelper.getInstance().insertDb(product);
    }

    @Override
    public List<Product> queryAllData() {
        ArrayList<Product> products = ProductDbHelper.getInstance().queryAllProduct();
        return products;
    }
}
