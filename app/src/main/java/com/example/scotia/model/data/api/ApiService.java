package com.example.scotia.model.data.api;

import com.example.scotia.model.RepoEntity;
import com.example.scotia.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    /**
     * request to get the user info
     * @param id
     * @return
     */
    @GET("{id}")
    Call<User> getUserDataById(@Path("id") String id);

    /**
     * request to get the repositories for the user
     * @param id
     * @return
     */
    @GET("{id}/repos")
    Call<List<RepoEntity>> getReposById(@Path("id") String id);

    /**
     * request to get the avatar
     * @param url
     * @return
     */
    @Headers("Content-Type:image/png; charset=utf-8")
    @GET
    @Streaming
    Call<ResponseBody> getAvatar(@Url String url);



}
