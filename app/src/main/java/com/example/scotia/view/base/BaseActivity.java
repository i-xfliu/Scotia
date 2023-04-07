package com.example.scotia.view.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.scotia.viewModel.base.BaseViewModel;
import com.example.scotia.R;
import com.example.scotia.databinding.ActivityBaseBinding;
import android.content.Intent;

/**
 * the root activity
 * @param <DB>
 * @param <VM>
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity {
    protected DB mDataBinding;
    protected VM mViewModel;
    private ActivityBaseBinding mActivityBaseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(this.getIntent());

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutResId(),
                mActivityBaseBinding.baseContentContainer, true);

        initViewModel();
        bindViewModel();

        initUI();
        initObserver();

        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * handle the intent passed
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {

    }


    /**
     * get the layout id
     *
     * @return resId
     */
    protected abstract int getLayoutResId();

    /**
     * init the viewModel
     */
    protected abstract void initViewModel();

    /**
     * bidn the view Model
     */
    protected abstract void bindViewModel();

    /**
     * init the UI
     */
    protected abstract void initUI();

    /**
     * init the observer
     */
    protected abstract void initObserver();



}
