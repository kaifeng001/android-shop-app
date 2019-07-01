package com.fengkai.zhouyang.yangyanghongkong.home.presenter;


import android.content.Context;
import android.content.DialogInterface;

import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.config.Config;
import com.fengkai.zhouyang.yangyanghongkong.constant.Constant;
import com.fengkai.zhouyang.yangyanghongkong.home.adapter.RecommendAdapter;
import com.fengkai.zhouyang.yangyanghongkong.home.port.IRecommend;
import com.fengkai.zhouyang.yangyanghongkong.view.EditProductDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecommendPresenter implements DialogInterface.OnDismissListener {
    private IRecommend mRecommend;
    private ProductImpl mImp;
    private List<Product> mList = new ArrayList<>();
    private HashMap<Integer, Product> mSelects = new HashMap<>();
    private EditProductDialog mEditDialog;

    public RecommendPresenter(IRecommend recommend) {
        mImp = new ProductImpl();
        mRecommend = recommend;
    }

    public void setData() {
        List<Product> products = mImp.queryAllData();
        mList.clear();
        mList.addAll(products);
        mRecommend.initRecyclerView(mList);
    }

    public boolean isBackEditState() {
        if (mRecommend.getEditState()) {
            mRecommend.exitEditState();
            mSelects.clear();
            return true;
        } else {
            return false;
        }
    }

    public void joinEditState(int position) {
        mRecommend.joinEditState();
        Product product = mList.get(position);
        mSelects.put(position, product);
    }

    public void dealCheckListener(int position, boolean isChecked) {
        if (isChecked) {
            mSelects.put(position, mList.get(position));
        } else {
            mSelects.remove(position);
        }
        int size = mSelects.size();
        if (size == 1) {
            mRecommend.productIsEdited(true);
        } else {
            mRecommend.productIsEdited(false);
        }
    }

    public void deleteSelectProduct() {
        Iterator<Map.Entry<Integer, Product>> iterator = mSelects.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> next = iterator.next();
            int position = next.getKey();
            Product product = next.getValue();
            mImp.deleteProductById(product.id);
            mList.remove(product);
            mRecommend.removeItem(position, mList);
        }
        if (mList.size() == 1) {
            mRecommend.exitEditState();
        }
    }

    public void dealEditCLick(Context context, EditProductDialog.OnIconSelectClickListener listener) {
        Iterator<Map.Entry<Integer, Product>> iterator = mSelects.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<Integer, Product> next = iterator.next();
            Product product = next.getValue();
            mEditDialog = new EditProductDialog(context, product);
            mEditDialog.setIconSelectListener(listener);
            mEditDialog.setOnDismissListener(this);
            mEditDialog.show();
        }

    }

    public void showSelectIconEdit(String path) {
        mEditDialog.showSelectIcon(path);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        setData();
        mRecommend.exitEditState();
        mSelects.clear();
    }
}
