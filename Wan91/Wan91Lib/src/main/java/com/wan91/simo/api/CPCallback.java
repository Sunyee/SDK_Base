package com.wan91.simo.api;


import com.wan91.simo.lib.listener.OnLoginListener;
import com.wan91.simo.lib.login.UserResult;

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
