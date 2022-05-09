package com.wan91.simo.lib.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.wan91.simo.lib.application.ActivityLifecycle;
import com.wan91.simo.lib.constant.GameSDKConstant;
import com.wan91.simo.lib.floatView.FloatViewManager;
import com.wan91.simo.lib.login.OnLoginListener;
import com.wan91.simo.lib.login.OnLogoutListener;
import com.wan91.simo.lib.pay.PayCallback;
import com.wan91.simo.lib.utils.SharedPreferencesUtils;
import com.wan91.simo.lib.utils.Wan91Log;

import java.util.Map;

class Wan91SDKImpl extends Wan91SDK{

    private static final String TAG = "YTSSDK";

    private Context mApplicationContext;
    private Application mApplication;

    public Activity getActivity() {
        return mContext;
    }

    private Activity mContext;
    private OnLoginListener mLoginCallback;
    private OnLogoutListener mOnLogoutListener;
    private PayCallback mPayCallback;
    private  Intent mIntent;
    private boolean isLogining = false;

    private Wan91SDKImpl() {
        super();
    }

    private static Wan91SDKImpl instance;

    public static Wan91SDK getInstance() {
        if (instance == null) {
            synchronized (Wan91SDKImpl.class) {
                if (instance == null) {
                    instance = new Wan91SDKImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void init(Application application) {
        mApplicationContext = application.getApplicationContext();
        mApplication = application;
//        initUMen(context);
//        initBugly(context);
        // 初始化后续的其他逻辑
    }

    @Override
    public void initSDK(final Activity mainActivity, final boolean debug) {
        mContext = mainActivity;
        Wan91Log.setDEBUG(debug);
    }

    @Override
    public void login() {
        //调用 登录
        if (isLogining) {
            return;
        }
        isLogining = true;
        if (null == mContext) {
            Wan91Log.e(TAG, "fun#login context is null");
            return;
        }
        /*
        *  这里加上一些登录判断
        * */
        boolean isThreeLogin = !SharedPreferencesUtils.getInstance().getIsThreeLogin(mContext);
        if (isThreeLogin){
        }
        boolean autoLogin = SharedPreferencesUtils.getInstance().getAutoLogin(mContext);
        if(autoLogin && !SharedPreferencesUtils.getInstance().getIsLogout(mContext)){
            //如果开启了自动登录、上次没有注销登录、上次不是QQ微信第三方快捷登录、没有开启极验，则调用自动登录
//            autologin(context);
        }else{
            //调用登录接口
//            login(context);
        }
        //假装登录成功
        GameSDKConstant.isLogin = true;

    }

    @Override
    public void logout() {
//        SdkHelper.exit(1);
        //调用 注销
    }

    /**
     * @param payInfos
     * @param payCallback
     */
    @Override
    public void pay(Map<String, String> payInfos, PayCallback payCallback) {
        mPayCallback = payCallback;
        String orderId = payInfos.get("orderId");
        String goodsId = payInfos.get("goodsId");
        String money = payInfos.get("money");
        String notifyUrl = payInfos.get("notifyUrl");
        String payDesc = payInfos.get("payDesc");
        String attach = payInfos.get("attach");
//        SdkHelper.xiaoyPay(orderId, goodsId, money, notifyUrl, payDesc,attach);
        //调用支付
        /*
        *  通过判断，调用支付
        *                  XYSDK.getInstance().pay(payParams);
                    ThirdPayManager.getInstance().thirdPayResult(postOrderId.get("longOrderNo"));
                } else if (info.getPayType() == 0) {
                    LOG.i(TAG, "backup完成后的支付");
                    payByXiaoY(info);
                }
        * */

    }

    @Override
    public void exit() {
//        SdkHelper.exit(0);
//        退出
    }

    @Override
    public void setOnLogoutListener(OnLogoutListener onLogoutListener) {
        this.mOnLogoutListener = onLogoutListener;
    }

    @Override
    public void setOnLoginListener(OnLoginListener loginListener) {
        this.mLoginCallback = loginListener;
    }

    @Override
    public void setGameAccount(Map<String, String> gameAccount) {
//        SdkHelper.setGameAccount(gameAccount);
    }

    @Override
    public void roleCreate(Map<String, String> gameAccount) {
//        SdkHelper.setRoleCreateInfo(mApplicationContext, gameAccount);
    }


    @Override
    public void setCPMainActivity(Class<?> cls) {
        Intent intent = new Intent(mApplicationContext,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mIntent = intent;
    }

    @Override
    public void setCPMainActivity(String mainActivityName) {
        Intent intent = new Intent();
        intent.setClassName(mApplicationContext.getPackageName(),mainActivityName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mIntent = intent;
    }

    @Override
    public Intent getCPMainActivity() {
        return mIntent;
    }

    private boolean isGameMainActivity(Activity activity) {
        return (mContext != null && mContext == activity);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
        Wan91Log.d("onResume");
        // 回到游戏，开启上报， 防沉迷等功能
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //悬浮球
                FloatViewManager.getInstance(mContext).show();
            }
        });
    }

    @Override
    public void onPause() {
        Wan91Log.d("onPause");
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FloatViewManager.getInstance(mContext).hide();  //关闭悬浮球
            }
        });
    }

    @Override
    public void onStop() {
        /**
         * 游戏进入后台或退出游戏时，请求下线, 不计时时长
         */
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

    }
//
//    private class XYSDKCallback extends SDKCallback {
//
//        public XYSDKCallback(Context context) {
//            super(context);
//        }
//
//        @Override
//        public void loginCompleted(int loginType, String userName, String accessToken, int vMacNum, int uuidInt) {
//            if (mLoginCallback != null) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("loginType", loginType);
//                bundle.putString("userName", userName);
//                bundle.putString("accessToken", accessToken);
//                bundle.putInt("vMacNum", vMacNum);
//                bundle.putInt("uuidInt", uuidInt);
//                mLoginCallback.onSuccess(bundle);
//            }
//        }
//
//        @Override
//        public void loginFailed(int code, String message) {
//            if (mLoginCallback != null) {
//                mLoginCallback.onFailure(code, message);
//            }
//        }
//
//        @Override
//        public void logoutSuccess() {
//            SMLog.i(TAG,"logoutSuccess impl:" + mLaunchCallback);
//            if (mLaunchCallback != null) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("code", 0);
//                mLaunchCallback.onLogoutSuccess(bundle);
//            }
//        }
//
//        @Override
//        public void logoutFailed() {
//            if (mLaunchCallback != null) {
//                mLaunchCallback.onLogoutFailure(-1, "注销失败");
//            }
//        }
//
//        @Override
//        public void onExit() {
//            SMLog.i(TAG,"onExit");
//            if (mOnExitListener != null){
//                Bundle bundle = new Bundle();
//                mOnExitListener.onSuccess(bundle);
//            }
//        }
//
//        /**
//         * @param payType 0小y支付 1 三方支付 2 新版本三方支付
//         * @param status  当payType== 0 时 status  0：成功 -1：失败 -2：取消
//         *                当payType== 1 时 status不做处理
//         *                当payType== 2 时 status  0：成功 -1：失败 -2：取消
//         * @param message 成功的订单号 或者失败的信息
//         */
//        @Override
//        public void payCallBack(int payType, int status, String message) {
//            if (mPayCallback != null) {
//                if (payType == 0 || payType == 2) {
//                    if (status == 0) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString("orderCode", message);
//                        mPayCallback.onSuccess(bundle);
//                    } else {
//                        mPayCallback.onFailure(status, message);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void onPluginMessage(String message) {
//            super.onPluginMessage(message);
//            SMLog.e("SDK", "sjy---" + message);
//            if (mOnPluginMessageCallback != null) {
//                mOnPluginMessageCallback.onPluginMessage(message);
//            }
//        }
//
//    }


}
