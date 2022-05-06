package com.wan91.simo.lib.api;

import android.os.Bundle;

public interface OnLogoutListener {
    void onSuccess(Bundle bundle);

    void onFailure(int code, String message);
}
