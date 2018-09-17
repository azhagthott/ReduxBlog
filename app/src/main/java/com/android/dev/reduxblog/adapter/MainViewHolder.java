package com.android.dev.reduxblog.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.dev.reduxblog.R;

public class MainViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout rlPost;
    public ImageView ivDetailedPost;
    private TextView tvPOstTitle;
    private TextView tvPostCategory;
    private TextView tvPostContent;

    public MainViewHolder(@NonNull View view) {
        super(view);
        rlPost = view.findViewById(R.id.rl_post);
        ivDetailedPost = view.findViewById(R.id.iv_detailed_post);
        tvPOstTitle = view.findViewById(R.id.tv_post_title);
        tvPostCategory = view.findViewById(R.id.tv_post_category);
        tvPostContent = view.findViewById(R.id.tv_post_content);
    }

    public void setTitle(String postTitle) {
        if (postTitle == null) {
            tvPOstTitle.setText(R.string.post_null);
        } else {
            tvPOstTitle.setText(postTitle);
        }
    }

    public void setCategory(String postCategory) {
        if (postCategory == null) {
            tvPostCategory.setText(R.string.post_null);
        } else {
            tvPostCategory.setText(postCategory);
        }
    }

    public void setContent(String postContent) {
        if (postContent == null) {
            tvPostContent.setText(R.string.post_null);
        } else {
            tvPostContent.setText(postContent);
        }
    }
}
