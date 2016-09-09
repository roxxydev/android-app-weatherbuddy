package com.weatherbuddy.core.request.api;

/** Type of API Service of the http request */
public enum ApiServiceType {

    FORECAST_GET("forecast_get");

    private final String description;

    private ApiServiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ApiServiceType fromDescription(String description) {
        for (ApiServiceType gender : ApiServiceType.values()) {
            if (gender.getDescription().equalsIgnoreCase(description))
                return gender;
        }
        return null;
    }
    
}
