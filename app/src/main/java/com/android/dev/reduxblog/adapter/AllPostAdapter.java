package com.android.dev.reduxblog.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dev.reduxblog.AppBase;
import com.android.dev.reduxblog.R;
import com.android.dev.reduxblog.model.Post;
import com.android.dev.reduxblog.ui.PostDetaliedActivity;

import java.util.ArrayList;
import java.util.List;

public class AllPostAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Post> mPostList = new ArrayList<>();

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int position) {

        final Post post = mPostList.get(position);
        final Context context = holder.itemView.getContext();

        holder.setTitle(String.valueOf(post.title));
        holder.setCategory(post.categories);
        holder.setContent(post.content);

        holder.ivDetailedPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetaliedActivity.class);
                intent.putExtra(AppBase.POST_ID, post.id);
                intent.putExtra(AppBase.POST_TITLE, post.title);
                intent.putExtra(AppBase.POST_CATEGORY, post.categories);
                intent.putExtra(AppBase.POST_CONTEN, post.content);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public void updatePostList(List<Post> posts) {
        this.mPostList.addAll(posts);
        notifyDataSetChanged();
    }

    public void removePost(int position) {
        mPostList.remove(position);
        notifyItemRemoved(position);
    }
}
