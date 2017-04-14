package com.github.qing.multtypeimagelayout.photo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ImageUrl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/12.
 * <p>
 * 使用PhotoView显示单张图片的Fragment
 */

public class PhotoFragment extends LazyFragment {

    public static final String KEY_IMG_URL = "img_url";
    public static final String KEY_START_BOUND = "startBounds";

    @BindView(R.id.photoView)
    SmoothImageView photoView;
    @BindView(R.id.rootView)
    View rootView;
    @BindView(R.id.progressContainer)
    View progress;

    private String imgUrl;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_layout, container, false);
        ButterKnife.bind(this, view);

        initArgs();
        initView();

        return view;
    }


    private void initArgs() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(KEY_IMG_URL)) {
            imgUrl = args.getString(KEY_IMG_URL);
            Rect startBounds = args.getParcelable(KEY_START_BOUND);
            photoView.setThumbRect(startBounds);

            // 先加在小图中Glide已经缓存过的图片，在Fragment显示的时，再加载清晰的大图
            Glide.with(this).load(ImageUrl.webplUrl(imgUrl)).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (photoView.getDrawable() == null) {
                        photoView.setImageBitmap(resource);
                    }
                }
            });
        }
    }

    private void initView() {
        photoView.setMinimumScale(1f);
        photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                if (photoView.checkMinScale()) {
                    ((PhotoActivity) getActivity()).transformOut();
                }
            }
        });

        photoView.setAlphaChangeListener(new SmoothImageView.OnAlphaChangeListener() {
            @Override
            public void onAlphaChange(int alpha) {
                rootView.setBackgroundColor(getColorWithAlpha(alpha / 255f, Color.BLACK));
            }
        });

        photoView.setTransformOutListener(new SmoothImageView.OnTransformOutListener() {
            @Override
            public void onTransformOut() {
                if (photoView.checkMinScale()) {
                    ((PhotoActivity) getActivity()).transformOut();
                }
            }
        });
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    private boolean isLoaded = false;
    private boolean isLoadFinish = false;

    @Override
    protected void onLazy() {
        if (isLoaded) {
            return;
        }
        isLoaded = true;
        Glide.with(this).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {

            @Override
            public void onLoadStarted(Drawable placeholder) {
                progress.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isLoadFinish) {
                            progress.setVisibility(View.VISIBLE);
                        }
                    }
                }, 400);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                isLoadFinish = true;
                progress.setVisibility(View.GONE);
                photoView.setImageBitmap(bitmap);
            }
        });
    }

    public void transformIn() {
        photoView.transformIn(new SmoothImageView.onTransformListener() {
            @Override
            public void onTransformCompleted(SmoothImageView.Status status) {
                rootView.setBackgroundColor(Color.BLACK);
            }
        });
    }

    public void transformOut(SmoothImageView.onTransformListener listener) {
        photoView.transformOut(listener);
    }

    public void changeBg(int color) {
        rootView.setBackgroundColor(color);
    }
}
