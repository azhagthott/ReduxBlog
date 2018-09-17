package com.android.dev.reduxblog.data.remote;

import com.android.dev.reduxblog.model.Post;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReduxBlogApi {

    @GET("/api/posts")
    Call<List<Post>> getAllPosts(@Query("key") int key);

    @POST("/api/posts")
    Call<Post> createPost(@Query("key") int key, @Body JsonObject jsonObject);

    @GET("/api/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);

    @DELETE("/api/posts/{id}")
    Call<Post> deletePost(@Path("id") int id);
}
