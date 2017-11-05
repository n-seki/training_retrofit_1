package com.example.seki.training_retrofit;

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seki.training_retrofit.databinding.ItemWeatherBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.WeatherViewHolder>(){

    private val mWeatherDatas: MutableList<WeatherData> = mutableListOf()


    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mBinding: ItemWeatherBinding = DataBindingUtil.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ListAdapter.WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.WeatherViewHolder, position: Int) {
        val data = mWeatherDatas[position]
        holder.mBinding.setVariable(com.example.seki.training_retrofit.BR.data, data)
        holder.mBinding.executePendingBindings()
    }

    override fun getItemCount() = mWeatherDatas.size

     fun addData(data: WeatherData) {
        mWeatherDatas.add(0, data)
        notifyDataSetChanged()
    }
}
