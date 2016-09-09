package com.weatherbuddy.core.entity;

import com.google.gson.annotations.SerializedName;
import com.weatherbuddy.core.entity.weather.Cloud;
import com.weatherbuddy.core.entity.weather.Coordinates;
import com.weatherbuddy.core.entity.weather.MainData;
import com.weatherbuddy.core.entity.weather.SystemWeatherData;
import com.weatherbuddy.core.entity.weather.Weather;
import com.weatherbuddy.core.entity.weather.Wind;

import java.util.List;

public class Forecast extends Entity {

    @SerializedName("cod")
    private int code;

    private String name;

    @SerializedName("dt")
    private String dataCalc;

    private String base;
    private int visibility;

    @SerializedName("coord")
    private Coordinates coordinates;

    private List<Weather> weather;

    @SerializedName("main")
    private MainData mainData;

    private Wind wind;
    private Cloud clouds;

    @SerializedName("sys")
    private SystemWeatherData systemWeatherData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataCalc() {
        return dataCalc;
    }

    public void setDataCalc(String dataCalc) {
        this.dataCalc = dataCalc;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public MainData getMainData() {
        return mainData;
    }

    public void setMainData(MainData mainData) {
        this.mainData = mainData;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public SystemWeatherData getSystemWeatherData() {
        return systemWeatherData;
    }

    public void setSystemWeatherData(SystemWeatherData systemWeatherData) {
        this.systemWeatherData = systemWeatherData;
    }
}