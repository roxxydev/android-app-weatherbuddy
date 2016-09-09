package com.weatherbuddy.core.entity.weather;

import com.google.gson.annotations.SerializedName;

/** Weather data for cloudiness. */
public class Cloud {

    @SerializedName("all")
    private int cloudAll;

    public int getCloudAll() {
        return cloudAll;
    }

    public void setCloudAll(int cloudAll) {
        this.cloudAll = cloudAll;
    }
}
