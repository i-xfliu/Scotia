package com.example.scotia.view.main;

import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.scotia.view.base.BaseActivity;
import com.example.scotia.R;
import com.example.scotia.databinding.ActivityMainBinding;
import com.example.scotia.http.Injection;
import com.example.scotia.view.main.adpter.RepoAdapter;
import com.example.scotia.viewModel.main.MainViewModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new MainViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void initUI() {
        initRecyclerView();
    }

    @Override
    protected void initObserver() {
        mViewModel.isLoading().observe(this, aBoolean -> {
            ProgressBar progressBar = mDataBinding.spinKit;
            if (aBoolean) {
                mDataBinding.ivAvatar.setImageDrawable(null);
                mDataBinding.ivAvatar.setVisibility(View.GONE);
                Sprite doubleBounce = new DoubleBounce();
                progressBar.setIndeterminateDrawable(doubleBounce);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
                mDataBinding.ivAvatar.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initRecyclerView() {
        mDataBinding.rvRepos.setAdapter(new RepoAdapter(mViewModel.dataList));
        mDataBinding.rvRepos.setLayoutManager(new LinearLayoutManager(this));
    }
}
