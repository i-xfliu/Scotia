package com.example.scotia.http;


import com.example.scotia.model.data.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * The entry of Retrofit Service
 */
public class RetrofitManager {

    private static class RetrofitInstance {
        private static Retrofit sRetrofit = buildRetrofit();
    }

    private RetrofitManager() {

    }

    public static Retrofit get() {
        return RetrofitInstance.sRetrofit;
    }

    private static Retrofit buildRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}
