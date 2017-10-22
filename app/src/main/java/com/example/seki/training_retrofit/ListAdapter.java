package com.example.seki.training_retrofit;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seki.training_retrofit.databinding.ItemWeatherBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.WeatherViewHolder>{

    final private List<WeatherData> mWeatherDatas;

    public ListAdapter() {
        mWeatherDatas = new ArrayList<>();
    }


    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        final ItemWeatherBinding mBinding;
        WeatherViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        final WeatherData data = mWeatherDatas.get(position);
        holder.mBinding.setVariable(com.example.seki.training_retrofit.BR.data, data);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mWeatherDatas.size();
    }

    public void addData(WeatherData data) {
        mWeatherDatas.add(0, data);
        notifyDataSetChanged();
    }
}
