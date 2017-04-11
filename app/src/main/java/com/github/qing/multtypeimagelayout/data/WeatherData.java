package com.github.qing.multtypeimagelayout.data;

import com.github.qing.multtypeimagelayout.R;

import java.util.Random;

public class WeatherData {

    private String city;
    private String temper;
    private String type;
    private String quality;
    private int icon;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public static class RandomData {

        private static Random random = new Random();

        private static String[] citys = {
                "北京",
                "上海",
                "深圳",
                "杭州",
                "郑州"
        };

        private static String[] qualitys = {"一般", "良好", "严重污染", "雾霾"};

        private static String[] types = {"晴天", "多云", "阴天", "小雨", "中雨"};

        private static String[] tempers = {"10~14℃", "10~24℃", "4~10℃", "5~12℃", "12~14℃"};

        private static int[] icons = {
                R.mipmap.weather01,
                R.mipmap.weather02,
                R.mipmap.weather03,
                R.mipmap.weather04,
                R.mipmap.weather05
        };

        public static WeatherData random() {
            WeatherData data = new WeatherData();
            data.setCity(citys[random.nextInt(citys.length - 1)]);
            data.setIcon(icons[random.nextInt(icons.length - 1)]);
            data.setType(types[random.nextInt(types.length - 1)]);
            data.setQuality(qualitys[random.nextInt(qualitys.length - 1)]);
            data.setTemper(tempers[random.nextInt(tempers.length - 1)]);
            return data;
        }
    }
}
