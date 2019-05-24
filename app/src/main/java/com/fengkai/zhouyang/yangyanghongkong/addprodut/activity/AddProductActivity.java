package com.fengkai.zhouyang.yangyanghongkong.addprodut.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.port.AddProduct;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.presenter.AddProductPresenter;
import com.fengkai.zhouyang.yangyanghongkong.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, AddProduct {
    private TextView mIcon;
    private EditText mTitle;
    private EditText mPrice;
    private EditText mNum;
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
                photo();
            case R.id.confirm:
                insertData();
                break;
        }
    }

    private void insertData() {
        if (TextUtils.isEmpty(mPath)) {
            Toast.makeText(this, "图片不能路径为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mTitle.getText().toString())) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mPrice.getText().toString())) {
            Toast.makeText(this, "价格不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(mNum.getText().toString())) {
            Toast.makeText(this, "件数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.insertData();
        finish();
    }

    public void photo() {
        String device = Build.MANUFACTURER;
        Intent innerIntent = new Intent(); // "android.intent.action.GET_CONTENT"
        if (device.equals("Xiaomi")) {//小米系统采用19及以上的api，进入的是文件管理，而不是图片库
            innerIntent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            if (Build.VERSION.SDK_INT < 19) {
                innerIntent.setAction(Intent.ACTION_GET_CONTENT);
            } else {
                innerIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
        }
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "11");
        this.startActivityForResult(wrapperIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            String[] proj = {MediaStore.Images.Media.DATA};
            // 获取选中图片的路径
            Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);

            String photo_path;
            if (cursor != null && cursor.moveToFirst()) {

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                photo_path = cursor.getString(column_index);
                if (photo_path == null) {
                    mPath = Utils.getPath(getApplicationContext(), data.getData());
                    Glide.with(this).load(mPath).into(mIconImage);
                    Toast.makeText(this, "mPath:" + mPath, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public String getProductTitle() {
        String title = mTitle.getText().toString();
        return title;
    }

    @Override
    public String getProductPrice() {
        String price = mPrice.getText().toString();
        return price;
    }

    @Override
    public String getProductNum() {
        String num = mNum.getText().toString();
        return num;
    }

    @Override
    public String getProductIcon() {
        return mPath;
    }
}
