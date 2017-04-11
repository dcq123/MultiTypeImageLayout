package com.github.qing.multtypeimagelayout.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContentData {


    /**
     * icon : 图标
     * title : 标题
     * time : 时间
     * content : 文本内容
     * type : 1.图片、2.音频、3.视频
     * like : 收藏个数
     * comments : 评论个数
     * share : 分享信息
     * img : [{"url":"图片地址","width":"宽度","height":"高度"}]
     * video : {"videoImageUrl":"视频封面地址","videoUrl":"视频地址"}
     * music : {"mscIcon":"音乐封面","mscName":"音乐名称","mscSinger":"演唱者","mscUrl":"音乐获取地址"}
     */

    private String icon;
    private String title;
    private String time;
    private String content;
    private int type;
    private int like;
    private int comments;
    private VideoBean video;
    private MusicBean music;
    private List<ImgBean> img;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public MusicBean getMusic() {
        return music;
    }

    public void setMusic(MusicBean music) {
        this.music = music;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }


    public static class RandomData {
        private static Random random = new Random();

        private static String[] titles = {
                "今天微博都在热议什么",
                "值得一看的长文章",
                "越狱第五季",
                "果壳精选",
                "睡前一张美女图",
                "知乎最新",
                "微博最热话题",
                "微信新动向",
                "Mac高效应用推荐",
                "每日冷笑话精选"
        };

        private static String[] contents = {
                "iconmonstr网站收录了超过3600个图标，并对其进行了分类。每一个图标都提供了SVG, AI, PSD, PNG, FONT等格式",
                "Captain Icon收录了超过350个漂亮，漂亮，漂亮(重说三)的图标",
                "现在能有意外结局还让人颠覆的故事，真的很难得了。结局半小时的反转再反转，能自圆其说，能刷新认识，能大呼过瘾，对一部悬疑推理片来说已经完成的相当漂亮。不会苛求太多，这样的故事和设定已经满足。",
                "最近苏有朋导演的中国版《嫌疑人X的献身》正在上映，却不知这部原版有多经典。本片改编自日本人气推理小说家东野圭吾06年的直木奖获奖作品，福山雅治、柴崎幸、堤真一主演…逻辑和推理，终究敌不过对一个人的深爱。",
                "抱着对法庭题材的偏见去看，以为会很沉闷枯燥，没想到竟是一部够回味好几天的片子。不仅仅因为惊艳的结尾，整部戏就像让观者身临其境一样",
                "前面铺垫紧张到冒汗，倒数第10分钟，剧情反转",
                "原话剧精妙的剧本、对白、场景、悬念，甚至演员的演技，很好的通过电视电影的形式展现，并无限放大其精髓和魅力。",
                "自 iOS 7 以来，App 的图标愈发复杂而精致。尽管每个人都知道苹果注重细节，然而并不是每个人都能在这微小变化中发现其背后复杂的设计逻辑。",
                "我日常当然不会使用这台手机，所以只安装了几个优化APP以及谷歌框架，没有任何日常APP",
                "实验用APP名单：爱奇艺、百度贴吧、百度地图、滴滴出行、美团、去哪儿旅行、QQ、QQ音乐、手机京东、手机淘宝、腾讯新闻、UC浏览器、微博、微信、支付宝、百度手机助手",
                "《人民的名义》已经成为一部现象级作品，无论城市乡村，还是老少男女都看得过瘾，知识分子拍手叫好，投资方名利双收。",
                "“蓝瘦香菇”梗出来的时候，知乎上有个问题，大致是疑惑这种一点也不好笑的东西为什么一夜之间就火了。",
                "随着互联网这张饼越来越大，越来越多的资本发现它有利可图逐步介入。"
        };

        public static List<ContentData> random() {
            List<ContentData> datas = new ArrayList<>();

            int count = random.nextInt(100);
            if (count < 20) {
                count = 20;
            }
            for (int i = 0; i < count; i++) {
                ContentData data = new ContentData();
                data.setTitle(titles[random.nextInt(titles.length - 1)]);
                data.setIcon(ImageUrl.thumbUrl(ImageUrl.ICONS[random.nextInt(ImageUrl.ICONS.length - 1)]));
                data.setComments(random.nextInt(200));
                data.setContent(contents[random.nextInt(contents.length - 1)]);
                data.setLike(random.nextInt(100));
                data.setTime("10:23");

                int type = random.nextInt(4);
                if (type == 0) {
                    type = 1;
                }
                data.setType(type);

                if (type == 1) {
                    data.setImg(ImgBean.RandomData.random());
                } else if (type == 2) {
                    data.setMusic(MusicBean.RandomData.random());
                } else {
                    data.setVideo(VideoBean.RandomData.random());
                }

                datas.add(data);
            }

            return datas;
        }
    }

    public static class VideoBean {
        /**
         * videoImageUrl : 视频封面地址
         * videoUrl : 视频地址
         */

        private String videoImageUrl;
        private String videoUrl;

        public String getVideoImageUrl() {
            return videoImageUrl;
        }

        public void setVideoImageUrl(String videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public static class RandomData {
            private static Random random = new Random();

            public static VideoBean random() {
                VideoBean videoBean = new VideoBean();
                videoBean.setVideoImageUrl(ImageUrl.normalUrl(ImageUrl.URLS[random.nextInt(ImageUrl.URLS.length - 1)]));
                return videoBean;
            }
        }
    }

    public static class MusicBean {
        /**
         * mscIcon : 音乐封面
         * mscName : 音乐名称
         * mscSinger : 演唱者
         * mscUrl : 音乐获取地址
         */

        private String mscIcon;
        private String mscName;
        private String mscSinger;
        private String mscUrl;

        public String getMscIcon() {
            return mscIcon;
        }

        public void setMscIcon(String mscIcon) {
            this.mscIcon = mscIcon;
        }

        public String getMscName() {
            return mscName;
        }

        public void setMscName(String mscName) {
            this.mscName = mscName;
        }

        public String getMscSinger() {
            return mscSinger;
        }

        public void setMscSinger(String mscSinger) {
            this.mscSinger = mscSinger;
        }

        public String getMscUrl() {
            return mscUrl;
        }

        public void setMscUrl(String mscUrl) {
            this.mscUrl = mscUrl;
        }

        public static class RandomData {
            private static Random random = new Random();

            public static MusicBean random() {
                MusicBean musicBean = new MusicBean();
                musicBean.setMscName("追梦人");
                musicBean.setMscSinger("白若溪");
                musicBean.setMscIcon(ImageUrl.thumbUrl(ImageUrl.URLS[random.nextInt(ImageUrl.URLS.length - 1)]));
                return musicBean;
            }
        }
    }

    public static class ImgBean {
        /**
         * url : 图片地址
         * width : 宽度
         * height : 高度
         */

        private String url;
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        public static class RandomData {
            private static Random random = new Random();

            public static List<ImgBean> random() {
                List<ImgBean> datas = new ArrayList<>();
                int count = random.nextInt(10);
                for (int i = 0; i < count; i++) {
                    ImgBean imgBean = new ImgBean();
                    imgBean.setUrl(ImageUrl.URLS[random.nextInt(ImageUrl.URLS.length - 1)]);
                    imgBean.setWidth(800);
                    imgBean.setHeight(500);
                    datas.add(imgBean);
                }

                return datas;
            }
        }
    }
}
