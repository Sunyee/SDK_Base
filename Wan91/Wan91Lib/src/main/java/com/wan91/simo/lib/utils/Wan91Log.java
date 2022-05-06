package com.wan91.simo.lib.utils;

import android.util.Log;

public class Wan91Log {

    private static boolean DEBUG = false;

    private final static String TAG = "liusy";

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        e(TAG,msg);
    }

    public static void e(String TAG,String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void setDEBUG(boolean DEBUG) {
        Wan91Log.DEBUG = DEBUG;
    }
}
