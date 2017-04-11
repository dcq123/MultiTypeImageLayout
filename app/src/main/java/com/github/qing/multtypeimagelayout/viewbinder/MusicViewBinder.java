package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dcq on 2017/4/11.
 * <p>
 * 音乐item
 */

public class MusicViewBinder extends BaseContentViewBinder<MusicViewBinder.ViewHolder> {


    @Override
    protected ViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_music_layout, parent, true));
    }

    @Override
    protected void onBindContentViewHolder(ViewHolder holder, @NonNull ContentData content) {
        ContentData.MusicBean music = content.getMusic();
        holder.musicName.setText(music.getMscName());
        holder.musicSinger.setText(music.getMscSinger());


        ImageLoader.loadImage(holder.itemView.getContext(), music.getMscIcon(), holder.musicIcon,
                new RoundedCornersTransformation(holder.itemView.getContext(), 10, 0));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.musicIcon)
        ImageView musicIcon;
        @BindView(R.id.musicName)
        TextView musicName;
        @BindView(R.id.musicSinger)
        TextView musicSinger;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
