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


import androidx.annotation.NonNull;

/**
 * An internal implementation of {@link LifecycleObserver} that relies on reflection.
 *
 * @deprecated internal infra to support deprecated {@link OnLifecycleEvent}
 */
@Deprecated
class ReflectiveGenericLifecycleObserver implements com.custom.framework.lifecycle.LifecycleEventObserver {
    private final Object mWrapped;
    private final com.custom.framework.lifecycle.ClassesInfoCache.CallbackInfo mInfo;

    @SuppressWarnings("deprecation")
    ReflectiveGenericLifecycleObserver(Object wrapped) {
        mWrapped = wrapped;
        mInfo = com.custom.framework.lifecycle.ClassesInfoCache.sInstance.getInfo(mWrapped.getClass());
    }

    @Override
    public void onStateChanged(@NonNull com.custom.framework.lifecycle.LifecycleOwner source, @NonNull Lifecycle.Event event) {
        mInfo.invokeCallbacks(source, event, mWrapped);
    }
}
