package com.fengkai.zhouyang.yangyanghongkong.home.presenter;

import android.util.Log;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.home.port.IRecommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendPresenter {
    private IRecommend mRecommend;
    private ProductImpl mImp;
    private List<Product> mList = new ArrayList<>();
    private List<Product> mSelectProducts = new ArrayList<>();

    public RecommendPresenter(IRecommend recommend) {
        mImp = new ProductImpl();
        mRecommend = recommend;
    }

    public void setData() {
        List<Product> products = mImp.queryAllData();
        mList.clear();
        mList.addAll(products);
        mRecommend.initRecyclerView(products);
    }

    public boolean isUseBackKeyEvent() {
        if (mRecommend.getEditState()) {
            mRecommend.exitEditState();
            mSelectProducts.clear();
            return true;
        } else {
            return false;
        }
    }

    public void joinEditState(int position) {
        mRecommend.joinEditState();
        Product product = mList.get(position);
        mSelectProducts.add(product);
    }

    public void dealCheckListener(int position, boolean isChecked) {
        if (isChecked) {
            mSelectProducts.add(mList.get(position));
        } else {
            mSelectProducts.remove(mList.get(position));
        }
        int size = mSelectProducts.size();
        if (size == 1) {
            mRecommend.productIsEdited(true);
        } else {
            mRecommend.productIsEdited(false);
        }
    }

    public void deleteSelectProduct() {
        int size = mSelectProducts.size();
        for (int i = 0; i < size; i++) {
            Product product = mSelectProducts.get(i);
            int id = product.id;
            mImp.deleteProductById(id);
            mList.remove(product);
        }
    }
}
