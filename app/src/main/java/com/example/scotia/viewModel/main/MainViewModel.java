package com.example.scotia.viewModel.main;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.*;
import androidx.lifecycle.MutableLiveData;
import com.example.scotia.viewModel.base.BaseViewModel;
import com.example.scotia.MyApplication;
import com.example.scotia.R;
import com.example.scotia.model.data.HttpCode;
import com.example.scotia.model.RepoEntity;
import com.example.scotia.model.User;
import com.example.scotia.model.data.DataManager;
import com.example.scotia.view.main.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

/**
 * the VM for the MainActivity
 */
public class MainViewModel extends BaseViewModel {
    // the field of the user name
    public final ObservableField<String> userId = new ObservableField<>();
    // the field of the client's input to the ediText
    public final ObservableField<String> userInput = new ObservableField<>();
    // the field of avatar url
    public final ObservableField<String> avatarUrl = new ObservableField<>();
    // the field of repos retrieving from gitHub
    public final ObservableList<Object> dataList = new ObservableArrayList<>();
    public static MutableLiveData<Boolean> mIsLoading;
    private User mUser;
    private DataManager mDataManager;

    public MainViewModel(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public MutableLiveData<Boolean> isLoading() {
        if (null == mIsLoading) {
            mIsLoading = new MutableLiveData<>();
            mIsLoading.setValue(false);
        }
        return mIsLoading;
    }

    public void onClickSearch(View view) {
        String userName = userInput.get();
        clearData();
        Call<User> userCall = mDataManager.getUserDataById(userName);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (response.code() == HttpCode.SUCCESS) {
                    mUser = response.body();
                    String name = mUser.getName();
                    if (null == name || name == "") {
                        setUserName("User Does not Set a Name Yet");
                    } else {
                        setUserName(mUser.getName());
                    }

                    if (null != mUser.getAvatarUrl() && mUser.getAvatarUrl() != "") {
                        avatarUrl.set(mUser.getAvatarUrl());
                    }

                    downloadRepos(userName);

                } else {
                    mIsLoading.setValue(false);
                    ToastUtils.showLongToast("Error: Could Not Get the User Info.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mIsLoading.setValue(false);
                ToastUtils.showLongToast("Error: Could Not Get the User Info.");
                Log.e("Scotia", "Error info: " + t.getMessage());
            }
        });

    }

    private void clearData() {
        dataList.clear();
        setUserName(null);
        mIsLoading.setValue(true);
    }

    private void downloadRepos(String userName) {
        Call<List<RepoEntity>> avatarCall = mDataManager.getReposById(userName);
        avatarCall.enqueue(new Callback<List<RepoEntity>>() {
            @Override
            public void onResponse(Call<List<RepoEntity>> call, retrofit2.Response<List<RepoEntity>> response) {
                if (response.code() == HttpCode.SUCCESS && null != response.body()
                        && response.body().size() > 0) {
                    List<RepoEntity> repoEntitys = response.body();
                    dataList.addAll(repoEntitys);

                } else {
                    ToastUtils.showLongToast("Error: Can Not Get the User Repos.");
                }


            }

            @Override
            public void onFailure(Call<List<RepoEntity>> call, Throwable t) {
                ToastUtils.showLongToast("Can Not Get the User Repos.");
                Log.e("Scotia", "Error info: " + t.getMessage());
            }
        });

    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl).fit().into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                mIsLoading.setValue(false);
            }

            @Override
            public void onError(Exception e) {
                mIsLoading.setValue(false);
                ToastUtils.showLongToast("Error: Failed to Get the Avatar.");
            }
        });



    }

    private void setUserName(String name) {
        userId.set(name);
    }
}
