package com.fengkai.zhouyang.yangyanghongkong.customer.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.customer.adapter.CustomerAdapter;
import com.fengkai.zhouyang.yangyanghongkong.customer.model.Customer;
import com.fengkai.zhouyang.yangyanghongkong.db.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int GO_ADD_CUSTOMER = 1;
    private RecyclerView mRecycler;
    private CustomerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer);
        initTopView();
        initView();
        initData();
    }

    private void initData() {
        List<Customer> customers = CustomerDatabase.getInstance().queryAllProduct();
        mAdapter.setData(customers);
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mRecycler = findViewById(R.id.customer_list);
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mAdapter = new CustomerAdapter(this);
        mRecycler.setAdapter(mAdapter);
    }

    private void initTopView() {
        View topBack = findViewById(R.id.top_back);
        topBack.setOnClickListener(this);
        topBack.setVisibility(View.VISIBLE);
        TextView topTitle = findViewById(R.id.top_title);
        topTitle.setText("我的客户");
        TextView topAdd = findViewById(R.id.top_right);
        topAdd.setText("添加");
        topAdd.setVisibility(View.VISIBLE);
        topAdd.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AddCustomerActivity.HAD_ADD_CUSTOMER) {
            refreshData();
        } else if (resultCode == AddCustomerActivity.EDIT_BACK) {
            refreshData();
        }
    }

    public void refreshData() {
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_right:
                Intent intent = new Intent(this, AddCustomerActivity.class);
                startActivityForResult(intent, GO_ADD_CUSTOMER);
                break;
        }
    }
}
