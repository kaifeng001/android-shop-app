package com.fengkai.zhouyang.yangyanghongkong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;


public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);
        ImageView iconView = findViewById(R.id.detail_icon);
        Intent intent = getIntent();
        String icon = intent.getStringExtra("icon");
        Glide.with(this).load(icon).into(iconView);
        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
