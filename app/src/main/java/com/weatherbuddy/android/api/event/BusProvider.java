package com.weatherbuddy.android.api.event;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Maintains a singleton instance for obtaining the bus.
 */
public class BusProvider extends Bus {

    private static BusProvider sBus;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public static BusProvider getInstance() {
        if (sBus == null) {
            sBus = new BusProvider();
        }
        return sBus;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    BusProvider.super.post(event);
                }
            });
        }
    }

}