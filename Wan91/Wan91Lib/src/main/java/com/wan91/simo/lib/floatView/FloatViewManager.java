package com.wan91.simo.lib.floatView;

import android.app.Activity;

import com.wan91.simo.lib.constant.GameSDKConstant;
import com.wan91.simo.lib.utils.Wan91Log;

public class FloatViewManager {
    private static FloatViewManager instance;
    private boolean floatingIsShow = false;
    private Activity mContext;

    /**
     * 获取FloatViewManager单例
     *
     * @return
     */
    public static FloatViewManager getInstance(Activity mContext) {

        if (instance == null) {
            synchronized (FloatViewManager.class) {
                if (instance == null) {
                    instance = new FloatViewManager(mContext);
                }
            }
        }
        return instance;
    }

    private FloatViewManager(Activity mContext) {
        this.mContext = mContext;
    }

    public void show() {
        //判断是否登录成功
        if (!GameSDKConstant.isLogin){
            Wan91Log.i("没登陆不显示float");
            return;
        }
        if (!floatingIsShow) {
            floatingIsShow = true;
            Wan91Log.i("fun#startFloating");
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FloatView.getInstance(mContext).show();
                }
            });
        }
    }

    public void hide() {
        Wan91Log.i("floatingIsShow: "+floatingIsShow);
        if (floatingIsShow) {
            floatingIsShow = false;
            Wan91Log.i("fun#stopFloating");
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FloatView.getInstance(mContext).close();
                }
            });
        }
    }

}
