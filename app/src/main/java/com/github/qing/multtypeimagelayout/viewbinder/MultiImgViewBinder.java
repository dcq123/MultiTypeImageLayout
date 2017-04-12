package com.github.qing.multtypeimagelayout.viewbinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.adapter.MultiImgAdapter;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.manager.ImgGridLayoutManager;
import com.github.qing.multtypeimagelayout.photo.PhotoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 多图item
 */

public class MultiImgViewBinder extends BaseContentViewBinder<MultiImgViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateContentViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_mult_img_layout, parent, true));
    }

    @Override
    protected void onBindContentViewHolder(ViewHolder holder, @NonNull ContentData content) {
        holder.setData(content.getImg());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.multImgRecyclerView)
        RecyclerView multiImgRecyclerView;
        MultiImgAdapter adapter;
        ImgGridLayoutManager layoutManager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initRecycler();
        }

        private void initRecycler() {
            layoutManager = new ImgGridLayoutManager(itemView.getContext());
            multiImgRecyclerView.setLayoutManager(layoutManager);
            adapter = new MultiImgAdapter(multiImgRecyclerView);
            multiImgRecyclerView.setAdapter(adapter);
        }


        private void setData(final List<ContentData.ImgBean> list) {
            layoutManager.setTotalSize(list.size());
            adapter.setData(list);
            multiImgRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
            adapter.setListener(new MultiImgAdapter.OnImageClickListener() {
                @Override
                public void onImageClick(int position) {
                    showPhoto(list, position);
                }
            });
        }

        private void showPhoto(List<ContentData.ImgBean> list, int index) {
            String[] urls = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                urls[i] = list.get(i).getUrl();
            }
            int childCount = multiImgRecyclerView.getChildCount();
            ArrayList<Rect> rects = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                View view = multiImgRecyclerView.getLayoutManager().findViewByPosition(i);
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                rects.add(rect);
            }

            Context context = itemView.getContext();
            Intent intent = new Intent(context, PhotoActivity.class);
            intent.putExtra(PhotoActivity.KEY_INDEX, index);
            intent.putExtra(PhotoActivity.KEY_IMG_URL, urls);
            intent.putExtra(PhotoActivity.KEY_RECT, rects);
            context.startActivity(intent);
        }
    }
}
