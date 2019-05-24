package com.fengkai.zhouyang.yangyanghongkong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.home.activity.MainActivity;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lauch);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                LaunchActivity.this.startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
