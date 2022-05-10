package com.wan91.simo.api;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.wan91.simo.application.ActivityLifecycle;
import com.wan91.simo.lib.listener.OnExitListener;
import com.wan91.simo.lib.listener.OnInitListener;
import com.wan91.simo.lib.listener.OnLoginListener;
import com.wan91.simo.lib.listener.OnLogoutListener;
import com.wan91.simo.lib.pay.PayCallback;

import java.util.Map;

public abstract class Wan91SDK implements ActivityLifecycle{

    protected Wan91SDK() {

    }

    public static Wan91SDK getInstance() {
        return Wan91SDKImpl.getInstance();
    }

    /**
     * 初始化接口
     * @param context
     */
    public abstract void init(Application context);

    /**
     * 启动SDK接口 需要在onCreate中调用
     * @param mainActivity
     */
    public abstract void initSDK(Activity mainActivity, OnInitListener listener);

    public abstract void login(OnLoginListener listener);

    public abstract void logout();

    public abstract void exit(OnExitListener exitListener);

    public abstract void setOnLogoutListener(OnLogoutListener onLogoutListener);

    public abstract void pay(Map<String, String> payInfos, PayCallback payCallback);


    /**
     * 每次登录成功的时候调用
     * @param gameAccount
     */
    public abstract void setGameAccount(Map<String, String> gameAccount);

    /**
     * 第一次创建成功角色的时候调用
     * @param gameAccount
     */
    public abstract void roleCreate(Map<String, String> gameAccount);

    public abstract void setCPMainActivity(Class<?> cls);

    public abstract void setCPMainActivity(String mainActivityName);

    public abstract  Intent getCPMainActivity();

    public abstract void setDebug(boolean debug);
}
