package com.fengkai.zhouyang.yangyanghongkong.home.fragment;

import android.view.View;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.base.BaseFragment;
import com.fengkai.zhouyang.yangyanghongkong.utils.LibTools;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public int setLayoutId() {
        return R.layout.layout_home;
    }

    @Override
    public void initView(View contentView) {
        contentView.findViewById(R.id.home_weixin_scan).setOnClickListener(this);
        contentView.findViewById(R.id.home_alipay_scan).setOnClickListener(this);
        contentView.findViewById(R.id.home_alipay_pay).setOnClickListener(this);
        contentView.findViewById(R.id.home_weixin_pay).setOnClickListener(this);
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
            case R.id.home_alipay_scan:
                LibTools.openAliPayScan(getContext());
                break;
            case R.id.home_weixin_scan:
                LibTools.openWeiXinScan(getContext());
                break;
            case R.id.home_alipay_pay:
                LibTools.openAliPayPay(getContext());
                break;
            case R.id.home_weixin_pay:
                LibTools.openWeiXinPay(getContext());
                break;
        }
    }
}
