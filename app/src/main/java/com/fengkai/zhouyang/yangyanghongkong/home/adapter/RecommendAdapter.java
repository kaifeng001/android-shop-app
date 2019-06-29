package com.fengkai.zhouyang.yangyanghongkong.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.config.Config;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter {
    public static final int PRODUCT_TYPE = 0;
    public static final int ADD_TYPE = 1;

    private List<Product> mList = new ArrayList<>();
    private OnItemClickListener mListener;
    private OnItemCheckListener mEditStateListener;
    private OnEditStateTrueListener mEditStateChangeTrue;
    private boolean mIsEditState;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemCheckListener {
        void onItemCheck(int position, boolean isChecked);
    }

    public interface OnEditStateTrueListener {
        void onEditStateTrue(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ADD_TYPE) {
            inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_add, parent, false);
            holder = new AddHolder(inflate);
        } else if (viewType == PRODUCT_TYPE) {
            inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            holder = new ProductHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ProductHolder) {
            binderProduct((ProductHolder) holder, position);
        } else if (holder instanceof AddHolder) {
            binderAddHolder((AddHolder) holder, position);
        }
    }

    private void binderAddHolder(@NonNull AddHolder holder, final int position) {
        AddHolder addHolder = holder;
        addHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsEditState) {
                    return;
                } else {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    private void binderProduct(@NonNull ProductHolder holder, final int position) {
        final ProductHolder proHolder = holder;
        Product product = mList.get(position);
        Glide.with(proHolder.itemView).load(product.icon).into(proHolder.mIcon);
        proHolder.mNum.setText("已卖" + product.num + "件");
        proHolder.mTitle.setText(product.title);
        proHolder.mPrice.setText(product.price);
        if (!mIsEditState) {
            proHolder.mCheck.setVisibility(View.GONE);
            proHolder.itemView.setSelected(false);
            proHolder.mCheck.setChecked(false);
        } else {
            proHolder.mCheck.setVisibility(View.VISIBLE);
        }

        proHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mIsEditState) {
                    boolean checked = !proHolder.mCheck.isChecked();
                    proHolder.mCheck.setChecked(checked);
                    proHolder.itemView.setSelected(checked);
                    mEditStateListener.onItemCheck(position, checked);
                } else {
                    mListener.onItemClick(position);
                }
            }
        });
        if (Config.isBusness) {
            proHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mEditStateChangeTrue == null){
                        return true;
                    }
                    if (mIsEditState) {
                        return true;
                    }
                    mEditStateChangeTrue.onEditStateTrue(position);
                    mIsEditState = true;
                    proHolder.mCheck.setVisibility(View.VISIBLE);
                    proHolder.mCheck.setChecked(true);
                    proHolder.itemView.setSelected(true);
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ProductHolder extends RecyclerView.ViewHolder {
        public ImageView mIcon;
        public TextView mTitle;
        public TextView mPrice;
        public TextView mNum;
        public CheckBox mCheck;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.pr_icon);
            mTitle = itemView.findViewById(R.id.pr_title);
            mPrice = itemView.findViewById(R.id.pr_price);
            mNum = itemView.findViewById(R.id.pr_buy_num);
            mCheck = itemView.findViewById(R.id.prod_checkbox);
        }
    }

    private class AddHolder extends RecyclerView.ViewHolder {

        public AddHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnEditStateClickListener(OnItemCheckListener listener) {
        mEditStateListener = listener;
    }

    public void setData(List<Product> list) {
        mList.clear();
        mList.addAll(list);
    }

    public boolean getEditState() {
        return mIsEditState;
    }

    public void setEditState(boolean isEdit) {
        mIsEditState = isEdit;
        notifyDataSetChanged();
    }

    public void setOnEditStateTrueListener(OnEditStateTrueListener listener) {
        mEditStateChangeTrue = listener;
    }
}
