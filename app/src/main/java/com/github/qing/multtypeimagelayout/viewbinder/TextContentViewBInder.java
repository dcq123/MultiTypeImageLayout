package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.qing.multtypeimagelayout.data.ContentData;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 纯文本item
 */

public class TextContentViewBInder extends BaseContentViewBinder {

    @Override
    protected RecyclerView.ViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return null;
    }

    @Override
    protected void onBindContentViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull ContentData content) {

    }
}
