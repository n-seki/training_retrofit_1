package com.example.seki.training_rx_1;

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

import com.example.seki.training_rx_1.client.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {

    private ListAdapter mAdapter;
    private WeatherService mWeatherService;

    public static WeatherFragment getInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.livedoor.com/forecast/webservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mWeatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.weather_list);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(false);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        final Button button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(v -> loadWeather("130010"));

        final Button button2 = (Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(v -> loadWeather("110010"));

        return  view;
    }

    public void loadWeather(String city) {
        final Call<WeatherData> dataCall = mWeatherService.singleWeather(city);
        dataCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                mAdapter.addData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                Log.d("singleWeather failure", t.toString());
            }
        });

    }


}
