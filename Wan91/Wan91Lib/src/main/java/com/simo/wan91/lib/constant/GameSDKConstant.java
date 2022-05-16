package com.simo.wan91.lib.constant;

/**
 * Created by joe on 2018/4/9.
 */

public class GameSDKConstant {
    public static final String sdkVersion = "1.0.0";

    public static final int POLL_MAX_LOADIMAGE_NUM = 8;//图片加载线程池最大并发量

    public static boolean isLogin = false;
    public static Boolean isPhoneBind = false; //是否已经进行手机绑定
    public static Boolean isCertificate = false; //是否已经进行实名认证

    public static String GAME_CONFIG = "91wan_config.xml"; //
    public static final String GAME_APPID = "appid";
    public static final String GAME_CCHID = "cchid";
    public static final String GAME_MDID = "mdid";

    public static String keFuMessage = ""; //客服信息
    public static String keFuQQ = ""; //客服qq
    public static String keFuPhone = ""; //客服电话

    public static String exitImgUrl = ""; //退弹图片地址
    public static String exitToUrl = ""; //点击退弹图片跳转地址

    public static String isShowBindPhoneView = "1"; //0关闭，1不强制，2强制
    public static String isShowFloatView = "1";//0关闭，1不强制，2强制
    public static String isShowCertView = "1"; //0关闭，1不强制，2强制
    public static String isShowAutoLoginView = "1";//0关闭，1开启
    public static String isShowFloatLogoutBt = "0";//0关闭，1开启
    public static String isShowPureUI = "0";//0关闭，1开启

    public static String loginNoticeFlag = "";
    public static String loginNoticeUrl = "";

    public static String userNickName = "";
    public static String userQQ = "";

    //初始化成功时间
    public static String initSuccessTime = "0";

    public static String isHoliady = "false";
    public static int fatigue_switch = 1;
    public static int fatigue_time = 2;

    public static String realName = "";
    public static String idCard = "";
}
