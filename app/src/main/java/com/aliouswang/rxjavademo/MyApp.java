package com.aliouswang.rxjavademo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by aliouswang on 16/2/26.
 */
public class MyApp extends Application{

    private static MyApp instance;
    private RefWatcher refWatcher;

    public static MyApp get() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return MyApp.get().refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = (MyApp) getApplicationContext();
        LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());
    }
}
