package com.github.qing.multtypeimagelayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.data.ImageUrl;
import com.github.qing.multtypeimagelayout.manager.ImgGridLayoutManager;
import com.github.qing.multtypeimagelayout.utils.ImageLoader;

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
    private int rvWidth = 0;
    private List<ContentData.ImgBean> data = new ArrayList<>();

    private ImgGridLayoutManager layoutManager;
    private RecyclerView recyclerView;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentData.ImgBean imgBean = data.get(position);
        String url = ImageUrl.thumbUrl(imgBean.getUrl());
        if (rvWidth == 0) {
            rvWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingLeft() - recyclerView.getPaddingRight();
            System.out.println("rvWidth:" + rvWidth);
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.imageView.getLayoutParams();
        int spanSize = layoutManager.getSpanSizeLookup().getSpanSize(position);
        if (spanSize == ImgGridLayoutManager.SPAN_SIZE) {
            if (rvWidth != 0) {
                layoutParams.height = (int) (rvWidth * 0.5f);
                holder.imageView.requestLayout();
            }
            url = ImageUrl.normalUrl(imgBean.getUrl());
        } else {
            if (rvWidth != 0) {
                int count = ImgGridLayoutManager.SPAN_SIZE / spanSize;
                layoutParams.height = (rvWidth - (layoutParams.leftMargin - layoutParams.rightMargin) * count) / count - layoutParams.topMargin - layoutParams.bottomMargin;
                holder.imageView.requestLayout();
            }
        }
        ImageLoader.loadImage(holder.itemView.getContext(), url, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
