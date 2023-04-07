package com.example.scotia.view.main.viewholder;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.scotia.R;
import com.example.scotia.databinding.ItemRepoBinding;
import com.example.scotia.viewModel.main.RepoViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * View Holder of the repository view
 */
public class RepoViewHolder extends BaseViewHolder<ItemRepoBinding, RepoViewModel> {

    public RepoViewHolder(@NonNull @NotNull ViewGroup parent) {
        super(parent, R.layout.item_repo);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new RepoViewModel();

    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
