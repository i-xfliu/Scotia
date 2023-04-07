package com.example.scotia.http.utils;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.schedulers.SingleScheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HttpUtils {
    public static <T> ObservableTransformer<T, T> applySingleScheduler() {
        return new ObservableTransformer<T, T>() {

            @Override
            public io.reactivex.rxjava3.core.@NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(new SingleScheduler());
            }
        };
    }
}
