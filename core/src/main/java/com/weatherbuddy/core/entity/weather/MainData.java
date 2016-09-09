package com.weatherbuddy.core.entity.weather;

import com.google.gson.annotations.SerializedName;

/** Represents main weather data like pressure, temperature, humidity, etc. */
public class MainData {

    private int temp;
    private int pressure;
    private int humidity;

    @SerializedName("temp_min")
    private int tempMin;

    @SerializedName("temp_max")
    private int tempMax;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}
