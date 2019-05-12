package com.example.ex_fengkai001.recyclerviewdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex_fengkai001.recyclerviewdemo.R;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter {
    private List<Integer> mList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        productHolder.mIcon.setImageResource(mList.get(position));
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

    public RecommendAdapter(List<Integer> list) {
        mList = list;
    }
}
