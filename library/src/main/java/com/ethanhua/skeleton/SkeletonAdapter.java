package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.recyclerview.widget.RecyclerView;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class SkeletonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mItemCount;
    private int mLayoutReference;
    private int mColor;
    private boolean mShimmer;
    private int mShimmerDuration;
    private int mShimmerAngle;
    private boolean isViewShowing = false;
    private final int SHOW = 1; //只是显示
    private final int START = 2; //开始动画

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == SHOW) {
            return new RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
            };
        } else if (mShimmer && viewType == START) {
            return new ShimmerViewHolder(inflater, parent, mLayoutReference);
        }
        return new RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
        };
    }

    public void setViewShowing(boolean viewShowing) {
        isViewShowing = viewShowing;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShimmer && holder.getItemViewType() == START) {
            ShimmerLayout layout = (ShimmerLayout) holder.itemView;
            layout.setShimmerAnimationDuration(mShimmerDuration);
            layout.setShimmerAngle(mShimmerAngle);
            layout.setShimmerColor(mColor);
            layout.startShimmerAnimation();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isViewShowing ? START : SHOW;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    public void setShimmerColor(int color) {
        this.mColor = color;
    }

    public void shimmer(boolean shimmer) {
        this.mShimmer = shimmer;
    }

    public void setShimmerDuration(int shimmerDuration) {
        this.mShimmerDuration = shimmerDuration;
    }

    public void setShimmerAngle(@IntRange(from = 0, to = 30) int shimmerAngle) {
        this.mShimmerAngle = shimmerAngle;
    }

}
