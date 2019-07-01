package com.fengkai.zhouyang.yangyanghongkong.addprodut.model;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.IProductModel;
import com.fengkai.zhouyang.yangyanghongkong.db.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProductModel {
    @Override
    public void insertData(Product product) {
        ProductDatabase.getInstance().insertDb(product);
    }

    @Override
    public List<Product> queryAllData() {
        List<Product> products = ProductDatabase.getInstance().queryAllProduct();
        if (products == null){
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public void deleteProductById(long id) {
        ProductDatabase.getInstance().deleteById(id);
    }

    @Override
    public void updateProduct(long id, Product product) {
        product.id = id;
        ProductDatabase.getInstance().updateDb(product);
    }
}
