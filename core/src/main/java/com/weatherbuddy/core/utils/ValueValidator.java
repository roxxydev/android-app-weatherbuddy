package com.weatherbuddy.core.utils;

/** Validate value of String data. */
public class ValueValidator {

	/**
     * Checks wether the string has character, not null, not equal to "", and not equal to " "(space).
     */
    public static boolean isStringValid(String strVal) {
        boolean isValid = false;
        if (strVal != null && !strVal.isEmpty() 
        	&& strVal.length() > 0 && !strVal.equals("")) {
            isValid = true;
        }
        return isValid;
    }

}

