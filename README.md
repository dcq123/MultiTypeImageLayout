### MultiTypeImageLayout

> 该项目是一个`Demo`项目，主要用来实现列表中多个`Item`展现，九宫格图片展示，以及类似微信的多图预览等功能。之所以没有做成`Lib`是由于有些实现需要根据项目的不同需求去修改，如：九宫格的展现方式，图片点击后的其他处理等，抽取出来如果有其他需求，还得在抽取的代码中修改。所以决定放在`Demo`中实现所有的功能。`Demo`中许多细节也都处理过，所以可以作为一种实现参考。

#### 多Item列表

列表界面仿`即刻APP`的消息列表页，里面包含不同的`Item`布局，使用[**MultiType**](https://github.com/drakeet/MultiType)实现，该`Lib`提供了详细的使用文档，对于多`Item`布局非常方便。

<div>

<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-02-30-016_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>

<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-03-45-920_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>

<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-03-35-130_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>

</div>



#### 九宫格图片展示

九宫格图片展示其实使用的是`GridLayoutManager`中提供的`setSpanSizeLookup()`方法，用来设置每个`Item`占据的`Span`。具体可参考`ImgGridLayoutManager`类。

<div>

<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-03-00-140_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>

<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-02-44-773_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>


<img width="280" height="480" src="http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/Screenshot_2017-04-14-12-02-17-267_com.github.qin.png?imageView2/0/format/webp/q/75|imageslim"/>

</div>

#### 图片预览

图片预览效果类似与微信的图片预览，进入预览时有共享动画，可**拖动退出图片预览**。由于`Gif`过大所以只能放置链接。

[图片预览转场动画](http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/4%E6%9C%88-14-2017%2016-15-08.gif)

[拖动图片退出预览动画](http://onxvhxvw6.bkt.clouddn.com/image/multiTypeImage/4%E6%9C%88-14-2017%2016-18-26.gif)

图片预览的处理实现，使用[**WeixinPhotoViewer**](https://github.com/nirvanawoody/WeixinPhotoViewer),它是在`PhotoView`的基础上实现了预览时类似与微信的转场效果。该`Demo`在此基础上添加了拖动退出图片预览的功能。
