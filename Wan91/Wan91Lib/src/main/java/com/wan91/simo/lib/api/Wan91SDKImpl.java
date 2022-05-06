package com.wan91.simo.lib.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.wan91.simo.lib.application.ActivityLifecycle;
import com.wan91.simo.lib.utils.Wan91Log;

import java.util.Map;

public class Wan91SDKImpl extends Wan91SDK implements ActivityLifecycle {

    private static final String TAG = "YTSSDK";

    private Context mApplicationContext;
    private Application mApplication;
    private Activity mMainActivity;
    private OnLogoutListener mLoginCallback;
    private OnLogoutListener mOnLogoutListener;
    private PayCallback mPayCallback;
    private  Intent mIntent;

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
    public void init(Application application, boolean debug) {
        mApplicationContext = application.getApplicationContext();
        mApplication = application;
        Wan91Log.setDEBUG(debug);
//        initUMen(context);
//        initBugly(context);
        // 初始化后续的其他逻辑
    }

    @Override
    public void launch(final Activity mainActivity, final LaunchCallback launchCallback) {
        mMainActivity = mainActivity;
    }

    @Override
    public void login(OnLogoutListener loginCallback) {
        mLoginCallback = loginCallback;
        //调用 登录
        
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
        return (mMainActivity != null && mMainActivity == activity);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResumed() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult() {
//        SdkHelper.onActivityResult(requestCode, resultCode, data);
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
