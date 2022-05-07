package com.wan91.simo.lib.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.wan91.simo.lib.api.Wan91SDK;

public class ProxyApplication {

    private String DEFAULT_PKG_NAME;
    private static final String PROXY_NAME = "SM_APPLICATION_PROXY_NAME";

    private IApplicationListener listener;

    Application app;

    public ProxyApplication(Application app) {
        this.app = app;
    }

    public void onCreate() {
        if (listener != null) {
            listener.onProxyCreate();
        }
        Wan91SDK.getInstance().init(app);
    }

    public void attachBaseContext(Context base) {
        DEFAULT_PKG_NAME = base.getPackageName();
        this.listener = initProxyApplication();
        if (this.listener != null) {
            this.listener.onProxyAttachBaseContext(base);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.listener != null) {
            this.listener.onProxyConfigurationChanged(newConfig);
        }
    }

    private IApplicationListener initProxyApplication() {
        String proxyAppName = this.getMetaData(app, PROXY_NAME);
        if (proxyAppName == null || TextUtils.isEmpty(proxyAppName)) {
            return null;
        }
        if (proxyAppName.startsWith(".")) {
            proxyAppName = DEFAULT_PKG_NAME + proxyAppName;
        }
        try {
            Class clazz = Class.forName(proxyAppName);
            return (IApplicationListener) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param smApplication
     * @param proxyName
     * @return
     */
    public static String getMetaData(Application smApplication, String proxyName) {
        Bundle bundle = getAppMetaDataBundle(smApplication.getPackageManager(), smApplication.getPackageName());
        if (bundle != null && bundle.containsKey(proxyName)) {
            return bundle.getString(proxyName);
        }
        return "";
    }

    private static Bundle getAppMetaDataBundle(PackageManager packageManager, String packageName) {
        Bundle bundle = null;
        ApplicationInfo ai = null;
        try {
            ai = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            bundle = ai.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("getMetaDataBundle", e.getMessage(), e);
        }
        return bundle;
    }


}
