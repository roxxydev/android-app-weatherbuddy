package com.weatherbuddy.core;

/**
 * Application configuration. Set the host url of the API Server and Log enabling.
 */
public class AppConfiguration {

    // Default configuration values, this can be replace by the app module using this core library

    public static final String HOST = "http://api.openweathermap.org/data/2.5";
    public static boolean ENABLE_LOG = true;

}
