package com.wan91.simo.lib.application;

import android.app.Activity;
import android.os.Bundle;

public interface ActivityLifecycle {

    void onStart();
    void onResumed();
    void onPause();
    void onStop();
    void onDestroy();
    void onActivityResult();
}
