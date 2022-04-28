package com.vasana.smartattendance;

import android.app.Application;

import timber.log.Timber;

public class Appp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
