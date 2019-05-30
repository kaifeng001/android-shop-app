package com.fengkai.zhouyang.yangyanghongkong.addprodut.port;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;

import java.util.List;

public interface IProductModel {
    void insertData(Product product);

    List<Product> queryAllData();

    void deleteProductById(int id);
}
