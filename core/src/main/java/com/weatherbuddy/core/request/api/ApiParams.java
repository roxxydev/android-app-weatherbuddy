package com.weatherbuddy.core.request.api;

import com.weatherbuddy.core.entity.weather.Coordinates;

public class ApiParams {

    private String appId;// API Key obtained from OpenWeatherMap
    private String language;// Determine the language format that be will returned by API.
    private String units;// Determine the unit value for returned temperature;
    private Coordinates coordinates;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Create instance of parameter for retrieving weather forecast from OpenWeatherMap API.
     * @param appId The API Key obtained from OpenWeatherMap API registration.
     * @param language The language format that will be returned by API. Default is 'en'.
     * @param units The temperature value format that will be returned by API.
     *              Default is 'metric' for degree celcius.
     * @param coord Contains coordinates of user current location(latitude and longitude).
     */
    public static ApiParams paramsGetForecast(String appId, String language, String units,
                                         Coordinates coord) {
        ApiParams params = new ApiParams();
        params.setAppId(appId);
        params.setLanguage(language);
        params.setUnits(units);
        params.setCoordinates(coord);
        return params;
    }

}
