package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.Nullable;

import com.github.qing.multtypeimagelayout.data.ContentData;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 类型转换
 */

public class ContentDataType {

    public abstract static class Text {
    }

    public abstract static class SingleImage {
    }

    public abstract static class MultImage {
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
            case 1:
                return Text.class;
            case 2:
                return SingleImage.class;
            case 3:
                return MultImage.class;
            case 4:
                return Video.class;
            case 5:
                return Music.class;
        }
        return null;
    }
}
