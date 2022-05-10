package com.wan91.simo.lib.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.custom.event.bean.UserRegister;
import com.custom.framework.livedata.Observer;
import com.custom.framework.viewmodel.ViewModelProvider;
import com.wan91.simo.api.CPCallback;
import com.wan91.simo.lib.constant.GameSDKConstant;
import com.wan91.simo.lib.floatView.FloatViewManager;
import com.wan91.simo.lib.listener.OnMultiClickListener;
import com.wan91.simo.lib.login.model.Wan91LoginModel;
import com.wan91.simo.lib.utils.LayoutUtils;
import com.wan91.simo.lib.utils.Log91;
import com.wan91.windows.BaseDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GameLoginDialog extends BaseDialog {

    private Wan91LoginModel loginModel;

    public GameLoginDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return LayoutUtils.getLayout(getContext(),"wan91_dialog_game_login");
    }

    @Override
    public void initView(View rooView) {
        loginModel = new ViewModelProvider(this).get(Wan91LoginModel.class);
        loginModel.getLoginListData().observe(this, new Observer<UserResult>() {
            @Override
            public void onChanged(UserResult userResult) {
                //登录成功，隐藏弹窗
                GameSDKConstant.isLogin = true;
                // 获取实名认证信息
                FloatViewManager.getInstance((Activity) mContext).show();
                dismiss();
                userResult = new UserResult();
                userResult.setResultCode(UserResult.USER_RESULT_LOGIN_SUCC);
                userResult.setResultMsg("登录成功");
                userResult.setAccountNo("qwedsdf");
                userResult.setBirthday("20200505");
                userResult.setToken("1111111111111");
                CPCallback.onLoginCallback(userResult);
            }
        });

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
            }
        });
        Button btnLogin = (Button) rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                        "btn_platform_login"));
        btnLogin.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                loginModel.doLogin("","");
            }
        });
        rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                        "tv_wan91_login_no_account")).setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                EventBus.getDefault().post(new UserRegister());
            }
        });
    }

    @Override
    public void show() {
        super.show();
        EventBus.getDefault().register(this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserRegister event) {
        // Do something
        Log91.e("注册成功的回调");
    }
}
