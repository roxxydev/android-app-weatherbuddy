package com.weatherbuddy.android.api.event;

public class ForecastUpdateEvent implements BusEvent {

    private boolean mIsResultOk = false;
    private int code;
    private String responseBody;

    @Override
    public int getHttpStatusCode() {
        return code;
    }

    @Override
    public boolean isSuccess() {
        return mIsResultOk;
    }

    @Override
    public void setIsSuccess(boolean isSuccess) {
        this.mIsResultOk = isSuccess;
    }

    @Override
    public void setHttpStatusCode(int code) {
        this.code = code;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
