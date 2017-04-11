package com.github.qing.multtypeimagelayout.viewbinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.qing.multtypeimagelayout.NewsActivity;
import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 抽取公用的内容Item,包含头部标题，icon以及底部收藏、分享等，具体不同类型的内容，再通过一个ViewHolder去create和bind
 */

@SuppressWarnings("unchecked")
public abstract class BaseContentViewBinder<SubViewHolder extends RecyclerView.ViewHolder>
        extends ItemViewBinder<ContentDataType, BaseContentViewBinder.FrameHolder> {

    /**
     * 定义item中不同内容的ViewHolder的创建
     *
     * @param inflater
     * @param parent   R.id.contentFrame对应的FrameLayout
     * @return
     */
    protected abstract SubViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    /**
     * 绑定内容布局中的数据
     *
     * @param holder  内容对应的ViewHolder
     * @param content 具体item的数据
     */
    protected abstract void onBindContentViewHolder(SubViewHolder holder, @NonNull ContentData content);

    @NonNull
    @Override
    protected FrameHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_base_content_layout, parent, false);
        FrameLayout contentFrame = (FrameLayout) root.findViewById(R.id.contentFrame);
        // 在此创建子内容ViewHolder，并设置给父容器的ViewHolder
        SubViewHolder subViewHolder = onCreateContentViewHolder(inflater, contentFrame);
        return new FrameHolder<>(root, subViewHolder);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull FrameHolder holder, @NonNull ContentDataType dataType) {

        final ContentData item = dataType.getContentData();

        holder.contentTitle.setText(item.getTitle());
        holder.contentLike.setText("" + item.getLike());
        holder.contentMsg.setText("" + item.getComments());
        holder.contentTime.setText(item.getTime());
        holder.content.setText(item.getContent());

        Glide.with(holder.itemView.getContext())
                .load(item.getIcon())
                .into(holder.contentIcon);

        // 获取包含的子内容ViewHolder，根据是否设置了ViewHolder，来决定是否显示内容区域
        RecyclerView.ViewHolder subViewHolder = holder.subViewHolder;
        if (subViewHolder != null) {
            holder.contentFrame.setVisibility(View.VISIBLE);
            // 回调内容ViewHolder的数据绑定过程
            onBindContentViewHolder((SubViewHolder) subViewHolder, item);
        } else {
            holder.contentFrame.setVisibility(View.GONE);
            onBindContentViewHolder(null, item);
        }

        if (item.getType() == ContentData.Type.TYPE_IMG) {
            holder.typeImg.setImageResource(R.mipmap.link);
        } else if (item.getType() == ContentData.Type.TYPE_MUSIC) {
            holder.typeImg.setImageResource(R.mipmap.music);
        } else if (item.getType() == ContentData.Type.TYPE_VIDEO) {
            holder.typeImg.setImageResource(R.mipmap.play_gray);
        }

        final Context context = holder.itemView.getContext();

        // 点击收藏
        holder.ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
            }
        });
        // 发表评论
        holder.ivMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "发表评论", Toast.LENGTH_SHORT).show();
            }
        });
        // 分享
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            }
        });

        // item 点击跳转
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra(NewsActivity.KEY_TITLE, item.getTitle());
                intent.putExtra(NewsActivity.KEY_URL, item.getNewsUrl());
                context.startActivity(intent);
            }
        });
    }

    class FrameHolder<SVH extends RecyclerView.ViewHolder> extends RecyclerView.ViewHolder {

        // 内容对应的ViewHolder
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
        @BindView(R.id.ivHeart)
        ImageView ivHeart;
        @BindView(R.id.ivMsg)
        ImageView ivMsg;
        @BindView(R.id.ivShare)
        ImageView ivShare;


        FrameHolder(View itemView, SVH subViewHolder) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.subViewHolder = subViewHolder;
        }
    }
}
