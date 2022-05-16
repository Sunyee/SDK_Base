package com.simo.windows;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.simo.wan91.lib.listener.OnMultiClickListener;
import com.simo.wan91.lib.listener.Wan91DialogListener;
import com.simo.wan91.lib.utils.LayoutUtils;

public class Wan91ExitDialog extends BaseDialog {

    private Wan91DialogListener callBack;

    public Wan91ExitDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return LayoutUtils.getLayout(getContext(),"wan91_dialog_alert_exit_main_light");
    }

    @Override
    public void initView(View rooView) {

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
            }
        });
        TextView btnLogin = (TextView) rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                "dialog_message"));
        rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                "cancel")).setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                dismiss();
                if (callBack !=null)
                    callBack.cancel("");
            }
        });
        rooView.findViewById(LayoutUtils.getIdByName(mContext, "id",
                "ok")).setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                //这里做一些退出操作
                if (callBack !=null)
                    callBack.ok();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setCallBack(Wan91DialogListener callBack) {
        this.callBack = callBack;
    }
}
