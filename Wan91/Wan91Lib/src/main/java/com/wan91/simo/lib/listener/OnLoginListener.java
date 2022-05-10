package com.wan91.simo.lib.listener;

import com.wan91.simo.lib.login.UserResult;

/**
 * CP 方登录回调
 *  */
public interface OnLoginListener {
    public void onFinish(final UserResult gpUserResult);
}
