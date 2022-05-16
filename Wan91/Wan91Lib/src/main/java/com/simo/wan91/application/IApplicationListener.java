package com.simo.wan91.application;

import android.content.Context;
import android.content.res.Configuration;

public interface IApplicationListener {
    void onProxyCreate();

    void onProxyAttachBaseContext(Context var1);

    void onProxyConfigurationChanged(Configuration var1);
}
