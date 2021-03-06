package com.simo.wan91.game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.simo.wan91.api.Wan91SDK;
import com.simo.wan91.lib.listener.OnExitListener;
import com.simo.wan91.lib.listener.OnInitListener;
import com.simo.wan91.lib.listener.OnLoginListener;
import com.simo.wan91.lib.listener.OnLogoutListener;
import com.simo.wan91.lib.login.UserResult;
import com.simo.wan91.lib.utils.Wan91GameConfig;

public class MainActivity extends Activity implements View.OnClickListener {
    static final String TAG = "91wan demo";
    private Button btn_login;
    private Button btn_logout;
    private Button btn_pay;
    private Button btn_on_plugin_message;
    private Button btn_create_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏(主要功能就是去除页面弹出时顶部黑色条)
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_on_plugin_message = (Button) findViewById(R.id.btn_on_plugin_message);
        btn_create_info = (Button) findViewById(R.id.btn_create_info);

        btn_login.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        btn_on_plugin_message.setOnClickListener(this);
        btn_create_info.setOnClickListener(this);

        //初始化YSDK
        sdkInit();
    }

    @Override
    public void onClick(View v) {
        if (v == btn_login) {
            login();
        } else if (v == btn_logout) {
            logout();
        } else if (v == btn_pay) {
            pay();
        } else if (v == btn_on_plugin_message) {
//            onPluginMessage();
            exit();
        } else if (v == btn_create_info) {
            createAccount();
        }
    }

    private void exit() {
        Wan91SDK.getInstance().exit(new OnExitListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "退出");
            }

            @Override
            public void onFail(String errMsg) {
                Log.d(TAG, "取消或者退出出现异常");
            }
        });
    }

    private void createAccount() {
    }

    private void pay() {
    }

    private void logout() {
        Wan91SDK.getInstance().logout();
    }

    private void login() {
        //登录接口， 因为和初始化存在并发情况， 一定要在初始化成功之后调用。 否则会导致初始化未完成时进行调用登录不成功。

        Wan91SDK.getInstance().login(loginCallback);
    }


    /**
     * 初始化相关功能
     */
    private void sdkInit() {
        // 1.初始化SDK
        Wan91SDK.getInstance().initSDK(this, new OnInitListener() {
            @Override
            public void onSuccess() {
                //成功后，吊起登录
                Log.d(TAG, "初始化成功: appid :" + Wan91GameConfig.appid);
                login();
            }

            @Override
            public void onFail(String errMsg) {
                Log.d(TAG, "初始化失败:" + errMsg);
            }
        });
        // 2.账号注销监听初始化
        Wan91SDK.getInstance().setOnLogoutListener(logoutCallback);
        // 3.游戏过程中玩家在sdk内实名认证监听初始化
//        MCApiFactory.getMCApi().initRealNameAuthenticationCallback(authenticationCallback);
//
//        MCApiFactory.getMCApi().allowPrivacy(activity, new PrivacyCallback() {
//            @Override
//            public void userPrivacy(int result) {
//                if (result == 1) {
//                    //同意隐私协议
//                    MCApiFactory.getMCApi().startlogin(activity);
//                }
//            }
//        });
    }


    /**
     * sdk用户登录回调
     */
    private OnLoginListener loginCallback = new OnLoginListener() {
        @Override
        public void onFinish(UserResult result) {
            switch (result.getResultCode()) {
                case UserResult.USER_RESULT_LOGIN_FAIL:
                    Log.d(TAG, "sdk登录回调：登录失败:" + result.getResultMsg());
                    break;
                case UserResult.USER_RESULT_LOGIN_SUCC:
                    String uid = result.getAccountNo();     //用户id（用户唯一标识）
                    String token = result.getToken();       //用户token
                    int ageStatus = result.getAgeStatus();  //用户实名认证状态：2认证通过且已成年 -1用户不存在 0未认证 1认证失败 3认证通过但未成年
                    String birthday = result.getBirthday(); //实名认证用户生日信息
                    String extra_param = result.getExtra_param();  //sdk预留的标识，发起支付方法时再传给sdk
                    Log.w(TAG, "sdk登录成功," + "userid = " + uid + "，token = " + token
                            + "，ageStatus = " + ageStatus + "， birthday = " + birthday);

                    //游戏在这时需要拿到以上userid和token到sdk服务端验证登录结果（详见《游戏登录支付通知接口文档》）
                    break;
            }
        }
    };


    /**
     * 帐号注销回调
     */
    private OnLogoutListener logoutCallback = new OnLogoutListener() {
        @Override
        public void onLogoutResult(String result) {
            if (TextUtils.isEmpty(result)) {
                return;
            }
            if ("1".equals(result)) {
                Log.i(TAG, "sdk注销回调：注销成功");
//                MCApiFactory.getMCApi().stopFloating(activity); //关闭悬浮球
//                MCApiFactory.getMCApi().startlogin(activity); //调用登录弹窗
            } else {
                Log.d(TAG, "sdk注销回调：注销失败");
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Wan91SDK.getInstance().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Wan91SDK.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wan91SDK.getInstance().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Wan91SDK.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Wan91SDK.getInstance().onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Wan91SDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Wan91SDK.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        exit();
    }
}