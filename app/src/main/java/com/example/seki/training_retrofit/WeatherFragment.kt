package com.example.seki.training_retrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.seki.training_retrofit.client.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class WeatherFragment: Fragment() {

    private var mAdapter: ListAdapter? = null
    private var mWeatherService: WeatherService? = null

    companion object {
        fun getInstance() = WeatherFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
                               .baseUrl("http://weather.livedoor.com/forecast/webservice/")
                               .addConverterFactory(GsonConverterFactory.create())
                               .build()

        mWeatherService = retrofit.create(WeatherService::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        val layoutManager = LinearLayoutManager(activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.weather_list)
        recyclerView.layoutManager = layoutManager

        mAdapter = ListAdapter()
        recyclerView.adapter = mAdapter
        recyclerView.setHasFixedSize(false)

        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {_ -> loadWeather("130010")}

        val button2 = view.findViewById<Button>(R.id.button2)
        button2.setOnClickListener {_ -> loadWeather("110010")}

        return  view
    }

    private fun loadWeather(city: String) {
        val dataCall = mWeatherService!!.singleWeather(city)
        dataCall.enqueue(object :Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                mAdapter!!.addData(response.body()!!)
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("singleWeather failure", t.toString())
            }
        })
    }


}
