package com.simo.wan91.lib.listener;

import com.simo.wan91.lib.login.UserResult;

/**
 * CP 方登录回调
 *  */
public interface OnLoginListener {
    public void onFinish(final UserResult gpUserResult);
}
