package com.github.qing.multtypeimagelayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.RecommendData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 推荐中的card
 */

public class RecommendCardAdapter extends RecyclerView.Adapter<RecommendCardAdapter.ViewHolder> {

    List<RecommendData> datas;

    public void setDatas(List<RecommendData> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RecommendData data = datas.get(position);
        holder.recomTitle.setText(data.getTitle());
        holder.recomWho.setText(data.getWhoRecom());
        Glide.with(holder.itemView.getContext())
                .load(data.getIcon())
                .into(holder.recomImage);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recomImage)
        ImageView recomImage;
        @BindView(R.id.recomTitle)
        TextView recomTitle;
        @BindView(R.id.recomWho)
        TextView recomWho;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
