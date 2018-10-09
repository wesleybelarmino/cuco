package com.app.cuco;

import android.app.Application;
import android.content.Context;

public class CucoApp extends Application {

    private static CucoApp instance;

    public static CucoApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
