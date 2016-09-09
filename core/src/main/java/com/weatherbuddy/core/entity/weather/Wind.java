package com.weatherbuddy.core.entity.weather;

import com.google.gson.annotations.SerializedName;

/** Represent data weather of wind condition which includes wind speed and wind direction. */
public class Wind {

    private float speed;

    @SerializedName("deg")
    private int degrees;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
