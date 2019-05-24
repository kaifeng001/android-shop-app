package com.fengkai.zhouyang.yangyanghongkong.home.presenter;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.home.port.IRecommend;

import java.util.List;

public class RecommendPresenter {
    private IRecommend mRecommend;
    private ProductImpl mImp;

    public RecommendPresenter(IRecommend recommend) {
        mImp = new ProductImpl();
        mRecommend = recommend;
    }

    public void setData() {
        List<Product> products = mImp.queryAllData();
        mRecommend.initRecyclerView(products);
    }

    public boolean isUseBackKeyEvent() {
        if (mRecommend.getEditState()) {
            mRecommend.exitEditState();
            return true;
        } else {
            return false;
        }
    }
}
