package com.simo.wan91.application;

import android.app.Application;
import android.content.Context;

public class Wan91ProxyApplication extends ProxyApplication {

    private static Application application;

    public Wan91ProxyApplication(Application app) {
        super(app);
        application = app;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    public void onCreate() {
        super.onCreate();
    }
}
