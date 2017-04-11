package com.github.qing.multtypeimagelayout.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 九宫格布局Manager
 */

public class ImgGridLayoutManager extends GridLayoutManager {

    public static final int SPAN_SIZE = 6;
    private int totalSize = 0;

    public ImgGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public ImgGridLayoutManager(Context context) {
        super(context, SPAN_SIZE);
        init();
    }

    public ImgGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        init();
    }

    private void init() {
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int total = getTotalSize();
                if (total == 1) {
                    return SPAN_SIZE;
                } else if (total == 2 || total == 3) {
                    return SPAN_SIZE / total;
                } else if (total == 4 || total == 7) {
                    if (position == 0) {
                        return SPAN_SIZE;
                    } else {
                        return SPAN_SIZE / 3;
                    }
                } else if (total == 5 || total == 8) {
                    if (position == 0 || position == 1) {
                        return SPAN_SIZE / 2;
                    } else {
                        return SPAN_SIZE / 3;
                    }
                } else {
                    return SPAN_SIZE / 3;
                }
            }
        });
    }


    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
