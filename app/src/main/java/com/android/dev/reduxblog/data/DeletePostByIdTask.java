package com.android.dev.reduxblog.data;

import android.os.AsyncTask;
import android.util.Log;

import com.android.dev.reduxblog.data.remote.PostInterceptor;
import com.android.dev.reduxblog.data.remote.ReduxBlogApi;
import com.android.dev.reduxblog.model.Post;

import retrofit2.Call;
import retrofit2.Response;

public class DeletePostByIdTask extends AsyncTask<Integer, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... integers) {

        final ReduxBlogApi request = PostInterceptor.get();
        Call<Post> call = request.deletePost(integers[0]);
        boolean status = false;

        try {
            Response<Post> response = call.execute();
            if (response.code() == 200) {
                status = true;
            }
        } catch (Exception e) {
            Log.d("TAG", "Exception: " + e.getMessage());
            status = false;
        }
        return status;
    }
}
