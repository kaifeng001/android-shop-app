package com.fengkai.zhouyang.yangyanghongkong.home.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.addprodut.activity.AddProductActivity;
import com.fengkai.zhouyang.yangyanghongkong.activity.ProductDetailsActivity;
import com.fengkai.zhouyang.yangyanghongkong.home.adapter.RecommendAdapter;
import com.fengkai.zhouyang.yangyanghongkong.home.fragment.base.BaseFragment;
import com.fengkai.zhouyang.yangyanghongkong.home.port.IRecommend;
import com.fengkai.zhouyang.yangyanghongkong.home.presenter.RecommendPresenter;
import com.fengkai.zhouyang.yangyanghongkong.utils.FileUtil;
import com.fengkai.zhouyang.yangyanghongkong.utils.Utils;
import com.fengkai.zhouyang.yangyanghongkong.view.EditProductDialog;
import com.fengkai.zhouyang.yangyanghongkong.view.recycleview.DividerGridItemDecoration;
import com.fengkai.zhouyang.yangyanghongkong.utils.LibTools;

import java.util.List;


public class RecommendFragment extends BaseFragment implements IRecommend, View.OnClickListener {
    private RecyclerView mRecycler;
    private TextView mDelete;
    private TextView mEdit;
    private RecommendAdapter mAdapter;
    private RecommendPresenter mPresenter;
    private String mPath;

    private static final int GO_DETAIL = 0;


    @Override
    public int setLayoutId() {
        return R.layout.layout_recommend;
    }

    @Override
    public void initPresenter() {
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    public void initView(View contentView) {
        mRecycler = contentView.findViewById(R.id.recommend_list);
        mRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new DividerGridItemDecoration(getContext(), LibTools.dp2px(2), R.color.divider));
        mAdapter = new RecommendAdapter();
    }

    @Override
    public void initTitle(View view) {
        mDelete = view.findViewById(R.id.top_delete);
        mEdit = view.findViewById(R.id.top_edit);
    }

    @Override
    public void initData() {
        mPresenter.setData();
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                if (mAdapter.getItemViewType(position) == RecommendAdapter.ADD_TYPE) {
                    intent = new Intent(getContext(), AddProductActivity.class);
                } else {
                    intent = new Intent(getContext(), ProductDetailsActivity.class);
                }
                startActivityForResult(intent, GO_DETAIL);
            }
        });
        mAdapter.setOnEditStateClickListener(new RecommendAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(int position, boolean isChecked) {
                mPresenter.dealCheckListener(position, isChecked);
            }
        });
        mAdapter.setOnEditStateTrueListener(new RecommendAdapter.OnEditStateTrueListener() {
            @Override
            public void onEditStateTrue(int position) {
                mPresenter.joinEditState(position);
            }
        });
        mDelete.setOnClickListener(this);
        mEdit.setOnClickListener(this);
    }

    public void setBackListener(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return mPresenter.isBackEditState();
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FileUtil.GO_PHOTO) {
            mPath = FileUtil.parsePhotoPath(getContext(), data);
            if (mPath == null){
                return;
            }
            mPresenter.showSelectIconEdit(mPath);
        } else if (requestCode == GO_DETAIL) {
            initData();
        }
    }

    @Override
    public void refreshView(List<Product> list) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void joinEditState() {
        mEdit.setVisibility(View.VISIBLE);
        mDelete.setVisibility(View.VISIBLE);
        mAdapter.setEditState(true);
    }

    @Override
    public void exitEditState() {
        mEdit.setVisibility(View.GONE);
        mDelete.setVisibility(View.GONE);
        mAdapter.setEditState(false);
    }

    @Override
    public void initRecyclerView(List<Product> products) {
        mAdapter.setData(products);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public boolean getEditState() {
        return mAdapter.getEditState();
    }

    @Override
    public void productIsEdited(boolean isCanEdited) {
        if (isCanEdited) {
            mEdit.setVisibility(View.VISIBLE);
        } else {
            mEdit.setVisibility(View.GONE);
        }
    }

    @Override
    public void removeItem(int position, List<Product> data) {
        mAdapter.setData(data);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, data.size() - position);
    }

    @Override
    public void clearSelectState() {
        if (mAdapter == null) {
            return;
        }
        boolean editState = mAdapter.getEditState();
        if (editState) {
            exitEditState();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_delete:
                mPresenter.deleteSelectProduct();
                break;
            case R.id.top_edit:
                mPresenter.dealEditCLick(getContext(), new EditProductDialog.OnIconSelectClickListener() {
                    @Override
                    public void onIconSelectClick() {
                        FileUtil.goPhotoSelect(RecommendFragment.this);
                    }
                });
                break;
        }
    }
}
