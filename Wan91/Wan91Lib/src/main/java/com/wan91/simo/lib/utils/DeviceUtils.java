package com.wan91.simo.lib.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import java.util.UUID;

public class DeviceUtils {

    protected static final String PREFS_DEVICE_ID = "91wan_device_id";
    protected static String uuid;
    /**
     * 先取androidId,deviceid。取不到就取Android id，否则生成一个uuid做为deviceid
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context){
        String deviceId = "";
        if (Build.VERSION.SDK_INT >= 29){
            deviceId = getAndroidId(context);
        }else if (Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
                TelephonyManager mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = mTelephony.getDeviceId();
            }
        }else{
            TelephonyManager mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = mTelephony.getDeviceId();

        }

        if (TextUtils.isEmpty(deviceId)){
            try{
                deviceId = getAndroidId(context);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (TextUtils.isEmpty(deviceId) || "9774d56d682e549c".equals(deviceId)){
            deviceId = writeDeviceID(context);
        }
        Log91.d("android deviceId:" + deviceId);
        return deviceId;

    }

    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
    }

    private static String writeDeviceID(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String did = sharedPreferences.getString(PREFS_DEVICE_ID,null);
        if (did != null){
            uuid = did;
        }else{
            UUID uuid = UUID.randomUUID();
            String deviceId = uuid.toString().replace("-","");
            sharedPreferences.edit().putString(PREFS_DEVICE_ID,deviceId).apply();
        }

        return uuid;
    }

    public static String getOAID(Context context){
        String oaid = SharedPreferencesUtils.getInstance().getOAID(context);
        Log91.d("android oaid:" + oaid);
        return oaid;
    }
}
