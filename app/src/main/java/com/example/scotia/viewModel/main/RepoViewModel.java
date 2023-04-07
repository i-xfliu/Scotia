package com.example.scotia.viewModel.main;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import com.example.scotia.viewModel.base.BaseItemViewModel;
import com.example.scotia.R;
import com.example.scotia.databinding.PopItemBinding;
import com.example.scotia.model.RepoEntity;
import org.jetbrains.annotations.NotNull;

/**
 * The VM for the repo view (git info detail)
 */
public class RepoViewModel extends BaseItemViewModel<RepoEntity> {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> forks = new ObservableField<>();
    public final ObservableField<String> stars = new ObservableField<>();
    public final ObservableField<String> update = new ObservableField<>();
    @Override
    protected void setModel(@NonNull @NotNull RepoEntity repository) {
        name.set(repository.getName());
        description.set(repository.getDescription());
        forks.set(repository.getForks() + "");
        stars.set(repository.getStars() + "");
        update.set(repository.getUpdate());

    }

    public void onClickItem(View view) {
        RepoEntity repository = getBaseModel();
        if (null != repository) {
            PopupWindow pop = new PopupWindow(view.getContext());
            PopItemBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.pop_item, null, false);
            pop.setContentView(binding.getRoot());
            binding.setViewModel(this);
            pop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            pop.update();
            pop.showAtLocation(view, Gravity.CENTER, 0, 0);
        }
    }
}
