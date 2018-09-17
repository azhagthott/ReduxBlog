package com.android.dev.reduxblog.data;

import android.os.AsyncTask;
import android.util.Log;

import com.android.dev.reduxblog.AppBase;
import com.android.dev.reduxblog.data.remote.PostInterceptor;
import com.android.dev.reduxblog.data.remote.ReduxBlogApi;
import com.android.dev.reduxblog.model.Post;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

public class CreateNewPostTask extends AsyncTask<Post, Void, Post> {
    @Override
    protected Post doInBackground(Post... posts) {

        final ReduxBlogApi request = PostInterceptor.get();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", posts[0].id);
        jsonObject.addProperty("title", posts[0].title);
        jsonObject.addProperty("categories", posts[0].categories);
        jsonObject.addProperty("content", posts[0].content);

        Call<Post> call = request.createPost(AppBase.API_KEY, jsonObject);

        Post post = new Post();

        try {
            Response<Post> response = call.execute();
            if (response.code() == 200) {
                if (response.body() != null) {
                    post.setPostId(response.body().id);
                    post.setPostTitle(response.body().title);
                    post.setPostCategory(response.body().categories);
                    post.setPostContent(response.body().content);
                }
            }
        } catch (Exception e) {
            Log.d("TAG", "Exception: " + e.getMessage());
        }
        return post;
    }
}
