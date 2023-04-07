package com.example.scotia.viewModel.base;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class BaseViewModel extends BaseObservable implements DefaultLifecycleObserver {



    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }


}

