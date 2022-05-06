package com.wan91.simo.lib.api;

import android.os.Bundle;

public interface LaunchCallback {
    public void onComplete(boolean status);

    public void onLogoutSuccess(Bundle bundle);

    public void onLogoutFailure(int code, String message);
}
