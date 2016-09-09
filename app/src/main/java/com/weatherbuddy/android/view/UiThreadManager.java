package com.weatherbuddy.android.view;

import android.os.Handler;
import android.os.Looper;

/** Run thread safe Bus events with update to UI views. */
public class UiThreadManager {

    public static void runOnUIThread(Runnable r) {
        new Handler(Looper.getMainLooper()).post(r);
    }

}
