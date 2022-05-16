package com.simo.wan91.game;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.simo.wan91.application.Wan91ProxyApplication;


/**
*  不方便继承的91玩application的，使用这种方式
* */
public class GameApp2 extends Application {
    Wan91ProxyApplication proxyApplication ;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.proxyApplication = new Wan91ProxyApplication(this);
        proxyApplication.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        proxyApplication.onCreate();
        //其他事情
        // sdk app 相关事件
        Log.d("liusy","application");
//        YTSSDK.getInstance().setCPMainActivity("com.stvgame.MainGameActivity");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        proxyApplication.onConfigurationChanged(newConfig);
    }

}
