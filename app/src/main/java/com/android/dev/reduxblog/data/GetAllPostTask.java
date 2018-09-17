package com.android.dev.reduxblog.data;

import android.os.AsyncTask;
import android.util.Log;

import com.android.dev.reduxblog.AppBase;
import com.android.dev.reduxblog.data.remote.PostInterceptor;
import com.android.dev.reduxblog.data.remote.ReduxBlogApi;
import com.android.dev.reduxblog.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetAllPostTask extends AsyncTask<Void, Integer, List<Post>> {

    @Override
    protected List<Post> doInBackground(Void... voids) {

        List<Post> mPostList = new ArrayList<>();
        final ReduxBlogApi request = PostInterceptor.get();

        Call<List<Post>> call = request.getAllPosts(AppBase.API_KEY);

        try {
            Response<List<Post>> response = call.execute();
            if (response.code() == 200) {
                mPostList.addAll(response.body());
            }
        } catch (Exception e) {
            Log.d("TAG", "Exception: " + e.getMessage());
        }
        return mPostList;
    }
}
