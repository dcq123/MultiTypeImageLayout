package com.github.qing.multtypeimagelayout.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.data.ImageUrl;
import com.github.qing.multtypeimagelayout.manager.ImgGridLayoutManager;
import com.github.qing.multtypeimagelayout.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 9宫格图片布局Adapter
 */

public class MultiImgAdapter extends RecyclerView.Adapter<MultiImgAdapter.ViewHolder> {
    private int itemViewWidth = 0;
    private List<ContentData.ImgBean> data = new ArrayList<>();

    private ImgGridLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private OnImageClickListener listener;

    public MultiImgAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.layoutManager = (ImgGridLayoutManager) recyclerView.getLayoutManager();
    }

    public void setData(List<ContentData.ImgBean> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ContentData.ImgBean imgBean = data.get(position);
//        String url = ImageUrl.thumbUrl(imgBean.getUrl());
        Context context = holder.itemView.getContext();
        // 获取9宫格itemView的宽度
        if (itemViewWidth == 0) {
            itemViewWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingLeft() - recyclerView.getPaddingRight();
        }
        if (itemViewWidth == 0) {
            itemViewWidth = (int) (DisplayUtils.getScreenWH(context).x - context.getResources().getDimension(R.dimen.item_padding) * 2);
        }
        // 根据Grid item 的位置，重新设置图片的宽高
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.imageView.getLayoutParams();
        int spanSize = layoutManager.getSpanSizeLookup().getSpanSize(position);
        // 占据一整行的item，高度设置为宽度的0.4
        if (spanSize == ImgGridLayoutManager.SPAN_SIZE) {
            if (itemViewWidth != 0) {
                layoutParams.height = (int) (itemViewWidth * 0.4f);
                holder.imageView.requestLayout();
            }
//            url = ImageUrl.normalUrl(imgBean.getUrl());
        } else {
            // 其他行的Item,宽高设置为相等
            if (itemViewWidth != 0) {
                int count = ImgGridLayoutManager.SPAN_SIZE / spanSize;
                layoutParams.height = (itemViewWidth - (layoutParams.leftMargin - layoutParams.rightMargin) * count) / count - layoutParams.topMargin - layoutParams.bottomMargin;
                holder.imageView.requestLayout();
            }
        }
        final String url = ImageUrl.webplUrl(imgBean.getUrl());
        holder.imageView.setTag(url);
//        ImageLoader.loadImage(holder.itemView.getContext().getApplicationContext(), ImageUrl.webplUrl(imgBean.getUrl()), holder.imageView);
        Glide.with(holder.itemView.getContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Object tag = holder.imageView.getTag();
                        if (tag != null && tag.equals(url)) {
                            holder.imageView.setImageBitmap(resource);
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnImageClickListener {
        void onImageClick(int position);
    }

    public void setListener(OnImageClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && v instanceof ImageView) {
                        listener.onImageClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
