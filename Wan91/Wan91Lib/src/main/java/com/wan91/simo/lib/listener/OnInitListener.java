package com.wan91.simo.lib.listener;

import com.wan91.simo.lib.login.UserResult;

public interface OnInitListener {
    void onSuccess();
    void onFail(String errMsg);
}
