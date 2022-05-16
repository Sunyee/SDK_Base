package com.simo.wan91.api;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.simo.wan91.lib.constant.GameSDKConstant;
import com.simo.wan91.lib.floatView.FloatViewManager;
import com.simo.wan91.lib.listener.OnExitListener;
import com.simo.wan91.lib.listener.OnInitListener;
import com.simo.wan91.lib.listener.OnLoginListener;
import com.simo.wan91.lib.listener.OnLogoutListener;
import com.simo.wan91.lib.listener.Wan91DialogListener;
import com.simo.wan91.lib.login.LoginPresenter;
import com.simo.wan91.lib.login.UserResult;
import com.simo.wan91.lib.pay.PayCallback;
import com.simo.wan91.lib.utils.DeviceUtils;
import com.simo.wan91.lib.utils.Log91;
import com.simo.wan91.lib.utils.PermissionUtils;
import com.simo.wan91.lib.utils.Wan91GameConfig;
import com.simo.windows.Wan91ExitDialog;

import java.util.Map;

class Wan91SDKImpl extends Wan91SDK {

    private static final String TAG = "YTSSDK";

    private Context mApplicationContext;
    private Application mApplication;

    public Activity getActivity() {
        return mContext;
    }

    private Activity mContext;
    private OnInitListener mOnInitListener;
    private OnLoginListener mLoginCallback;
    private OnLogoutListener mOnLogoutListener;
    private PayCallback mPayCallback;
    private Intent mIntent;
    private boolean isLogining = false;

    private Wan91SDKImpl() {
        super();
    }

    private static Wan91SDKImpl instance;

    public static Wan91SDKImpl getInstance() {
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
        Log91.setDEBUG(true); //上线时不要打开
        // 初始化后续的其他逻辑  加载参数，等等。 有些逻辑可能需要在 initSDK 中处理
        //        initUMen(context);
//        initBugly(context);
    }

    @Override
    public void initSDK(final Activity mainActivity, OnInitListener listener) {
        this.mOnInitListener = listener;
        mContext = mainActivity;
        detectionPermission(mainActivity);
        Wan91GameConfig.initGameConfig(mainActivity, listener);
        // 应用权限设置？
        mOnInitListener.onSuccess();
    }

    @Override
    public void login(OnLoginListener listener) {
        this.mLoginCallback = listener;
        //调用 登录
        if (isLogining || GameSDKConstant.isLogin) {
            Log91.d("登录过或者正在进行登录，不可以重复调用");
            return;
        }
        isLogining = true;
        if (null == mContext) {
            UserResult userResult = new UserResult();
            userResult.setResultCode(UserResult.USER_RESULT_LOGIN_FAIL);
            userResult.setResultMsg("请先进行initSDK");
            CPCallback.onLoginCallback(userResult);
            return;
        }
        LoginPresenter.instance().doLogin(mContext);
        //假装登录成功
    }

    @Override
    public void logout() {
//        SdkHelper.exit(1);
        //调用 注销
        DeviceUtils.getDeviceId(mContext);
        DeviceUtils.getOAID(mContext);
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
    public void exit(OnExitListener exitListener) {
        if (mContext == null) return;
        Wan91ExitDialog exitDialog = new Wan91ExitDialog(mContext);
        exitDialog.setCallBack(new Wan91DialogListener() {
            @Override
            public void ok() {
                // 1. 注销账号登录
                exitDialog.dismiss();
                exitListener.onSuccess();
                mContext.finish();
                System.exit(0);
            }

            @Override
            public void cancel(String errMsg) {
                exitListener.onFail("取消");
            }
        });
        exitDialog.show();

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
        Intent intent = new Intent(mApplicationContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mIntent = intent;
    }

    @Override
    public void setCPMainActivity(String mainActivityName) {
        Intent intent = new Intent();
        intent.setClassName(mApplicationContext.getPackageName(), mainActivityName);
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
        if (mContext == null) return;
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
        if (mContext == null) return;

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 631) {
            //非强制，先不处理
        }
    }

    public OnLoginListener getLoginCallback() {
        isLogining = false; //登录流程结束
        return mLoginCallback;
    }

    public OnLogoutListener getOnLogoutListener() {
        return mOnLogoutListener;
    }

    public PayCallback getPayCallback() {
        return mPayCallback;
    }

    private void detectionPermission(Activity activity) {
        //  检测并申请权限,  手机状态
//        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE};
        PermissionUtils.checkAndRequestMorePermissions(activity, permissions, 631);
    }

}
