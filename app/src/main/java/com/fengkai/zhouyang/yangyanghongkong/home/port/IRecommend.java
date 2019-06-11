package com.fengkai.zhouyang.yangyanghongkong.home.port;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;

import java.util.List;

public interface IRecommend {
    void refreshView(List<Product> products);

    void joinEditState();

    void exitEditState();

    void initRecyclerView(List<Product> products);

    boolean getEditState();

    void productIsEdited(boolean isCanEdited);

    void removeItem(int position, List<Product> data);

    int getType();
}
