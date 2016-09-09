package com.weatherbuddy.core.request.http.retrofit;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RetroHttpService {

    @GET("/weather")
    public void forecastGet(@Query("lat") String latitude, @Query("lon") String longitude,
                            @Query("appid") String appId, @Query("units") String units,
                            @Query("lang") String language,
                            Callback<Response> callback);

}