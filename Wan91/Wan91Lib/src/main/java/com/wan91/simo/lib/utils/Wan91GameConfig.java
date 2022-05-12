package com.wan91.simo.lib.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.wan91.simo.lib.constant.GameSDKConstant;
import com.wan91.simo.lib.listener.OnInitListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class Wan91GameConfig {

    public static String appid = "10001";
    public static String cchid = "1";
    public static String mdid = "";

    public static void initGameConfig(Context context, OnInitListener initCallback) {
        getGameInfo(context, initCallback);
    }

    private static void getGameInfo(Context context, OnInitListener initCallback) {
        // 从资产中读取XMl文件
        // 获取资产管理器
        AssetManager assetManager = context.getAssets();
        if (assetManager == null) {
            if (initCallback != null) {
                initCallback.onFail("获取assets失败");
            }
            return;
        }
        try {
            // 获取文件输入流
            InputStream is = assetManager.open(GameSDKConstant.GAME_CONFIG);
            if (is == null) {
                if (initCallback != null) {
                    initCallback.onFail(GameSDKConstant.GAME_CONFIG + "文件不存在");
                }
                return;
            }
            // 创建构建XMLPull分析器工厂
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            // 创建XMLPull分析器
            XmlPullParser xpp = xppf.newPullParser();
            // 设置分析器的输入流
            xpp.setInput(is, "utf-8");
            // 得到下一个事件
            int eventType;
            // 得到当前的事件
            eventType = xpp.getEventType();
            // 循环事件
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (xpp.getName().equals(GameSDKConstant.GAME_APPID)) {
                            appid = xpp.nextText().trim();
                        }
                        if (xpp.getName().equals(GameSDKConstant.GAME_CCHID)) {
                            cchid = xpp.nextText().trim();
                        }
                        if (xpp.getName().equals(GameSDKConstant.GAME_MDID)) {
                            mdid = xpp.nextText().trim();
                        }
                        break;
                    default:
                        break;
                }
                // 获取下一个事件
                eventType = xpp.next();
            }
        } catch (Exception e) {
            if (initCallback != null) {
                initCallback.onFail(GameSDKConstant.GAME_CONFIG + "文件配置错误");
            }
            return;
        }
    }

}
