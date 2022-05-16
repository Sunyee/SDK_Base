package com.simo.wan91.lib.floatView;

import android.app.Activity;

import com.simo.wan91.lib.constant.GameSDKConstant;
import com.simo.wan91.lib.utils.Log91;

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
            return;
        }
        if (!floatingIsShow) {
            floatingIsShow = true;
            Log91.i("startFloating");
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FloatView.getInstance(mContext).show();
                }
            });
        }
    }

    public void hide() {
        Log91.i("floatingIsShow: "+floatingIsShow);
        if (floatingIsShow) {
            floatingIsShow = false;
            Log91.i("fun#stopFloating");
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FloatView.getInstance(mContext).close();
                }
            });
        }
    }

}
