package com.wan91.simo.lib.login;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.custom.framework.livedata.Observer;
import com.custom.framework.viewmodel.ViewModelProvider;
import com.wan91.simo.lib.listener.OnMultiClickListener;
import com.wan91.simo.lib.login.model.Wan91LoginModel;
import com.wan91.simo.lib.utils.LayoutUtils;
import com.wan91.simo.lib.utils.Wan91Log;
import com.wan91.windows.BaseDialog;

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
            public void onChanged(UserResult countTime) {
                //登录成功， 隐藏弹窗
                Wan91Log.d("登录成功了");
            }
        });

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
            }
        });
        Button btnLogin = (Button) (Button) rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                        "btn_platform_login"));
        btnLogin.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                loginModel.doLogin("","");
            }
        });
    }

}
