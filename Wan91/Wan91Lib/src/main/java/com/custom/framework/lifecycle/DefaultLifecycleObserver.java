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
 * Callback interface for listening to {@link com.custom.framework.lifecycle.LifecycleOwner} state changes.
 * If a class implements both this interface and {@link com.custom.framework.lifecycle.LifecycleEventObserver}, then
 * methods of {@code DefaultLifecycleObserver} will be called first, and then followed by the call
 * of {@link com.custom.framework.lifecycle.LifecycleEventObserver#onStateChanged(com.custom.framework.lifecycle.LifecycleOwner, Lifecycle.Event)}
 * <p>
 * If a class implements this interface and in the same time uses {@link com.custom.framework.lifecycle.OnLifecycleEvent}, then
 * annotations will be ignored.
 */
@SuppressWarnings("unused")
public interface DefaultLifecycleObserver extends com.custom.framework.lifecycle.FullLifecycleObserver {

    /**
     * Notifies that {@code ON_CREATE} event occurred.
     * <p>
     * This method will be called after the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onCreate}
     * method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onCreate(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_START} event occurred.
     * <p>
     * This method will be called after the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onStart} method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onStart(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_RESUME} event occurred.
     * <p>
     * This method will be called after the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onResume}
     * method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onResume(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_PAUSE} event occurred.
     * <p>
     * This method will be called before the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onPause} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onPause(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_STOP} event occurred.
     * <p>
     * This method will be called before the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onStop} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onStop(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_DESTROY} event occurred.
     * <p>
     * This method will be called before the {@link com.custom.framework.lifecycle.LifecycleOwner}'s {@code onDestroy} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onDestroy(@NonNull com.custom.framework.lifecycle.LifecycleOwner owner) {
    }
}

