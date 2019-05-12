package com.example.ex_fengkai001.recyclerviewdemo.pinan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ex_fengkai001.recyclerviewdemo.R;

public class ItemAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_product, parent, false);
        NormalHolder holder = new NormalHolder(inflate);
        return holder;
    }
    private class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
