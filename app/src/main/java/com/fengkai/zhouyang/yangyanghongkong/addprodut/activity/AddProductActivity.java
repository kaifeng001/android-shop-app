package com.fengkai.zhouyang.yangyanghongkong.addprodut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.AddProduct;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.presenter.AddProductPresenter;
import com.fengkai.zhouyang.yangyanghongkong.utils.FileUtil;
import com.fengkai.zhouyang.yangyanghongkong.view.AddSettingView;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, AddProduct {
    private TextView mIcon;
    private AddSettingView mTitle;
    private AddSettingView mPrice;
    private AddSettingView mNum;
    private AddSettingView mType;
    private Button mConfirm;
    private String mPath;
    private AddProductPresenter mPresenter;
    private RoundedImageView mIconImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);
        initTopView();
        initView();

        mPresenter = new AddProductPresenter(this);
    }

    private void initView() {
        mIcon = findViewById(R.id.prod_icon);
        mTitle = findViewById(R.id.prod_title);
        mPrice = findViewById(R.id.prod_price);
        mNum = findViewById(R.id.prod_num);
        mConfirm = findViewById(R.id.confirm);
        mIconImage = findViewById(R.id.prod_icon_image);
        mType = findViewById(R.id.prod_type);

        mIcon.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    private void initTopView() {
        View topBack = findViewById(R.id.top_back);
        topBack.setOnClickListener(this);
        topBack.setVisibility(View.VISIBLE);
        TextView topTitle = findViewById(R.id.top_title);
        topTitle.setText("添加商品");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.prod_icon:
                FileUtil.goPhotoSelect(this);
            case R.id.confirm:
                insertData();
                break;
        }
    }

    private void insertData() {
        if (TextUtils.isEmpty(mPath)) {
            Toast.makeText(this, "图片不能路径为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mTitle.getEditText())) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mPrice.getEditText())) {
            Toast.makeText(this, "价格不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mNum.getEditText())) {
            Toast.makeText(this, "件数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.insertData();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FileUtil.GO_PHOTO) {
            mPath = FileUtil.parsePhotoPath(this, data);
            if (mPath == null){
                return;
            }
            Glide.with(this).load(mPath).into(mIconImage);
        }
    }

    @Override
    public String getProductTitle() {
        String title = mTitle.getEditText();
        return title;
    }

    @Override
    public String getProductPrice() {
        String price = mPrice.getEditText();
        return price;
    }

    @Override
    public String getProductNum() {
        String num = mNum.getEditText();
        return num;
    }

    @Override
    public String getProductIcon() {
        return mPath;
    }
}
