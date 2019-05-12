package com.example.ex_fengkai001.recyclerviewdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ex_fengkai001.recyclerviewdemo.R;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.CenterFragment;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.HomeFragment;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.RecommendFragment;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.SearchFragment;
import com.example.ex_fengkai001.recyclerviewdemo.fragment.TalkFragment;
import com.example.ex_fengkai001.recyclerviewdemo.view.MainBottomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private List<Fragment> mList;
    private MainBottomView mBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        initView();
        initListener();
    }

    private void initListener() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewpager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        mViewpager.setOffscreenPageLimit(5);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mBottom.select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mBottom.setViewPager(mViewpager);
    }

    private void initView() {
        initBottom();
        mBottom = findViewById(R.id.main_bottom);
        mViewpager = findViewById(R.id.viewpager);
        mList = new ArrayList<>();
        mList.add(new HomeFragment());
        mList.add(new RecommendFragment());
        mList.add(new SearchFragment());
        mList.add(new TalkFragment());
        mList.add(new CenterFragment());
    }

    private void initBottom() {

    }
}
