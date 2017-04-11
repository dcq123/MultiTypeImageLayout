package com.github.qing.multtypeimagelayout.viewbinder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * Created by dcq on 2017/4/11.
 * <p>
 * 视频类型item
 */

public class VideoViewBinder extends BaseContentViewBinder<VideoViewBinder.ViewHolder> {


    @Override
    protected ViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_video_layout, parent, true));
    }

    @Override
    protected void onBindContentViewHolder(ViewHolder holder, @NonNull ContentData content) {
        ContentData.VideoBean video = content.getVideo();
        ImageLoader.loadImage(holder.itemView.getContext(), video.getVideoImageUrl(), holder.videoImg,
                new ColorFilterTransformation(holder.itemView.getContext(), Color.parseColor("#66000000")));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.videoImg)
        ImageView videoImg;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "播放视频", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
