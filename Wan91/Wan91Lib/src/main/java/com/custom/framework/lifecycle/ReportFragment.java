/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.custom.framework.lifecycle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Internal class that dispatches initialization events.
 */
public class ReportFragment {

    public static void injectIfNeededIn(com.custom.framework.lifecycle.LifecycleCallbackOwner lifecycleCallbackOwner) {
        LifecycleCallbacks.registerIn(lifecycleCallbackOwner);
    }

    private static void dispatch(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle instanceof LifecycleRegistry) {
            ((LifecycleRegistry) lifecycle).handleLifecycleEvent(event);
        }
    }

    // this class isn't inlined only because we need to add a proguard rule for it (b/142778206)
    // In addition to that registerIn method allows to avoid class verification failure,
    // because registerActivityLifecycleCallbacks is available only since api 29.
    public static class LifecycleCallbacks {

        static void registerIn(com.custom.framework.lifecycle.LifecycleCallbackOwner lifecycleCallbackOwner) {
            lifecycleCallbackOwner.setLifecycleCallbacks(new LifecycleCallbacks());
        }

        public void onActivityCreated(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner,
                                      @Nullable Bundle bundle) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_CREATE);
        }

        public void onActivityStarted(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_START);
        }

        public void onActivityResumed(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_RESUME);
        }

        public void onActivityPaused(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_PAUSE);
        }

        public void onActivityStopped(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_STOP);
        }

        public void onActivitySaveInstanceState(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner,
                                                @NonNull Bundle bundle) {
        }

        public void onActivityDestroyed(@NonNull com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
            dispatch(lifecycleOwner, Lifecycle.Event.ON_DESTROY);
        }
    }
}
