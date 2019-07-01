package com.fengkai.zhouyang.yangyanghongkong.customer.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.customer.acitivity.AddCustomerActivity;
import com.fengkai.zhouyang.yangyanghongkong.customer.model.Customer;
import com.fengkai.zhouyang.yangyanghongkong.db.CustomerDatabase;
import com.fengkai.zhouyang.yangyanghongkong.utils.FileUtil;
import com.fengkai.zhouyang.yangyanghongkong.utils.LibTools;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter {

    private List<Customer> mList = new ArrayList<>();
    private Activity mContext;
    private LayoutInflater mInflater;
    private PopupWindow mPopupWindow;

    public CustomerAdapter(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_layout_add_customer, parent, false);
        return new CustomerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CustomerHolder) {
            final CustomerHolder customerHolder = (CustomerHolder) holder;
            if (mList != null && mList.size() > 0) {
                final String name = mList.get(position).name + " ,";
                customerHolder.mName.setText(name);
                final String phone = mList.get(position).phone;
                customerHolder.mPhone.setText(phone);
                final String address = mList.get(position).address;
                customerHolder.mAddress.setText(address);
                customerHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showCopy(customerHolder.itemView, name + phone + "\\n" + address, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class CustomerHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mPhone;
        public TextView mAddress;

        public CustomerHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.customer_name_item);
            mPhone = itemView.findViewById(R.id.customer_phone_item);
            mAddress = itemView.findViewById(R.id.customer_address_item);
        }
    }

    public void setData(List<Customer> list) {
        mList.clear();
        mList.addAll(list);
    }

    private void showCopy(View view, final String copyString, final int position) {
        View popupView = mInflater.inflate(R.layout.copy_popwindow, null, false);
        popupView.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtil.CopyToClipboard(mContext, copyString);
                Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = mList.get(position);
                Intent intent = new Intent(mContext, AddCustomerActivity.class);
                intent.putExtra("id", customer.id);
                intent.putExtra("name", customer.name);
                intent.putExtra("address", customer.address);
                intent.putExtra("phone", customer.phone);
                mContext.startActivityForResult(intent, 2);
                mPopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = mList.get(position);
                CustomerDatabase.getInstance().deleteById(customer.id);
                mList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(view, LibTools.dp2px(150), -LibTools.dp2px(50));
    }
}
