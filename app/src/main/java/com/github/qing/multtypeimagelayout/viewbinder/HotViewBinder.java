package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.HotData;
import com.github.qing.multtypeimagelayout.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 最近热门item
 */

public class HotViewBinder extends ItemViewBinder<HotData, HotViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_hot_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HotData item) {

        holder.content.setText(item.getContent());
        holder.hotSource.setText("来源：" + item.getSource());
        ImageLoader.loadImage(holder.itemView.getContext(), item.getIcon(), holder.hotImage);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.hotImage)
        ImageView hotImage;
        @BindView(R.id.hotSource)
        TextView hotSource;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
