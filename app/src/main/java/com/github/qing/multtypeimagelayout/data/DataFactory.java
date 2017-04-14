package com.github.qing.multtypeimagelayout.data;

import com.github.qing.multtypeimagelayout.viewbinder.ContentDataType;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    public static List<Object> createData() {
        return flatTypeData(createMetaData());
    }

    private static JikeData createMetaData() {
        JikeData jikeData = new JikeData();

        jikeData.setHotData(HotData.RandomData.random());
        jikeData.setContentDatas(ContentData.RandomData.random());
        jikeData.setRecommendDatas(RecommendData.RandomData.random());
        jikeData.setWeatherData(WeatherData.RandomData.random());

        return jikeData;
    }

    private static List<Object> flatTypeData(JikeData data) {
        List<Object> items = new ArrayList<>();
        items.add(data.getHotData());
        List<ContentData> contentDatas = data.getContentDatas();
        for (int i = 0; i < contentDatas.size(); i++) {
            ContentData item = contentDatas.get(i);

            // 根据ContentData的type确定构建那种类型的item
            int type = ContentDataType.TYPE_TEXT;
            if (item.getType() == ContentData.Type.TYPE_IMG) {
                if (item.getImg().size() == 0) {
                    type = ContentDataType.TYPE_TEXT;
                } else if (item.getImg().size() == 1) {
                    type = ContentDataType.TYPE_SINGLE_IMAGE;
                } else {
                    type = ContentDataType.TYPE_MULTI_IMAGE;
                }
            } else if (item.getType() == ContentData.Type.TYPE_VIDEO) {
                type = ContentDataType.TYPE_VIDEO;
            } else if (item.getType() == ContentData.Type.TYPE_MUSIC) {
                type = ContentDataType.TYPE_MUSIC;
            }
            // 包装 ContentData 类型 --> ContentDataType 类型，用于1对多的Item映射
            items.add(new ContentDataType(item, type));

            // 随便找了个位置，放置【天气item】和【推荐item】
            if (i == 5) {
                items.add(data.getWeatherData());
            }
            if (i == 8) {
                items.add(new RecommendList(data.getRecommendDatas()));
            }
        }
        return items;
    }
}
