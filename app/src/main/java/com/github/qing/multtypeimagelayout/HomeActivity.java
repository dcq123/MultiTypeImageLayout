package com.github.qing.multtypeimagelayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.github.qing.itemdecoration.LinearDividerItemDecoration;
import com.github.qing.multtypeimagelayout.data.DataFactory;
import com.github.qing.multtypeimagelayout.data.HotData;
import com.github.qing.multtypeimagelayout.data.RecommendList;
import com.github.qing.multtypeimagelayout.data.WeatherData;
import com.github.qing.multtypeimagelayout.viewbinder.ContentDataType;
import com.github.qing.multtypeimagelayout.viewbinder.HotViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.MultiImgViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.MusicViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.RecommendViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.SingleImgViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.TextContentViewBInder;
import com.github.qing.multtypeimagelayout.viewbinder.VideoViewBinder;
import com.github.qing.multtypeimagelayout.viewbinder.WeatherViewBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.FlatTypeClassAdapter;
import me.drakeet.multitype.MultiTypeAdapter;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MultiTypeAdapter adapter;
    List<Object> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        adapter = new MultiTypeAdapter();

        // 注册所有的item类型
        adapter.register(HotData.class, new HotViewBinder());
        adapter.register(WeatherData.class, new WeatherViewBinder());
        adapter.register(RecommendList.class, new RecommendViewBinder());
        // 对于常规的内容Item，根据其是音频，图片，还是视频，进行1对多的Item映射
        adapter.register(ContentDataType.Text.class, new TextContentViewBInder());
        adapter.register(ContentDataType.SingleImage.class, new SingleImgViewBinder());
        adapter.register(ContentDataType.MultiImage.class, new MultiImgViewBinder());
        adapter.register(ContentDataType.Music.class, new MusicViewBinder());
        adapter.register(ContentDataType.Video.class, new VideoViewBinder());

        // 定义 MultiType 的类型转换
        adapter.setFlatTypeAdapter(new FlatTypeClassAdapter() {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Object item) {
                // 当item为ContentDataType时，需要获取其映射的类型
                if (item instanceof ContentDataType) {
                    return ((ContentDataType) item).getTypeClass();
                }
                return item.getClass();
            }
        });

        // 添加列表分隔符
        recyclerView.addItemDecoration(
                new LinearDividerItemDecoration.Builder()
                        .setDividerHeight(16)
                        .setDividerColor(getResources().getColor(R.color.space_bg_color))
                        .build()
        );

        recyclerView.setAdapter(adapter);

        loadData();
        initRefreshLayout();

    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent));
    }

    private void loadData() {
        items.clear();
        items.addAll(DataFactory.createData());
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
