package com.example.seki.training_retrofit;

public final class WeatherData {
    public Location location;
    public Description description;

    public static class Location {
        public String city;
    }

    public static class Description {
        public String text;
    }
}
