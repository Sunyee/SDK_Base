package com.wan91.simo.lib.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by joe on 2018/4/9.
 */

public class SDKUtils {

    private Context mContext;

    public SDKUtils(Context context){
        this.mContext = context;
    }

    /**
     * @param context
     * @return true 为竖屏，反之横屏
     */
    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

}
