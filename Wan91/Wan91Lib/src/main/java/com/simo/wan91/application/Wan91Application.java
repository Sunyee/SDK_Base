package com.simo.wan91.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;

import com.bun.miitmdid.core.JLibrary;
import com.simo.wan91.api.Wan91SDK;
import com.simo.wan91.lib.utils.Log91;
import com.simo.wan91.lib.utils.MiitHelper;
import com.simo.wan91.lib.utils.SharedPreferencesUtils;

public class Wan91Application extends Application {
    private String DEFAULT_PKG_NAME;
    private static final String PROXY_NAME = "SM_APPLICATION_PROXY_NAME";
    private IApplicationListener listener;

    public Wan91Application() {
    }

    public void onCreate() {
        super.onCreate();
        if (this.listener != null) {
            this.listener.onProxyCreate();
        }
        initOAID();
        // sdk app 相关事件
        Wan91SDK.getInstance().init(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.DEFAULT_PKG_NAME = base.getPackageName();
        this.listener = this.initProxyApplication();
        if (this.listener != null) {
            this.listener.onProxyAttachBaseContext(base);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.listener != null) {
            this.listener.onProxyConfigurationChanged(newConfig);
        }
    }

    private IApplicationListener initProxyApplication() {
        String proxyAppName = getMetaData(this, PROXY_NAME);
        if (proxyAppName != null && !TextUtils.isEmpty(proxyAppName)) {
            if (proxyAppName.startsWith(".")) {
                proxyAppName = this.DEFAULT_PKG_NAME + proxyAppName;
            }

            try {
                Class clazz = Class.forName(proxyAppName);
                return (IApplicationListener) clazz.newInstance();
            } catch (ClassNotFoundException var3) {
                var3.printStackTrace();
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }

    public static String getMetaData(Application xyApplication, String proxyName) {
        Bundle bundle = getAppMetaDataBundle(xyApplication.getPackageManager(), xyApplication.getPackageName());
        return bundle != null && bundle.containsKey(proxyName) ? bundle.getString(proxyName) : "";
    }

    private static Bundle getAppMetaDataBundle(PackageManager packageManager, String packageName) {
        Bundle bundle = null;
        ApplicationInfo ai = null;

        try {
            ai = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            bundle = ai.metaData;
        } catch (NameNotFoundException var5) {
            Log91.e(var5.getMessage());
        }

        return bundle;
    }

    private void initOAID() {
        String oaid = SharedPreferencesUtils.getInstance().getOAID(this);
        if (!TextUtils.isEmpty(oaid)) {
            return;
        }
        try {
            JLibrary.InitEntry(this);
            // 获取OAID等设备标识符
            MiitHelper miitHelper = new MiitHelper(new MiitHelper.AppIdsUpdater() {
                @Override
                public void OnIdsAvalid(String ids) {
                    Log91.d("android oaid:" + oaid);
                    // oaid
                    if (!TextUtils.isEmpty(ids)) {
                        SharedPreferencesUtils.getInstance().putOAID(Wan91Application.this, ids);
                    } else {
                    }
                }
            });
            miitHelper.getDeviceIds(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
