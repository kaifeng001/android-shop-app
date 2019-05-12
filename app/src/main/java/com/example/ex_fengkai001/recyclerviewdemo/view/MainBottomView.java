package com.example.ex_fengkai001.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.ex_fengkai001.recyclerviewdemo.R;


public class MainBottomView extends LinearLayout implements View.OnClickListener {
    private ImageView mHome;
    private ImageView mRecommend;
    private ImageView mSearch;
    private ImageView mTalk;
    private ImageView mCenter;

    private TextView mHomeText;
    private TextView mRecommendText;
    private TextView mSearchText;
    private TextView mTalkText;
    private TextView mCenterText;

    private ViewPager mViewPager;

    public MainBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_layout_bottom, this);
        mHome = inflate.findViewById(R.id.main_bottom_image0);
        mRecommend = inflate.findViewById(R.id.main_bottom_image1);
        mSearch = inflate.findViewById(R.id.main_bottom_image2);
        mTalk = inflate.findViewById(R.id.main_bottom_image3);
        mCenter = inflate.findViewById(R.id.main_bottom_image4);

        mHomeText = inflate.findViewById(R.id.main_bottom_text0);
        mRecommendText = inflate.findViewById(R.id.main_bottom_text1);
        mSearchText = inflate.findViewById(R.id.main_bottom_text2);
        mTalkText = inflate.findViewById(R.id.main_bottom_text3);
        mCenterText = inflate.findViewById(R.id.main_bottom_text4);

        findViewById(R.id.bottom_layout_0).setOnClickListener(this);
        findViewById(R.id.bottom_layout_1).setOnClickListener(this);
        findViewById(R.id.bottom_layout_2).setOnClickListener(this);
        findViewById(R.id.bottom_layout_3).setOnClickListener(this);
        findViewById(R.id.bottom_layout_4).setOnClickListener(this);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public void select(int position) {
        switch (position) {
            case 0:
                mHome.setImageResource(R.drawable.main_home_select);
                mRecommend.setImageResource(R.drawable.main_remend);
                mSearch.setImageResource(R.drawable.main_search);
                mTalk.setImageResource(R.drawable.main_talk);
                mCenter.setImageResource(R.drawable.main_center);

                mHomeText.setTextColor(Color.parseColor("#e02e24"));
                mRecommendText.setTextColor(Color.parseColor("#666666"));
                mSearchText.setTextColor(Color.parseColor("#666666"));
                mTalkText.setTextColor(Color.parseColor("#666666"));
                mCenterText.setTextColor(Color.parseColor("#666666"));
                break;
            case 1:
                mHome.setImageResource(R.drawable.main_home);
                mRecommend.setImageResource(R.drawable.main_remend_select);
                mSearch.setImageResource(R.drawable.main_search);
                mTalk.setImageResource(R.drawable.main_talk);
                mCenter.setImageResource(R.drawable.main_center);

                mHomeText.setTextColor(Color.parseColor("#666666"));
                mRecommendText.setTextColor(Color.parseColor("#e02e24"));
                mSearchText.setTextColor(Color.parseColor("#666666"));
                mTalkText.setTextColor(Color.parseColor("#666666"));
                mCenterText.setTextColor(Color.parseColor("#666666"));
                break;
            case 2:
                mHome.setImageResource(R.drawable.main_home);
                mRecommend.setImageResource(R.drawable.main_remend);
                mSearch.setImageResource(R.drawable.main_search_select);
                mTalk.setImageResource(R.drawable.main_talk);
                mCenter.setImageResource(R.drawable.main_center);

                mHomeText.setTextColor(Color.parseColor("#666666"));
                mRecommendText.setTextColor(Color.parseColor("#666666"));
                mSearchText.setTextColor(Color.parseColor("#e02e24"));
                mTalkText.setTextColor(Color.parseColor("#666666"));
                mCenterText.setTextColor(Color.parseColor("#666666"));
                break;
            case 3:
                mHome.setImageResource(R.drawable.main_home);
                mRecommend.setImageResource(R.drawable.main_remend);
                mSearch.setImageResource(R.drawable.main_search);
                mTalk.setImageResource(R.drawable.main_talk_select);
                mCenter.setImageResource(R.drawable.main_center);

                mHomeText.setTextColor(Color.parseColor("#666666"));
                mRecommendText.setTextColor(Color.parseColor("#666666"));
                mSearchText.setTextColor(Color.parseColor("#666666"));
                mTalkText.setTextColor(Color.parseColor("#e02e24"));
                mCenterText.setTextColor(Color.parseColor("#666666"));
                break;
            case 4:
                mHome.setImageResource(R.drawable.main_home);
                mRecommend.setImageResource(R.drawable.main_remend);
                mSearch.setImageResource(R.drawable.main_search);
                mTalk.setImageResource(R.drawable.main_talk);
                mCenter.setImageResource(R.drawable.main_center_select);

                mHomeText.setTextColor(Color.parseColor("#666666"));
                mRecommendText.setTextColor(Color.parseColor("#666666"));
                mSearchText.setTextColor(Color.parseColor("#666666"));
                mTalkText.setTextColor(Color.parseColor("#666666"));
                mCenterText.setTextColor(Color.parseColor("#e02e24"));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_layout_0:
                select(0);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.bottom_layout_1:
                select(1);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.bottom_layout_2:
                select(2);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.bottom_layout_3:
                select(3);
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.bottom_layout_4:
                select(4);
                mViewPager.setCurrentItem(4, false);
                break;
        }
    }
}
