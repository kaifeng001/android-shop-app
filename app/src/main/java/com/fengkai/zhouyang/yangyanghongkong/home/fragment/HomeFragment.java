package com.fengkai.zhouyang.yangyanghongkong.home.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.constant.Constant;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public TabLayout mTab;
    public ViewPager mViewPager;
    private List<Fragment> mList;

    @Override
    public int setLayoutId() {
        return R.layout.layout_home;
    }

    @Override
    public void initView(View contentView) {
        initViewPager(contentView);
        initTabs(contentView);
    }

    private void initTabs(View contentView) {
        mTab = contentView.findViewById(R.id.home_tab);
        mTab.setupWithViewPager(mViewPager);
        for (int i = 0; i < Constant.HOME_TABS.length; i++) {
            TabLayout.Tab tabAt = mTab.getTabAt(i);
            tabAt.setText(Constant.HOME_TABS[i]);
        }
    }

    private void initViewPager(View contentView) {
        mViewPager = contentView.findViewById(R.id.home_viewpager);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        mList = new ArrayList<>();
        for (int i = 0; i < Constant.HOME_TABS.length; i++) {
            mList.add(new RecommendFragment(i));
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
