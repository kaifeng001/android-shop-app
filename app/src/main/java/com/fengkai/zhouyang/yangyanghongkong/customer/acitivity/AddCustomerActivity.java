package com.fengkai.zhouyang.yangyanghongkong.customer.acitivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.customer.model.Customer;
import com.fengkai.zhouyang.yangyanghongkong.db.CustomerDatabase;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mName;
    private EditText mAddress;
    private EditText mPhone;
    public static final int HAD_ADD_CUSTOMER = 2;
    public static final int EDIT_BACK = 3;
    private int mId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_customer);
        initTopView();
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        mId = intent.getIntExtra("id", -1);
        if (name != null) {
            mName.setText(name);
        }
        if (address != null) {
            mAddress.setText(address);
        }
        if (phone != null) {
            mPhone.setText(phone);
        }
    }

    private void initView() {
        mName = findViewById(R.id.customer_name);
        mAddress = findViewById(R.id.customer_address);
        mPhone = findViewById(R.id.customer_phone);
    }

    private void initTopView() {
        View topBack = findViewById(R.id.top_back);
        topBack.setOnClickListener(this);
        topBack.setVisibility(View.VISIBLE);
        TextView topTitle = findViewById(R.id.top_title);
        topTitle.setText("添加地址");
        TextView topAdd = findViewById(R.id.top_right);
        topAdd.setText("保存");
        topAdd.setVisibility(View.VISIBLE);
        topAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:

                finish();
                break;
            case R.id.top_right:
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String phone = mPhone.getText().toString();
                if (name == null) {
                    Toast.makeText(this, "名字不能为空", Toast.LENGTH_SHORT).show();
                    break;
                } else if (phone == null) {
                    Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                } else if (address == null) {
                    Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                Customer customer = new Customer();
                customer.name = name;
                customer.phone = phone;
                customer.address = address;
                if (mId == -1) {
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    setResult(HAD_ADD_CUSTOMER);
                    CustomerDatabase.insertDb(customer);
                } else {
                    Toast.makeText(this, "编辑完成", Toast.LENGTH_SHORT).show();
                    setResult(EDIT_BACK);
                    CustomerDatabase.updateById(customer, mId);
                }
                finish();
                break;
        }
    }

}
