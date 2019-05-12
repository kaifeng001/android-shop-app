package com.example.ex_fengkai001.recyclerviewdemo.fragment.base;

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
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            initData();
            initListener();
        }
    }

    public abstract int setLayoutId();

    public abstract void initView(View contentView);

    public abstract void initData();

    public abstract void initListener();
}
