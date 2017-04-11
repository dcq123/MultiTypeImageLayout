package com.github.qing.multtypeimagelayout.data;

import java.util.Random;

public class HotData {

    private String content;
    private String source;
    private String icon;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public static class RandomData {
        private static Random random = new Random();

        private static String[] contents = {
                "你的室友能提供给你最大限度的安慰是「我也没开始做呢」。",
                "婚后发现妻子以前的经历十分复杂，该怎么相处？",
                "浅谈产品经理的8大核心工作"
        };

        private static String[] sources = {
                "人人都是产品经理",
                "浴室沉思",
                "抽屉新热榜每日最热"
        };

        private static String[] icons = {
                "http://onxvhxvw6.bkt.clouddn.com/image/jike/jike_1491801948230_pic.jpeg",
                "http://onxvhxvw6.bkt.clouddn.com/image/jike/jike_1491801956529_pic.jpeg",
                "http://onxvhxvw6.bkt.clouddn.com/image/jike/jike_1491801964316_pic.jpeg"
        };


        public static HotData random() {
            HotData hotData = new HotData();
            hotData.setContent(contents[random.nextInt(contents.length - 1)]);
            hotData.setIcon(icons[random.nextInt(icons.length - 1)]);
            hotData.setSource(sources[random.nextInt(sources.length - 1)]);
            return hotData;
        }
    }

}
