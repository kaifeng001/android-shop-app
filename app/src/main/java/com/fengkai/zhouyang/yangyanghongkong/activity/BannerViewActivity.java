package com.fengkai.zhouyang.yangyanghongkong.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.home.adapter.BannerAdapter;
import com.fengkai.zhouyang.yangyanghongkong.view.recycleview.carousel.CarouselLayoutManager;
import com.fengkai.zhouyang.yangyanghongkong.view.recycleview.carousel.CarouselZoomPostLayoutListener;
import com.fengkai.zhouyang.yangyanghongkong.view.recycleview.carousel.CenterScrollListener;

import java.util.List;

public class BannerViewActivity extends AppCompatActivity {
    private RecyclerView mList;
    private BannerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_view_layout);
        mList = findViewById(R.id.banner_list);

        initRecyclerView(mList, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true));

        ProductImpl mImp = new ProductImpl();
        List<Product> products = mImp.queryAllData();
        mAdapter.setData(products);
        mList.setAdapter(mAdapter);


    }

    public void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager) {
        mAdapter = new BannerAdapter(this);

        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        layoutManager.setMaxVisibleItems(2);

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(mAdapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());
    }
}
