package com.simo.wan91.lib.constant;

public abstract class ApiConstant {
    public static String HOST_USER = "https://login.xy51.com/";

    static {
        /**
         * todo: 这里domainType不能动, 如果需要手动修改环境 建议去 DatConfigManager 修改 domainType的默认值
         */
        // 0 正式环境 1 测试环境
        //动态切换环境
        int domainType = 0;
        if (domainType == 1) {
            HOST_USER = "http://124.193.101.194:8818/";
        } else if (domainType == 2) {
            HOST_USER = "http://test.wani1.com/";
        }else {
		}
    }

    /**
     * 登录
     */
    public static final String WAN91_USER_LOGIN = HOST_USER + "game-center/login/visitorsLogin";

}
