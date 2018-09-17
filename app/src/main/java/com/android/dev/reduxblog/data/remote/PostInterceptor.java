package com.android.dev.reduxblog.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.dev.reduxblog.AppBase.READ_TIME_OUT;
import static com.android.dev.reduxblog.AppBase.TIME_OUT;
import static com.android.dev.reduxblog.AppBase.URL_BASE;

public class PostInterceptor {

    public static ReduxBlogApi get() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return interceptor.create(ReduxBlogApi.class);
    }
}
