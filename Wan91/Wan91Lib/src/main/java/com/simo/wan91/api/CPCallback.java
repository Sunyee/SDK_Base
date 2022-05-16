package com.simo.wan91.api;


import com.simo.wan91.lib.listener.OnLoginListener;
import com.simo.wan91.lib.login.UserResult;

public class CPCallback {

    public static void onLoginCallback(UserResult countTime){
        OnLoginListener loginCallback = Wan91SDKImpl.getInstance().getLoginCallback();
        if (loginCallback !=null){
            loginCallback.onFinish(countTime);
        }
    }
    public static void onLogoutListener(){
        Wan91SDKImpl.getInstance().getOnLogoutListener();
    }
    public static void onPayCallback(){
        Wan91SDKImpl.getInstance().getPayCallback();
    }

}
