package com.simo.wan91.lib.pay;

import android.os.Bundle;


public interface PayCallback {

    void onSuccess(Bundle bundle);

    /**
     *
     * @param code -1：失败 -2：取消
     * @param message
     */
    void onFailure(int code, String message);
}
