package com.github.qing.multtypeimagelayout.viewbinder;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 抽取公用的内容Item,包含头部标题，icon以及底部收藏、分享等
 */

@SuppressWarnings("unchecked")
public abstract class BaseContentViewBinder<SubViewHolder extends RecyclerView.ViewHolder>
        extends ItemViewBinder<ContentDataType, BaseContentViewBinder.FrameHolder> {

    protected abstract SubViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindContentViewHolder(SubViewHolder holder, @NonNull ContentData content);

    @NonNull
    @Override
    protected FrameHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_base_content_layout, parent, false);
        FrameLayout contentFrame = (FrameLayout) root.findViewById(R.id.contentFrame);
        SubViewHolder subViewHolder = onCreateContentViewHolder(inflater, contentFrame);
        return new FrameHolder<>(root, subViewHolder);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull FrameHolder holder, @NonNull ContentDataType dataType) {

        ContentData item = dataType.getContentData();

        holder.contentTitle.setText(item.getTitle());
        holder.contentLike.setText("" + item.getLike());
        holder.contentMsg.setText("" + item.getComments());
        holder.contentTime.setText(item.getTime());
        holder.content.setText(item.getContent());

        Glide.with(holder.itemView.getContext())
                .load(item.getIcon())
                .into(holder.contentIcon);

        RecyclerView.ViewHolder subViewHolder = holder.subViewHolder;
        if (subViewHolder != null) {
            holder.contentFrame.setVisibility(View.VISIBLE);
            onBindContentViewHolder((SubViewHolder) subViewHolder, item);
        } else {
            holder.contentFrame.setVisibility(View.GONE);
            onBindContentViewHolder(null, item);
        }

        if (item.getType() == 1) {
            holder.typeImg.setImageResource(R.mipmap.link);
        } else if (item.getType() == 2) {
            holder.typeImg.setImageResource(R.mipmap.music);
        } else if (item.getType() == 3) {
            holder.typeImg.setImageResource(R.mipmap.play_gray);
        }

    }

    class FrameHolder<SVH extends RecyclerView.ViewHolder> extends RecyclerView.ViewHolder {

        SVH subViewHolder;

        @BindView(R.id.contentIcon)
        ImageView contentIcon;
        @BindView(R.id.contentTitle)
        TextView contentTitle;
        @BindView(R.id.contentTime)
        TextView contentTime;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.contentFrame)
        FrameLayout contentFrame;
        @BindView(R.id.contentLike)
        TextView contentLike;
        @BindView(R.id.contentMsg)
        TextView contentMsg;
        @BindView(R.id.typeImg)
        ImageView typeImg;


        FrameHolder(View itemView, SVH subViewHolder) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.subViewHolder = subViewHolder;
        }
    }
}
