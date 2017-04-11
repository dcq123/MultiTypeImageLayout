package com.github.qing.multtypeimagelayout.data;

public class DataFactory {

    public static JikeData createNewData() {
        JikeData jikeData = new JikeData();

        jikeData.setHotData(HotData.RandomData.random());
        jikeData.setContentDatas(ContentData.RandomData.random());
        jikeData.setRecommendDatas(RecommendData.RandomData.random());
        jikeData.setWeatherData(WeatherData.RandomData.random());

        return jikeData;
    }
}
