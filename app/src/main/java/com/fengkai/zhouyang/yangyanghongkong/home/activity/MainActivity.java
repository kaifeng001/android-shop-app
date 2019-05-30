package com.fengkai.zhouyang.yangyanghongkong.home.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.CenterFragment;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.HomeFragment;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.RecommendFragment;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.SearchFragment;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.TalkFragment;
import com.fengkai.zhouyang.yangyanghongkong.view.MainBottomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private List<Fragment> mList;
    private MainBottomView mBottom;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        initView();
        initListener();
        initPermission();
        //进来就选择推荐
        mViewpager.setCurrentItem(1, false);
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
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initBottom() {

    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            if (i == PackageManager.PERMISSION_GRANTED) {
                return;
            }
            ActivityCompat.requestPermissions(this, permissions, 321);
        }
    }

}
