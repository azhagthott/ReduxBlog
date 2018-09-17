package com.android.dev.reduxblog.data;

import android.os.AsyncTask;
import android.util.Log;

import com.android.dev.reduxblog.AppBase;
import com.android.dev.reduxblog.data.remote.PostInterceptor;
import com.android.dev.reduxblog.data.remote.ReduxBlogApi;
import com.android.dev.reduxblog.model.Post;

import retrofit2.Call;
import retrofit2.Response;

public class GetPostByIdTask extends AsyncTask<Integer, Void, Post> {
    @Override
    protected Post doInBackground(Integer... integers) {

        Post post = new Post();
        final ReduxBlogApi request = PostInterceptor.get();

        Call<Post> call = request.getPostById(integers[0]);

        try {
            Response<Post> response = call.execute();
            if (response.code() == 200) {
                post = response.body();
            }
        } catch (Exception e) {
            Log.d("TAG", "Exception: " + e.getMessage());
        }
        return post;
    }
}
