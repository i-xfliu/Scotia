package com.example.scotia.model.data;

import androidx.annotation.NonNull;
import com.example.scotia.http.IHttpDataSource;
import com.example.scotia.model.RepoEntity;
import com.example.scotia.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.List;

/**
 * dataManger provides users the apis to request to GitHub
 */
public class DataManager implements IHttpDataSource {

    private static volatile DataManager sDataManager;

    private IHttpDataSource mHttpDataSource;

    private DataManager(@NonNull IHttpDataSource httpDataSource) {
        mHttpDataSource = httpDataSource;
    }

    public static DataManager getInstance(IHttpDataSource httpDataSource) {
        if (sDataManager == null) {
            synchronized (DataManager.class) {
                if (sDataManager == null) {
                    sDataManager = new DataManager(httpDataSource);
                }
            }
        }

        return sDataManager;
    }
    @Override
    public Call<User> getUserDataById(String id) {
        return mHttpDataSource.getUserDataById(id);
    }

    @Override
    public Call<List<RepoEntity>> getReposById(String id) {
        return mHttpDataSource.getReposById(id);
    }

    @Override
    public Call<ResponseBody> getAvatar(String url) {
        return mHttpDataSource.getAvatar(url);
    }
}
