package com.fengkai.zhouyang.yangyanghongkong.home.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter {

    private List<Product> mList = new ArrayList<>();


    public BannerAdapter(Activity activity) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate;
        inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        RecyclerView.ViewHolder holder = new ProductHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ProductHolder) {
            binderProduct((ProductHolder) holder, position);
        }
    }


    private void binderProduct(@NonNull ProductHolder holder, final int position) {
        final ProductHolder proHolder = holder;
        final Product product = mList.get(position);
        Glide.with(proHolder.itemView).load(product.icon).into(proHolder.mIcon);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ProductHolder extends RecyclerView.ViewHolder {
        public ImageView mIcon;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.pr_icon);
        }
    }

    public void setData(List<Product> list) {
        mList.clear();
        mList.addAll(list);
    }

}
