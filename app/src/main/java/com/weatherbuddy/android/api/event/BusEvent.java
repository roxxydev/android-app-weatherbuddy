package com.weatherbuddy.android.api.event;

import com.weatherbuddy.core.request.http.HttpStatusCode;

public interface BusEvent {

    /**
     * Get if http request of API call is successful.
     */
    public boolean isSuccess();

    public void setIsSuccess(boolean isSuccess);

    /**
     * Get the http status code returned from http request of API call.
     */
    public int getHttpStatusCode();

    public void setHttpStatusCode(int code);
}
