package com.weatherbuddy.core.request.api;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the JSON of API Status Code response.
 * This class represents the JSON String response of the API in form of
 * { "cod":200, "message":"Forecast data returned" }
 */
public class ApiStatusModel {

    @SerializedName("cod")
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
