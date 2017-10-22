package com.example.seki.training_rx_1.client;

import com.example.seki.training_rx_1.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("json/v1")
    Call<WeatherData> singleWeather(@Query("city") String city);
}