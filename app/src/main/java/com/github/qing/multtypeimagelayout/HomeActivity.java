package com.github.qing.multtypeimagelayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.github.qing.itemdecoration.LinearDividerItemDecoration;
import com.github.qing.multtypeimagelayout.data.ContentData;
import com.github.qing.multtypeimagelayout.data.DataFactory;
import com.github.qing.multtypeimagelayout.data.HotData;
import com.github.qing.multtypeimagelayout.data.JikeData;
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
        adapter.register(HotData.class, new HotViewBinder());
        adapter.register(WeatherData.class, new WeatherViewBinder());
        adapter.register(RecommendList.class, new RecommendViewBinder());
        adapter.register(ContentDataType.Text.class, new TextContentViewBInder());
        adapter.register(ContentDataType.SingleImage.class, new SingleImgViewBinder());
        adapter.register(ContentDataType.MultImage.class, new MultiImgViewBinder());
        adapter.register(ContentDataType.Music.class, new MusicViewBinder());
        adapter.register(ContentDataType.Video.class, new VideoViewBinder());

        adapter.setFlatTypeAdapter(new FlatTypeClassAdapter() {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Object item) {
                if (item instanceof ContentDataType) {
                    return ((ContentDataType) item).getTypeClass();
                }
                return item.getClass();
            }
        });


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
        JikeData newData = DataFactory.createNewData();
        items.clear();
        items.add(newData.getHotData());
        List<ContentData> contentDatas = newData.getContentDatas();
        for (int i = 0; i < contentDatas.size(); i++) {
            ContentData item = contentDatas.get(i);
            int type = 1;
            if (item.getType() == 1) {
                if (item.getImg().size() == 0) {
                    type = 1;
                } else if (item.getImg().size() == 1) {
                    type = 2;
                } else {
                    type = 3;
                }
            } else if (item.getType() == 3) {
                type = 4;
            } else if (item.getType() == 2) {
                type = 5;
            }
            items.add(new ContentDataType(item, type));
            if (i == 8) {
                items.add(newData.getWeatherData());
            }
            if (i == 12) {
                items.add(new RecommendList(newData.getRecommendDatas()));
            }
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
