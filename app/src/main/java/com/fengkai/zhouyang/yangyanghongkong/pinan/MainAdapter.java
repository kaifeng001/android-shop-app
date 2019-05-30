package com.fengkai.zhouyang.yangyanghongkong.pinan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fengkai.zhouyang.yangyanghongkong.R;
import com.fengkai.zhouyang.yangyanghongkong.utils.LibTools;
import com.makeramen.roundedimageview.RoundedImageView;

public class MainAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 5) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_product_list, parent, false);
            return new MainHolder(inflate);
        } else if (viewType == 0) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_care, parent, false);
            return new NormalHolder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar, parent, false);
            return new AvatarHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_demo, parent, false);
            return new NormalHolder(inflate);
        }
    }

    private class AvatarHolder extends RecyclerView.ViewHolder {
        public AvatarView avatarView;

        public AvatarHolder(View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.avatar);
        }
    }

    private class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);

        }
    }

    private class MainHolder extends RecyclerView.ViewHolder {
        private RecyclerView mView;

        public MainHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.product_recycler);
        }

        public void initRecyclerView() {
            mView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mView.setLayoutManager(layoutManager);
            mView.setAdapter(new ItemAdapter());
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainHolder) {
            MainHolder holder1 = (MainHolder) holder;
            holder1.initRecyclerView();
        } else if (holder instanceof AvatarHolder) {
            bindHolder(holder);

        }
    }

    private void bindHolder(RecyclerView.ViewHolder holder) {
        Context context = holder.itemView.getContext();
        AvatarHolder holder1 = (AvatarHolder) holder;
        AvatarView avatarView = holder1.avatarView;
        avatarView.setmOffset(LibTools.dip2px(context, 16));
        for (int i = 0; i < 9; i++) {
            RoundedImageView imageView = new RoundedImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LibTools.dip2px(context, 45), LibTools.dip2px(context, 45));
            imageView.setLayoutParams(params);
            imageView.setOval(true);
            imageView.setImageResource(R.mipmap.pro);
            if (i % 2 == 0) {
                imageView.setImageResource(R.mipmap.home_page_small_default3);
            }
            avatarView.addView(imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
