package com.fengkai.zhouyang.yangyanghongkong.addprodut.presenter;

import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.AddProduct;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.fengkai.zhouyang.yangyanghongkong.db.DbHelper;
import com.fengkai.zhouyang.yangyanghongkong.db.ProductDatabase;

public class AddProductPresenter {
    private ProductImpl mBiz;
    private AddProduct mView;

    public AddProductPresenter(AddProduct view) {
        mBiz = new ProductImpl();
        mView = view;
    }

    public void insertData() {
        Product product = new Product();
        product.icon = mView.getProductIcon();
        product.title = mView.getProductTitle();
        product.price = mView.getProductPrice();
        product.num = mView.getProductNum();
        mBiz.insertData(product);
        Toast.makeText(MainAplication.getInstance().getApplicationContext(), "数据库个数" +
                ProductDatabase.queryCount(), Toast.LENGTH_SHORT).show();
    }
}
