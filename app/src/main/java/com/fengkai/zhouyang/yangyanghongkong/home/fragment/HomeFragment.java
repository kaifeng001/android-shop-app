package com.fengkai.zhouyang.yangyanghongkong.home.fragment;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    @Override
    public int setLayoutId() {
        return R.layout.layout_home;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View contentView) {
//        try {
//            //利用Intent打开微信
//            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        } catch (Exception e) {
//            //若无法正常跳转，在此进行错误处理
//            e.printStackTrace();
//        }
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setFlags(335544320);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        } catch (Exception e) {
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
