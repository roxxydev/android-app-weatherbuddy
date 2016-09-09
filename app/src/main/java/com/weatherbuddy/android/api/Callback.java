package com.weatherbuddy.android.api;

import com.weatherbuddy.android.api.event.BusProvider;
import com.weatherbuddy.android.api.event.ForecastUpdateEvent;
import com.weatherbuddy.core.request.api.ApiCallback;
import com.weatherbuddy.core.request.api.ApiResponse;
import com.weatherbuddy.core.request.api.ApiServiceType;

/**
 * Handle all API http requests response.
 */
public class Callback implements ApiCallback {

    private static final String TAG = Callback.class.getSimpleName();

    private ApiServiceType mApiServiceType;

    public Callback(ApiServiceType apiServiceType) {
        this.mApiServiceType = apiServiceType;
    }

    @Override
    public void onSuccess(ApiResponse apiResponse) {
        if (mApiServiceType == ApiServiceType.FORECAST_GET) {
            ForecastUpdateEvent busEvent = new ForecastUpdateEvent();
            busEvent.setHttpStatusCode(apiResponse.getCode());
            busEvent.setIsSuccess(true);
            busEvent.setResponseBody(apiResponse.getResponseBody());
            BusProvider.getInstance().post(busEvent);
        }
    }

    @Override
    public void onFailed(ApiResponse apiResponse) {
        if (mApiServiceType == ApiServiceType.FORECAST_GET) {
            ForecastUpdateEvent busEvent = new ForecastUpdateEvent();
            busEvent.setIsSuccess(false);
            busEvent.setHttpStatusCode(apiResponse.getCode());
            BusProvider.getInstance().post(busEvent);
        }
    }
}
