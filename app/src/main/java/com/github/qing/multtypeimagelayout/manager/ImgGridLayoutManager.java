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
        /**
         * 此处使用Grid每个item占几个Span来实现九宫格的效果，
         * 默认每行6个item,实际最多显示3张图片，所以常规情况下，每张图片占据2个item的span
         */
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int total = getTotalSize();
                // 当只有一张图片时，直接占据整行
                if (total == 1) {
                    return SPAN_SIZE;
                }
                // 当只有2张时，应该每张占据3个span，当只有3张时，应该每张占据2个span
                else if (total == 2 || total == 3) {
                    return SPAN_SIZE / total;
                }
                // 当有4张或7张时，第一张会占据整行，其余的每个item占2个span
                else if (total == 4 || total == 7) {
                    if (position == 0) {
                        return SPAN_SIZE;
                    } else {
                        return SPAN_SIZE / 3;
                    }
                }
                // 当5张或8张时，前两张图片会均分整行，也就是每个占据3个span，其余的3张占据一行，所以每个占2个span
                else if (total == 5 || total == 8) {
                    if (position == 0 || position == 1) {
                        return SPAN_SIZE / 2;
                    } else {
                        return SPAN_SIZE / 3;
                    }
                }
                // 其余情况，也就是6和9，他们刚好每行都可以占据3张图片，且每张占据2个span
                else {
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
