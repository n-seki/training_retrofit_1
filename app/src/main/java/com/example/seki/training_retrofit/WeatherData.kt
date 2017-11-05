package com.example.seki.training_retrofit;

data class WeatherData(val location: Location, val description: Description)
data class Location(val city: String)
data class Description(val text: String)
