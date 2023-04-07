package com.example.scotia.manage;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class OkHttpProvider {

    private static OkHttpClient instance = null;

    public static OkHttpClient getOkHttpInstance() {
        if (instance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            instance = new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(interceptor)
                    .build();
        }
        return instance;
    }
}
