package com.simo.wan91.application;

import android.content.Intent;


public interface ActivityLifecycle {

    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults);
}
