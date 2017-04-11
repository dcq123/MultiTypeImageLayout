package com.github.qing.multtypeimagelayout.viewbinder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.data.ImageUrl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 纯文本item
 */

public class SingleImgViewBinder extends BaseContentViewBinder<SingleImgViewBinder.SingleImgViewHolder> {


    @Override
    protected SingleImgViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater,
                                                            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_content_single_image, parent, true);
        return new SingleImgViewHolder(view);
    }

    @Override
    protected void onBindContentViewHolder(@NonNull SingleImgViewHolder holder, @NonNull ContentData content) {
        ContentData.ImgBean imgBean = content.getImg().get(0);
        Glide.with(holder.itemView.getContext())
                .load(ImageUrl.thumbUrl(imgBean.getUrl()))
                .placeholder(new ColorDrawable(Color.parseColor("#eeeeee")))
                .override(imgBean.getWidth(), imgBean.getHeight())
                .into(holder.singleImg);
        final Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show();
            }
        });
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
