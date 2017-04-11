package com.github.qing.multtypeimagelayout.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class DisplayUtils {

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @return
     */
    public static Point getScreenWH(Context context) {
        Point point = new Point();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        point.set(displayMetrics.widthPixels, displayMetrics.heightPixels);

        return point;
    }

}
