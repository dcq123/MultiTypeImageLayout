package com.github.qing.multtypeimagelayout.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.qing.multtypeimagelayout.R;
import com.github.qing.multtypeimagelayout.data.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dcq on 2017/4/10.
 * <p>
 * 天气item
 */

public class WeatherViewBinder extends ItemViewBinder<WeatherData, WeatherViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_weather_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull WeatherData item) {

        holder.city.setText(item.getCity());
        holder.temper.setText(item.getTemper() + "  " + item.getType());
        holder.weatherIcon.setImageResource(item.getIcon());
        holder.weatherQuality.setText("空气质量：" + item.getQuality());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weatherQuality)
        TextView weatherQuality;
        @BindView(R.id.temper)
        TextView temper;
        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.weatherIcon)
        ImageView weatherIcon;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
