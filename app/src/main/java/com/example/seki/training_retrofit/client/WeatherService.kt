package com.example.seki.training_retrofit.client;

import com.example.seki.training_retrofit.WeatherData

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("json/v1")
    fun singleWeather(@Query("city") city: String): Call<WeatherData>
}