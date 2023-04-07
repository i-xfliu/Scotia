package com.example.scotia.http;

import androidx.annotation.NonNull;
import com.example.scotia.model.RepoEntity;
import com.example.scotia.model.User;
import com.example.scotia.model.data.api.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.List;


public class HttpDataSource implements IHttpDataSource{

    private static volatile HttpDataSource sHttpDataSource;

    private ApiService mApiService;

    private HttpDataSource(@NonNull ApiService apiService) {
        mApiService = apiService;
    }

    public static HttpDataSource getInstance(ApiService apiService) {
        if (sHttpDataSource == null) {
            synchronized (HttpDataSource.class) {
                if (sHttpDataSource == null) {
                    sHttpDataSource = new HttpDataSource(apiService);
                }
            }
        }

        return sHttpDataSource;
    }

    @Override
    public Call<User> getUserDataById(String id) {
        return mApiService.getUserDataById(id);
    }

    @Override
    public Call<List<RepoEntity>> getReposById(String id) {
        return mApiService.getReposById(id);
    }

    @Override
    public Call<ResponseBody> getAvatar(String url) {
        return mApiService.getAvatar(url);
    }
}
