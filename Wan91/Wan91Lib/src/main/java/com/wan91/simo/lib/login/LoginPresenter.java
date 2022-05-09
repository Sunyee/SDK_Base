package com.wan91.simo.lib.login;

import android.app.Activity;

import com.wan91.simo.lib.utils.SharedPreferencesUtils;

public class LoginPresenter {
    private static LoginPresenter loginModel;

    public static LoginPresenter instance() {
        if (null == loginModel) {
            loginModel = new LoginPresenter();
        }
        return loginModel;
    }

    public LoginPresenter() {
    }

    public void doLogin(Activity activity) {
        /*
         *  这里加上一些登录判断
         * */
        boolean isThreeLogin = !SharedPreferencesUtils.getInstance().getIsThreeLogin(activity);
        if (isThreeLogin){
            //三方登录预留
        }
        boolean autoLogin = SharedPreferencesUtils.getInstance().getAutoLogin(activity);
        if(autoLogin && !SharedPreferencesUtils.getInstance().getIsLogout(activity)){
            //如果开启了自动登录、上次没有注销登录、则调用自动登录
//            autologin(context);

            //自动登录没做
            GameLoginDialog loginDialog = new GameLoginDialog(activity);
            loginDialog.show();
        }else{
            //调用登录接口
            GameLoginDialog loginDialog = new GameLoginDialog(activity);
            loginDialog.show();
        }
    }


    public boolean isLogin() {
//        String userId = PersonalCenterModel.getInstance().getUserId();
//        String account = PersonalCenterModel.getInstance().getAccount();
//        return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(userId);
        return false;
    }
}
