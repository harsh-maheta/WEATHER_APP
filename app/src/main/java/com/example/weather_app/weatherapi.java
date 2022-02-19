package com.example.weather_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherapi {
    @GET("weather")
    Call<Example>fetch(@Query("q") String cityname,
                       @Query("appid") String apikey);
    }

