package com.example.ex_fengkai001.recyclerviewdemo.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex_fengkai001.recyclerviewdemo.R;
import com.example.ex_fengkai001.recyclerviewdemo.adapter.RecommendAdapter;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.base.BaseFragment;
import com.example.ex_fengkai001.recyclerviewdemo.recycleview.DividerGridItemDecoration;
import com.example.ex_fengkai001.recyclerviewdemo.utils.LibTools;

import java.util.ArrayList;


public class RecommendFragment extends BaseFragment {
    private RecyclerView mRecycler;
    private GridLayoutManager layoutManager;

    @Override
    public int setLayoutId() {
        return R.layout.layout_recommend;
    }

    @Override
    public void initView(View contentView) {
        mRecycler = contentView.findViewById(R.id.recommend_list);
        mRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new DividerGridItemDecoration(getContext(), LibTools.dp2px(2), R.color.divider));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.product1);
        list.add(R.drawable.product2);
        list.add(R.drawable.product3);
        list.add(R.drawable.product4);
        list.add(R.drawable.product5);
        list.add(R.drawable.product6);
        list.add(R.drawable.product1);
        list.add(R.drawable.product2);
        list.add(R.drawable.product3);
        list.add(R.drawable.product4);
        list.add(R.drawable.product5);
        list.add(R.drawable.product6);
        list.add(R.drawable.product1);
        list.add(R.drawable.product2);
        list.add(R.drawable.product3);
        list.add(R.drawable.product4);
        list.add(R.drawable.product5);
        list.add(R.drawable.product6);
        mRecycler.setAdapter(new RecommendAdapter(list));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
