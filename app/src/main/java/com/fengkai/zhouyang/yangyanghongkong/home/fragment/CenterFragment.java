package com.fengkai.zhouyang.yangyanghongkong.home.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.AlgorithmUtils;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.constant.Constant;
import com.fengkai.zhouyang.yangyanghongkong.customer.acitivity.CustomerActivity;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.base.BaseFragment;
import com.fengkai.zhouyang.yangyanghongkong.utils.FileUtil;
import com.fengkai.zhouyang.yangyanghongkong.utils.SharePreferenceUtils;
import com.makeramen.roundedimageview.RoundedImageView;


public class CenterFragment extends BaseFragment implements View.OnClickListener {
    private RoundedImageView mIcon;
    private RelativeLayout mItemCustomer;
    private String mPath;

    @Override
    public int setLayoutId() {
        return R.layout.layout_center;
    }

    @Override
    public void initView(View contentView) {
        mItemCustomer = contentView.findViewById(R.id.center_item_customer);
        mIcon = contentView.findViewById(R.id.center_icon);
    }

    @Override
    public void initData() {
        mPath = SharePreferenceUtils.getInstance().getString(Constant.USER_ICON);
        if (TextUtils.isEmpty(mPath)) {
            return;
        }
        Glide.with(this).load(mPath).into(mIcon);
    }

    @Override
    public void initListener() {
        mIcon.setOnClickListener(this);
        mItemCustomer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_item_customer:
                Intent intent = new Intent(getContext(), CustomerActivity.class);
                startActivity(intent);
                AlgorithmUtils.testAlgorithm();
                break;
            case R.id.center_icon:
                FileUtil.goPhotoSelect(this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FileUtil.GO_PHOTO) {
            mPath = FileUtil.parsePhotoPath(getContext(), data);
            if (mPath == null) {
                return;
            }
            SharePreferenceUtils.getInstance().putString(Constant.USER_ICON, mPath);
            Glide.with(this).load(mPath).into(mIcon);
        }
    }
}
