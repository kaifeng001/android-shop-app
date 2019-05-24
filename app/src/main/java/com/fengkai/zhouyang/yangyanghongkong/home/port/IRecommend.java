package com.fengkai.zhouyang.yangyanghongkong.home.port;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;

import java.util.List;

public interface IRecommend {
    void refreshView();

    void joinEditState();

    void exitEditState();

    void initRecyclerView(List<Product> products);

    boolean getEditState();
}
