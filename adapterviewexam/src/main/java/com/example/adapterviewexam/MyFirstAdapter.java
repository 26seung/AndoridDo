package com.example.adapterviewexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFirstAdapter extends BaseAdapter {

    private List<Weather> mData;
    private Map<String, Integer> mWeatherImageMap;

    public MyFirstAdapter(List<Weather> data) {
        this.mData = data;

        // 이미지 데이터
        mWeatherImageMap = new HashMap<>();
        mWeatherImageMap.put("맑음",R.drawable.sunny);
        mWeatherImageMap.put("흐림",R.drawable.cloud);
        mWeatherImageMap.put("비",R.drawable.rain);
        mWeatherImageMap.put("눈",R.drawable.cloud);
        mWeatherImageMap.put("번개",R.drawable.thunder);
    }

    // 아이템의 갯수 표시
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 리스트 뷰를 호출 할때 각각 호출되어 레이아웃이 표시 됨
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);

            ImageView weatherImage = convertView.findViewById(R.id.weather_image);
            TextView cityText = convertView.findViewById(R.id.city_text);
            TextView tempText = convertView.findViewById(R.id.temp_text);

            holder.weatherImage = weatherImage;
            holder.cityText = cityText;
            holder.tempText = tempText;

            convertView.setTag(holder);

        } else {
            holder= (ViewHolder) convertView.getTag();
        }

//        ImageView weatherImage = convertView.findViewById(R.id.weather_image);
//        TextView cityText = convertView.findViewById(R.id.city_text);
//        TextView tempText = convertView.findViewById(R.id.temp_text);

        Weather weather = mData.get(position);
        holder.cityText.setText(weather.getCity());
        holder.tempText.setText(weather.getTemp());
        holder.weatherImage.setImageResource(mWeatherImageMap.get(weather.getWeather()));

        return convertView;
    }

    static class ViewHolder {
        ImageView weatherImage;
        TextView cityText;
        TextView tempText;
    }
    
    // 최적화.. 네트워크를 이용해 정보를 가져오고 하면 느리기 때문에 ViewHolder 를 통하여 최적화 작업
}
