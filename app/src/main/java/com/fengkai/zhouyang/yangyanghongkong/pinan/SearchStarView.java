package com.fengkai.zhouyang.yangyanghongkong.pinan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fengkai.zhouyang.yangyanghongkong.R;


public class SearchStarView extends LinearLayout {
    private ImageView mFirstStar;
    private ImageView mSecondStar;
    private ImageView mThirdStar;
    private ImageView mFourthStar;
    private ImageView mFifthStar;
    private TextView mScore;

    public SearchStarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_search_care_star, this);
        mFirstStar = inflate.findViewById(R.id.care_first_star);
        mSecondStar = inflate.findViewById(R.id.care_second_star);
        mThirdStar = inflate.findViewById(R.id.care_third_star);
        mFourthStar = inflate.findViewById(R.id.care_fourth_star);
        mFifthStar = inflate.findViewById(R.id.care_fifth_star);
        mScore = inflate.findViewById(R.id.care_score);
    }

    public void setScore(int score) {
        mScore.setText(score);
        switch (score) {
            case 1:
                mFirstStar.setImageResource(R.mipmap.search_care_star_light);
                mSecondStar.setImageResource(R.mipmap.search_care_star_normal);
                mThirdStar.setImageResource(R.mipmap.search_care_star_normal);
                mFourthStar.setImageResource(R.mipmap.search_care_star_normal);
                mFifthStar.setImageResource(R.mipmap.search_care_star_normal);
                break;
            case 2:
                mFirstStar.setImageResource(R.mipmap.search_care_star_light);
                mSecondStar.setImageResource(R.mipmap.search_care_star_light);
                mThirdStar.setImageResource(R.mipmap.search_care_star_normal);
                mFourthStar.setImageResource(R.mipmap.search_care_star_normal);
                mFifthStar.setImageResource(R.mipmap.search_care_star_normal);
                break;
            case 3:
                mFirstStar.setImageResource(R.mipmap.search_care_star_light);
                mSecondStar.setImageResource(R.mipmap.search_care_star_light);
                mThirdStar.setImageResource(R.mipmap.search_care_star_light);
                mFourthStar.setImageResource(R.mipmap.search_care_star_normal);
                mFifthStar.setImageResource(R.mipmap.search_care_star_normal);
                break;
            case 4:
                mFirstStar.setImageResource(R.mipmap.search_care_star_light);
                mSecondStar.setImageResource(R.mipmap.search_care_star_light);
                mThirdStar.setImageResource(R.mipmap.search_care_star_light);
                mFourthStar.setImageResource(R.mipmap.search_care_star_light);
                mFifthStar.setImageResource(R.mipmap.search_care_star_normal);
                break;
            case 5:
                mFirstStar.setImageResource(R.mipmap.search_care_star_light);
                mSecondStar.setImageResource(R.mipmap.search_care_star_light);
                mThirdStar.setImageResource(R.mipmap.search_care_star_light);
                mFourthStar.setImageResource(R.mipmap.search_care_star_light);
                mFifthStar.setImageResource(R.mipmap.search_care_star_light);
                break;
        }
    }
}
