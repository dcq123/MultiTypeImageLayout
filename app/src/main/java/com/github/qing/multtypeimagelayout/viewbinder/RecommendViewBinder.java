package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.qing.itemdecoration.LinearSpaceItemDecoration;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.adapter.RecommendCardAdapter;
import com.github.qing.multtypeimagelayout.data.RecommendData;
import com.github.qing.multtypeimagelayout.data.RecommendList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 推荐item
 */

public class RecommendViewBinder extends ItemViewBinder<RecommendList, RecommendViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_recommend_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull RecommendList item) {
        if (!holder.isInit) {
            holder.initRecyclerView();
        }
        holder.setData(item.getRecommendDataList());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recommendRecyclerView)
        RecyclerView recommendRecyclerView;
        RecommendCardAdapter adapter;
        boolean isInit = false;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            initRecyclerView();
        }

        private void initRecyclerView() {
            isInit = true;
            adapter = new RecommendCardAdapter();
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recommendRecyclerView.setLayoutManager(layoutManager);
            recommendRecyclerView.setAdapter(adapter);
            recommendRecyclerView.addItemDecoration(
                    new LinearSpaceItemDecoration.Builder()
                            .setOrientation(LinearLayoutManager.HORIZONTAL)
                            .setSpaceSize(12)
                            .build()
            );
        }

        private void setData(List<RecommendData> list) {
            if (adapter != null) {
                adapter.setDatas(list);
            }
        }
    }
}
