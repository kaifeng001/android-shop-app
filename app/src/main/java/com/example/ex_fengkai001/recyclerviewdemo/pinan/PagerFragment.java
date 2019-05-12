package com.example.ex_fengkai001.recyclerviewdemo.pinan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex_fengkai001.recyclerviewdemo.R;

public class PagerFragment extends Fragment {
    private int mPosition;

    public static Fragment newInstance(int position) {
        PagerFragment fragment = new PagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recycler, null);
        RecyclerView list = inflate.findViewById(R.id.list);
        list.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        list.setLayoutManager(layoutManager);
        list.setAdapter(new MainAdapter());
        return inflate;
    }
}
