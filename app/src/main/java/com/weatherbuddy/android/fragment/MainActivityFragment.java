package com.weatherbuddy.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import com.weatherbuddy.android.api.ApiCall;
import com.weatherbuddy.android.api.event.BusProvider;
import com.weatherbuddy.android.api.event.ForecastUpdateEvent;
import com.weatherbuddy.android.view.ImageLoaderUtil;
import com.weatherbuddy.android.view.UiThreadManager;
import com.weatherbuddy.core.entity.Forecast;
import com.weatherbuddy.core.entity.weather.Coordinates;
import com.weatherbuddy.core.request.json.JsonParser;
import com.weatherbuddy.core.utils.LogMe;

import com.weatherbuddy.android.R;
import com.weatherbuddy.android.view.DialogHelper;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    private View mViewRoot;
    private ImageView mIvWeather;
    private TextView mTvMsg, mTvWeatherLabel, mTvWeatherDesc, mTvPlace, mTvTemperature,
            mTvHumidity, mTvPressure, mTvWind, mTvCloudiness;
    private ProgressBar mPgBar;

    private Location mLocation;

    public MainActivityFragment() {
        // Default constructor as required by activity for fragment usage
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mViewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        return mViewRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIvWeather = (ImageView) mViewRoot.findViewById(R.id.fgmt_main_iv);
        mTvMsg = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_msg);
        mTvWeatherLabel = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_label_weather_desc);
        mTvWeatherDesc = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_weather_desc);
        mTvPlace = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_citycountry);
        mTvTemperature = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_temperature);
        mTvHumidity = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_humidity);
        mTvPressure = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_pressure);
        mTvWind = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_wind);
        mTvCloudiness = (TextView) mViewRoot.findViewById(R.id.fgmt_main_tv_cloudiness);
        mPgBar = (ProgressBar) mViewRoot.findViewById(R.id.fgmt_main_pg);

        BusProvider.getInstance().register(this);
        getUserLocation();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mLocation != null)
            getWeatherData(mLocation.getLatitude(), mLocation.getLongitude());
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }

    // Get the user's location for getting the coordinates(latitude and longitude)
    private void getUserLocation() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);   //default
        String bestProvider = lm.getBestProvider(criteria, false);
        mLocation = lm.getLastKnownLocation(bestProvider);

        if (mLocation == null) {
            showDlgEnableLocProvider();
        } else {
            LogMe.d(TAG, "latitude: " + mLocation.getLatitude() + " longitude: " +
                        mLocation.getLongitude());
                getWeatherData(mLocation.getLatitude(), mLocation.getLongitude());
        }

        // location updates: at least 100 meter and 1 second change
        MyLocationListener mylistener = new MyLocationListener();
        lm.requestLocationUpdates(bestProvider, 1000, 100, mylistener);
    }

    // Prompt dialog to user that the device location provider is currently disabled
    private void showDlgEnableLocProvider() {
        DialogHelper.showDialog(getActivity(), getResources().getString(R.string.app_name),
                getResources().getString(R.string.dlg_msg_loc_disabled),
                getResources().getString(R.string.dlg_btn_ok_label), "", true, false,
                new DialogHelper.IDialogOnClickListener() {
                    @Override
                    public void onPositiveClick() {
                        super.onPositiveClick();
                        // leads to the settings because there is no last known location
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
    }

    // Call API of OpenWeatherMap passing the coordinates obtained from location
    private void getWeatherData(double lat, double lon) {
        Coordinates coord = new Coordinates();
        coord.setLat(lat);
        coord.setLon(lon);
        ApiCall.callApiGetForecast(getActivity(), coord);
    }

    @Subscribe
    public void onWeatherDataFetched(final ForecastUpdateEvent event) {
        UiThreadManager.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mPgBar.setVisibility(View.GONE);
                if (event.isSuccess()) {
                    Forecast forecast = JsonParser.toForecast(event.getResponseBody());
                    displayWeatherForecast(forecast);
                } else {
                    mTvMsg.setVisibility(View.VISIBLE);
                    mTvMsg.setText(getResources().getString(R.string.msg_api_prob));
                }
            }
        });
    }

    // Display weather forecast details fetched to views
    private void displayWeatherForecast(Forecast forecast) {
        String labelDesc = getResources().getString(R.string.label_weather_condition);
        String labelLoc = getResources().getString(R.string.label_location);
        String labelTemp = getResources().getString(R.string.label_temperature);
        String labelHumidity = getResources().getString(R.string.label_humidity);
        String labelPressure = getResources().getString(R.string.label_pressure);
        String labelWind = getResources().getString(R.string.label_wind);
        String labelCloudiness = getResources().getString(R.string.label_cloudiness);

        mTvMsg.setVisibility(View.GONE);
        mTvWeatherLabel.setText(labelDesc);
        mTvWeatherDesc.setText(" " + forecast.getWeather().get(0).getDescription());
        mTvPlace.setText(labelLoc + " " + forecast.getName() + ", "
                + forecast.getSystemWeatherData().getCountryCode());
        mTvTemperature.setText(labelTemp + " " + forecast.getMainData().getTemp() + "\u2103");
        mTvHumidity.setText(labelHumidity + " " + forecast.getMainData().getHumidity() + "%");
        mTvPressure.setText(labelPressure + " " + forecast.getMainData().getPressure() + " hPa");
        mTvWind.setText(labelWind + " " + forecast.getWind().getSpeed() + " mps");
        mTvCloudiness.setText(labelCloudiness + " " + forecast.getClouds().getCloudAll() + "%");

        String icon = "http://openweathermap.org/img/w/" +
                forecast.getWeather().get(0).getIcon() + ".png";
        ImageLoaderUtil.setImage(getActivity(), icon, mIvWeather, R.drawable.ic_launcher);
    }

    // Location listener for any changes with location and status of location provider.
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;// update location value
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LogMe.d(TAG, provider + " status: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            LogMe.d(TAG, provider + " enabled");

        }

        @Override
        public void onProviderDisabled(String provider) {
            LogMe.w(TAG, provider + " disabled");
        }
    }
}
