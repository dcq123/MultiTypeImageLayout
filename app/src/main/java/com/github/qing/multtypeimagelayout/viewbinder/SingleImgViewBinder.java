package com.github.qing.multtypeimagelayout.viewbinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.data.ImageUrl;
import com.github.qing.multtypeimagelayout.photo.PhotoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 单张图片item
 */

public class SingleImgViewBinder extends BaseContentViewBinder<SingleImgViewBinder.SingleImgViewHolder> {


    @Override
    protected SingleImgViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater,
                                                            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_content_single_image, parent, true);
        return new SingleImgViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(@NonNull final SingleImgViewHolder holder, @NonNull ContentData content) {
        final ContentData.ImgBean imgBean = content.getImg().get(0);
        Glide.with(holder.itemView.getContext())
                .load(ImageUrl.webplUrl(imgBean.getUrl()))
                .placeholder(new ColorDrawable(Color.parseColor("#eeeeee")))
                .override(imgBean.getWidth(), imgBean.getHeight())
                .into(holder.singleImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoto(holder.singleImg, imgBean.getUrl());
            }
        });
    }

    private void showPhoto(ImageView imageView, String url) {
        String[] urls = {url};
        ArrayList<Rect> rects = new ArrayList<>();
        Rect rect = new Rect();
        imageView.getGlobalVisibleRect(rect);
        rects.add(rect);

        Context context = imageView.getContext();
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(PhotoActivity.KEY_INDEX, 0);
        intent.putExtra(PhotoActivity.KEY_IMG_URL, urls);
        intent.putExtra(PhotoActivity.KEY_RECT, rects);
        context.startActivity(intent);
    }

    static class SingleImgViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.singleImage)
        ImageView singleImg;

        SingleImgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
