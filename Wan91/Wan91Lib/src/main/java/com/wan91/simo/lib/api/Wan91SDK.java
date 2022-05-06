package com.wan91.simo.lib.api;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import java.util.Map;

public abstract class Wan91SDK{

    protected Wan91SDK() {

    }

    public static Wan91SDK getInstance() {
        return Wan91SDKImpl.getInstance();
    }

    /**
     * 初始化接口
     * @param context
     */
    public abstract void init(Application context, boolean debug);

    /**
     * 启动SDK接口 需要在onCreate中调用
     * @param mainActivity
     * @param launchCallback
     */
    public abstract void launch(Activity mainActivity, LaunchCallback launchCallback);

    public abstract void login(OnLogoutListener loginCallback);

    public abstract void logout();

    public abstract void exit();

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
}
