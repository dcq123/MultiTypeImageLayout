package com.github.qing.multtypeimagelayout.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 图片加载
 */

public class ImageLoader {


    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(new ColorDrawable(Color.parseColor("#eeeeee")))
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView, Transformation transformation) {
        Glide.with(context)
                .load(url)
                .placeholder(new ColorDrawable(Color.parseColor("#eeeeee")))
                .bitmapTransform(transformation)
                .into(imageView);
    }
}
