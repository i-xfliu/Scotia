package com.example.scotia.view.main.adpter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import com.example.scotia.view.base.adapter.BaseAdapter;
import com.example.scotia.model.RepoEntity;
import com.example.scotia.view.main.viewholder.RepoViewHolder;
import org.jetbrains.annotations.NotNull;

/**
 * adapter for the repository recylcerView
 */
public class RepoAdapter extends BaseAdapter<RepoViewHolder> {


    public RepoAdapter(@NonNull @NotNull ObservableList<Object> dataList) {
        super(dataList);
        mDataList = dataList;
    }

    @NonNull
    @NotNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RepoViewHolder holder, int position) {
        Object data = mDataList.get(position);
        holder.getViewModel().setBaseModel((RepoEntity) data);

    }
}
