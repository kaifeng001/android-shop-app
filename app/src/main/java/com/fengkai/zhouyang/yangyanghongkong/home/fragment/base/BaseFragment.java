package com.fengkai.zhouyang.yangyanghongkong.home.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = setLayoutId();
        View view = inflater.inflate(layoutId, null);
        initView(view);
        initTitle(view);
        initListener();
        setBackListener(view);
        initPresenter();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            clearSelectState();
            initData();
        }
    }

    public abstract int setLayoutId();

    public abstract void initView(View contentView);

    public abstract void initData();

    public abstract void initListener();

    public void initPresenter() {

    }

    public void initTitle(View view) {

    }

    public void setBackListener(View view){

    }

    public void clearSelectState(){

    }
}
