package com.wan91.simo.net;

import android.content.Context;

import com.custom.net.okhttp.OkHttpUtils;
import com.wan91.simo.lib.bean.FunctionBean;
import com.wan91.simo.lib.constant.ApiConstant;

import java.util.HashMap;
import java.util.Map;

final public class NetApi {

    private static final String TAG = "Api";

    public static void get(Map<String, String> params, String url, JsonCallback jsonCallback) {
        OkHttpUtils.get().url(url)
                .headers(getHeaders())
                .params(params)
                .build()
                .execute(jsonCallback);
    }

    public static void post(Map<String, String> params, String url, JsonCallback jsonCallback) {
        OkHttpUtils.post().url(url)
                .headers(getHeaders())
                .params(params)
                .build()
                .execute(jsonCallback);
    }

    /**
     * 返回header 后续再定
     * @return
     */
    public static Map<String, String> getHeaders() {
        String appKey = "";
        String appSecret = "";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //header
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("appkey", appKey);
        headerMap.put("timestamp", timestamp);
//        headerMap.putAll(getHeader());
//        //generate sign
//        try {
//            String sign = XiaoyEncryptUtil.GetSign(params, appKey, appSecret, timestamp, nonce);
//            //put sign to header
//            headerMap.put("sign", sign);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return headerMap;
    }


    public static void loginBy91(String name, String pwd, JsonCallback<FunctionBean> callback) {
        Map<String, String> params = new HashMap<>();
//        params.put("appid", String.valueOf(Launcher.mAppId));
//        params.put("reportedType", loginState + "");//上报类型
//        params.put("deviceType", !DensityUtil.isPhone(null) ? "1" : "2");  //设备类型
//        params.put("limitLoginTimeSwitch", CertificationHelper.getInstance().getLimitLoginTimeSwitch()+"");
//        params.put("sessionId", Utils.getSessionId());
//        params.put("reportSwitch", CertificationHelper.getInstance().getReportSwitch()+"");
//        params.put("rnVer", ApiContext.getPluginAppId(Launcher.getInstance().getContext(), Launcher.mAppId));
        post(params, ApiConstant.WAN91_USER_LOGIN, callback);
    }
}
