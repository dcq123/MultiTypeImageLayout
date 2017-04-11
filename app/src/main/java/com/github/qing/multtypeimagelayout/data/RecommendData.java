package com.github.qing.multtypeimagelayout.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendData {

    private String icon;
    private String title;
    private String whoRecom;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWhoRecom() {
        return whoRecom;
    }

    public void setWhoRecom(String whoRecom) {
        this.whoRecom = whoRecom;
    }

    public static class RandomData {
        private static Random random = new Random();

        private static String[] titles = {
                "现在娱乐圈谁比较火",
                "电影新资讯",
                "Geek史上的今天",
                "ZARA折扣提醒",
                "微博热议",
                "知乎热门",
                "网易轻松一刻"
        };

        private static String[] whoRecoms = {
                "编辑推荐",
                "有人刚刚关注",
                "大家都在看",
                "你可能感兴趣的",
                "近期新鲜推荐",
                "热门搜索",
                "大家力推",
        };

        private static String THUMB_SUFFIX = "?imageView2/1/w/200/h/200/format/webp/q/75|imageslim";

        public static List<RecommendData> random() {
            List<RecommendData> datas = new ArrayList<>();
            int count = random.nextInt(20);
            if (count < 5) {
                count = 5;
            }
            for (int i = 0; i < count; i++) {
                int index = random.nextInt(titles.length - 1);
                RecommendData data = new RecommendData();
                data.setTitle(titles[index]);
                data.setWhoRecom(whoRecoms[index]);
                data.setIcon(ImageUrl.ICONS[random.nextInt(ImageUrl.ICONS.length - 1)] + THUMB_SUFFIX);
                datas.add(data);
            }
            return datas;
        }

    }
}
