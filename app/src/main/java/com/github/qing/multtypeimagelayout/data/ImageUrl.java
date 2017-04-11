package com.github.qing.multtypeimagelayout.data;

public class ImageUrl {

    /**
     * 缩略图后缀
     */
    private static String THUMB_SUFFIX = "?imageView2/1/w/200/h/200/format/webp/q/75|imageslim";
    /**
     * 正常图片后缀
     */
    private static String NORMAL_SUFFIX = "?imageView2/0/format/webp/q/75|imageslim";

    public static String[] URLS = {
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/01.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/05.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/12378628_1342603613473.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/12378628_1342603613475.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/13464336_1346396602086_800x600.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/1621215101-0.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/2010071529420095.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/23151400_1373424309126_800x600.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/3904.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/4U718R3B5607.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/500fd9f9d72a605911bbcd082b34349b033bba7c.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/52734bbec6cad.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/53df2e6510d13.jpg",
            "http://onxvhxvw6.bkt.clouddn.com/image/fengjing/5895522.jpg",
    };

    public static String[] ICONS = {
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/App_807px_1128336_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/App_Store_114px_1076591_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Apple_App_Store_256px_1131811_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Bubbles_1024px_1184591_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Connected_1024px_1184588_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Crossed_1024px_1184587_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Disc_1024px_1184586_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Dots_1024px_1184584_easyicon.net%20%281%29.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Festive_1024px_1184582_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Payments_1024px_1184574_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Pieces_1024px_1184573_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Stream_1024px_1184568_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Sun_1024px_1184567_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Ticks_1024px_1184566_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/Weather_1024px_1184564_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/aple_app_512px_1143679_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/app_drawer_96px_1110305_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/google_android_apps_reader_app_streamlistactivity_111px_1135342_easyicon.net.png",
            "http://onxvhxvw6.bkt.clouddn.com/image/icon/iCal_App_512px_561900_easyicon.net.png",
    };

    public static String thumbUrl(String url) {
        return url + THUMB_SUFFIX;
    }

    public static String normalUrl(String url) {
        return url + NORMAL_SUFFIX;
    }

}
