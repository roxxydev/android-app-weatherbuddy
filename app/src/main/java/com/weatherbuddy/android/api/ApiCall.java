package com.weatherbuddy.android.api;

import android.content.Context;

import com.weatherbuddy.core.entity.weather.Coordinates;
import com.weatherbuddy.core.request.api.ApiParams;
import com.weatherbuddy.core.request.api.ApiServiceType;
import com.weatherbuddy.core.request.http.retrofit.RetroHttpRequest;

/**
 * Performs all needed API calls for OpenWeatherMap
 */
public class ApiCall {

    private static final String sAppId = "3fe89b69387fd63418867633be04a879";
    private static final String sLanguage = "en";
    private static final String sUnits = "metric";

    public static void callApiGetForecast(Context context, Coordinates coordinate) {
        RetroHttpRequest apiRequest = new RetroHttpRequest(context, ApiServiceType.FORECAST_GET,
                new Callback(ApiServiceType.FORECAST_GET));
        apiRequest.executeHttpRequest(ApiParams.paramsGetForecast(
                sAppId, sLanguage, sUnits, coordinate));
    }

}
