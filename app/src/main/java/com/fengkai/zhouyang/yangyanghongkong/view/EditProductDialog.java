package com.fengkai.zhouyang.yangyanghongkong.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.ProductImpl;
import com.fengkai.zhouyang.yangyanghongkong.db.ProductDatabase;
import com.makeramen.roundedimageview.RoundedImageView;

public class EditProductDialog extends BottomDialog {
    public interface OnIconSelectClickListener {
        void onIconSelectClick();
    }

    private OnIconSelectClickListener mIconListener;
    private RoundedImageView mIcon;
    private EditText mPrice;
    private EditText mTitle;
    private EditText mNum;
    private String mPath;
    private long mId;
    private ProductImpl mBiz;

    public EditProductDialog(@NonNull Context context, Product product) {
        super(context);
        initView(context);
        initData(product);
        mBiz = new ProductImpl();
    }

    private void initData(Product product) {
        mId = product.id;
        mPath = product.icon;
        Glide.with(mContext).load(product.icon).into(mIcon);
        mPrice.setHint(product.price);
        mTitle.setHint(product.title);
        mNum.setHint(product.num);
    }

    public void showSelectIcon(String icon) {
        mPath = icon;
        Glide.with(mContext).load(icon).into(mIcon);
    }

    public void setIconSelectListener(OnIconSelectClickListener listener) {
        mIconListener = listener;
    }

    private void initView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.product_edit_dialog, null);
        mView.findViewById(R.id.dialog_prod_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIconListener.onIconSelectClick();
            }
        });
        mIcon = mView.findViewById(R.id.dialog_prod_icon_image);
        mNum = mView.findViewById(R.id.dialog_prod_num);
        mPrice = mView.findViewById(R.id.dialog_prod_price);
        mTitle = mView.findViewById(R.id.dialog_prod_title);
        mView.findViewById(R.id.dialog_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmUpdate();
            }
        });
    }

    private void confirmUpdate() {
        Product product = new Product();
        product.icon = mPath;
        String hintTitle = mTitle.getHint().toString();
        String title = mTitle.getText().toString();

        String hintPrice = mPrice.getHint().toString();
        String price = mPrice.getText().toString();

        String hintNum = mNum.getHint().toString();
        String num = mNum.getText().toString();
        if (setTitle(product, hintTitle, title)){
            Toast.makeText(mContext, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (setPrice(product, hintPrice, price)){
            Toast.makeText(mContext, "价格不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (setNum(product, hintNum, num)){
            Toast.makeText(mContext, "件数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mBiz.updateProduct(mId,product);
        Toast.makeText(mContext, "更新数据成功", Toast.LENGTH_SHORT).show();
        dismiss();
    }

    private boolean setTitle(Product product, String hint, String text) {
        if (!TextUtils.isEmpty(text)){
            if (TextUtils.isEmpty(text)){
                return true;
            }else {
                product.title = text;
            }
        }else {
            product.title = hint;
        }
        return false;
    }

    private boolean setPrice(Product product, String hint, String text) {
        if (!TextUtils.isEmpty(text)){
            if (TextUtils.isEmpty(text)){
                return true;
            }else {
                product.price = text;
            }
        }else {
            product.price = hint;
        }
        return false;
    }

    private boolean setNum(Product product, String hint, String text) {
        if (!TextUtils.isEmpty(text)){
            if (TextUtils.isEmpty(text)){
                return true;
            }else {
                product.num = text;
            }
        }else {
            product.num = hint;
        }
        return false;
    }
}
