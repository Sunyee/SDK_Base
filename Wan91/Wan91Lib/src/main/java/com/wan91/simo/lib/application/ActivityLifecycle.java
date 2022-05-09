package com.wan91.simo.lib.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.wan91.simo.lib.api.Wan91SDK;

public interface ActivityLifecycle {

    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
