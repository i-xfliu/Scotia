package com.example.scotia.viewModel.base;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public abstract class BaseItemViewModel<T> extends BaseObservable {

    public final ObservableField<T> baseModel = new ObservableField<>();

    /**
     * obtain the basic model
     *
     * @return base model
     */
    public T getBaseModel() {
        return baseModel.get();
    }

    /**
     * set the base model
     *
     * @param t base model
     */
    public void setBaseModel(T t) {
        baseModel.set(t);

        if (t != null) {
            setModel(t);
        }
    }

    /**
     * implement this method to get the model
     *
     * @param t base model
     */
    protected abstract void setModel(@NonNull T t);
}

