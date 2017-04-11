package com.github.qing.multtypeimagelayout.data;


import java.util.List;

public class JikeData {

    private HotData hotData;
    private List<RecommendData> recommendDatas;
    private WeatherData weatherData;
    private List<ContentData> contentDatas;

    public HotData getHotData() {
        return hotData;
    }

    public void setHotData(HotData hotData) {
        this.hotData = hotData;
    }

    public List<RecommendData> getRecommendDatas() {
        return recommendDatas;
    }

    public void setRecommendDatas(List<RecommendData> recommendDatas) {
        this.recommendDatas = recommendDatas;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public List<ContentData> getContentDatas() {
        return contentDatas;
    }

    public void setContentDatas(List<ContentData> contentDatas) {
        this.contentDatas = contentDatas;
    }
}
