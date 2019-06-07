package com.fengkai.zhouyang.yangyanghongkong.addprodut.model;

import com.fengkai.zhouyang.yangyanghongkong.db.DbHelper;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.IProductModel;
import com.fengkai.zhouyang.yangyanghongkong.db.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProductModel {
    @Override
    public void insertData(Product product) {
        ProductDatabase.insertDb(product);
    }

    @Override
    public List<Product> queryAllData() {
        ArrayList<Product> products = ProductDatabase.queryAllProduct();
        return products;
    }

    @Override
    public void deleteProductById(int id) {
        ProductDatabase.deleteById(id);
    }
}
