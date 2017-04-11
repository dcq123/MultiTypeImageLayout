package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.Nullable;

import com.github.qing.multtypeimagelayout.data.ContentData;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 类型转换
 */

public class ContentDataType {

    // 纯文本类型
    public static final int TYPE_TEXT = 1;
    // 单张图片类型
    public static final int TYPE_SINGLE_IMAGE = 2;
    // 多图类型
    public static final int TYPE_MULTI_IMAGE = 3;
    // 视频
    public static final int TYPE_VIDEO = 4;
    // 音频
    public static final int TYPE_MUSIC = 5;

    public abstract static class Text {
    }

    public abstract static class SingleImage {
    }

    public abstract static class MultiImage {
    }

    public abstract static class Video {
    }

    public abstract static class Music {
    }

    private ContentData contentData;
    private Class typeClass;

    public ContentDataType(ContentData contentData, int type) {
        this.contentData = contentData;
        this.typeClass = getTypeClass(type);
    }

    public ContentData getContentData() {
        return contentData;
    }

    public Class getTypeClass() {
        return typeClass;
    }

    @Nullable
    private static Class getTypeClass(int type) {
        switch (type) {
            case TYPE_TEXT:
                return Text.class;
            case TYPE_SINGLE_IMAGE:
                return SingleImage.class;
            case TYPE_MULTI_IMAGE:
                return MultiImage.class;
            case TYPE_VIDEO:
                return Video.class;
            case TYPE_MUSIC:
                return Music.class;
        }
        return null;
    }
}
