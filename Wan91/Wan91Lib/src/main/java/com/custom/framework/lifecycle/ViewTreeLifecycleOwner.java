/*
 * Copyright 2019 The Android Open Source Project
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

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Accessors for finding a view tree-local {@link com.custom.framework.lifecycle.LifecycleOwner} that reports the lifecycle for
 * the given view.
 */
public class ViewTreeLifecycleOwner {
    // 此id值需要重写 暂时定为100是为了编译不报错
    private static int id = 100;
    private ViewTreeLifecycleOwner() {
        // No instances
    }

    /**
     * Set the {@link com.custom.framework.lifecycle.LifecycleOwner} responsible for managing the given {@link View}.
     * Calls to {@link #get(View)} from this view or descendants will return {@code lifecycleOwner}.
     *
     * <p>This should only be called by constructs such as activities or fragments that manage
     * a view tree and reflect their own lifecycle through a {@link com.custom.framework.lifecycle.LifecycleOwner}. Callers
     * should only set a {@link com.custom.framework.lifecycle.LifecycleOwner} that will be <em>stable.</em> The associated
     * lifecycle should report that it is destroyed if the view tree is removed and is not
     * guaranteed to later become reattached to a window.</p>
     *
     * @param view Root view managed by lifecycleOwner
     * @param lifecycleOwner LifecycleOwner representing the manager of the given view
     */
    public static void set(@NonNull View view, @Nullable com.custom.framework.lifecycle.LifecycleOwner lifecycleOwner) {
        view.setTag(id, lifecycleOwner);
    }

    /**
     * Retrieve the {@link com.custom.framework.lifecycle.LifecycleOwner} responsible for managing the given {@link View}.
     * This may be used to scope work or heavyweight resources associated with the view
     * that may span cycles of the view becoming detached and reattached from a window.
     *
     * @param view View to fetch a {@link com.custom.framework.lifecycle.LifecycleOwner} for
     * @return The {@link com.custom.framework.lifecycle.LifecycleOwner} responsible for managing this view and/or some subset
     *         of its ancestors
     */
    @Nullable
    public static com.custom.framework.lifecycle.LifecycleOwner get(@NonNull View view) {
        com.custom.framework.lifecycle.LifecycleOwner found = (com.custom.framework.lifecycle.LifecycleOwner) view.getTag(id);
        if (found != null) return found;
        ViewParent parent = view.getParent();
        while (found == null && parent instanceof View) {
            final View parentView = (View) parent;
            found = (com.custom.framework.lifecycle.LifecycleOwner) parentView.getTag(id);
            parent = parentView.getParent();
        }
        return found;
    }
}
