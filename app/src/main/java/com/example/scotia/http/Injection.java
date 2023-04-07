package com.example.scotia.http;

import com.example.scotia.model.data.DataManager;
import com.example.scotia.model.data.api.ApiService;

public final class Injection {

    private Injection() {

    }

    /**
     * init DataManager
     *
     * @return DataManager
     */
    public static DataManager provideDataManager() {
        return DataManager.getInstance(HttpDataSource.getInstance(RetrofitManager.get().create(ApiService.class)));
    }
}
