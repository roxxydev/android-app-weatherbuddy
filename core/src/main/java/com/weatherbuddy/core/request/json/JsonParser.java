package com.weatherbuddy.core.request.json;

import com.weatherbuddy.core.entity.Forecast;
import com.weatherbuddy.core.request.api.ApiStatusModel;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser of JSON String and Entity classes.
 */
public class JsonParser {

    private static Gson getDefaultGson() {
        return new GsonBuilder().create();
    }

    private static Gson getDefaultGson(ExclusionStrategy exclStrategy) {
        return new GsonBuilder().setExclusionStrategies(exclStrategy).create();
    }

    /**
     * Parse JSON String value to Forecast object.
     * @param jsonString The JSON String value to parse.
     * @return Object of Forecast of parsed JSON String value.
     */
    public static Forecast toForecast(String jsonString) {
        return getDefaultGson().fromJson(jsonString, Forecast.class);
    }

    /**
     * Parse Forecast object instance to JSON String value.
     * @param forecastObj The Forecast object instance to parse.
     * @return The JSON String value of parsed Forecast object.
     */
    public static String fromForecastObj(Forecast forecastObj) {
        return getDefaultGson().toJson(forecastObj, Forecast.class);
    }

    /**
     * Parse JSON String value to List of Forecast.
     * @param jsonString The JSON String value to parse.
     * @return Object List of Forecast of parsed JSON String value.
     */
    public static ArrayList<Forecast> toForecastList(String jsonString) {
        Type listType = new TypeToken<List<Forecast>>() {}.getType();
        ArrayList<Forecast> arrForecastList = getDefaultGson().fromJson(jsonString, listType);
        return arrForecastList;
    }

    /**
     * Parse JSON String value to ApiStatusModel object.
     * @param jsonString The JSON String value to parse.
     * @return Object of ApiStatusModel of parsed JSON String value.
     */
    public static ApiStatusModel toApiStatusModel(String jsonString) {
        return getDefaultGson().fromJson(jsonString, ApiStatusModel.class);
    }

    /**
     * Exclude fields that are not to be included in POST JSON body of http request.
     */
    private static class EntityExlusionStrategy implements ExclusionStrategy {

        private Class<?> mClass;
        private ArrayList<String> mArrFieldName;

        /**
         * The Class to check if has exclusion strategies FieldAttributes.
         * @param className The Class to check if has exclusion strategies FieldAttributes
         * @param arrFieldsToExclude The fields in {@link JsonName}
         *                           that will be used.
         */
        public EntityExlusionStrategy(Class className, ArrayList<String> arrFieldsToExclude) {
            this.mClass = className;
            this.mArrFieldName = arrFieldsToExclude != null ? arrFieldsToExclude :
                    new ArrayList<String>();
        }

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            String fieldAttribName = f.getName();
            if (fieldAttribName.startsWith("m")) {
                String firstLetter = fieldAttribName.substring(0, 0).toUpperCase();
                fieldAttribName = firstLetter + fieldAttribName.substring(2,
                        fieldAttribName.length() -1);
            }
            if (f.getDeclaringClass() == mClass && mArrFieldName != null
                    && mArrFieldName.contains(fieldAttribName)) {
                return true;
            }
            return false;
        }
    }

}
