package com.simo.windows;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.custom.framework.lifecycle.Lifecycle;
import com.custom.framework.lifecycle.LifecycleCallbackOwner;
import com.custom.framework.lifecycle.LifecycleOwner;
import com.custom.framework.lifecycle.LifecycleRegistry;
import com.custom.framework.lifecycle.ReportFragment;
import com.custom.framework.viewmodel.ViewModelStore;
import com.custom.framework.viewmodel.ViewModelStoreOwner;


public abstract class BaseDialog extends Dialog implements LifecycleOwner, ViewModelStoreOwner, LifecycleCallbackOwner {

    public Context mContext;
    public long stayStart;
    public long stayEnd;

    public float getLoadingViewAlpha() {
        return loadingViewAlpha;
    }

    public float loadingViewAlpha = 1.0f;
    public int[] baseRealScreenSize;
    private static int showCount;

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private ViewModelStore mViewModelStore;
    private ReportFragment.LifecycleCallbacks lifecycleCallbacks;

    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;

        mViewModelStore = new ViewModelStore();
        ReportFragment.injectIfNeededIn(this);

//        baseRealScreenSize = DisplayHelper.getRealScreenSize(context);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCancelable(false);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        this.getWindow().setGravity(Gravity.LEFT);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        this.getWindow().setAttributes(lp);
        //hideNavagationBar();
        View contentView = View.inflate(context, getLayout(), null);
        setContentView(contentView);
        initView(contentView);
    }

    private void hideNavagationBar(){
        int uiOptions =View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN;

        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    /**
     * 设计图尺寸1080
     */
    public int getSizeByHeight(int px){
        float percent = px / 1080f;
        int radii = (int) (percent * baseRealScreenSize[1]);
        return radii;
    }

    /**
     * @return 布局的id
     */
    public abstract int getLayout();

    /**
     * 初始化界面
     */
    public abstract void initView(View rooView);

    public int getBackGroundColor(){
        return 0;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    protected boolean isOkCode(int keyCode){
        if (keyCode == 96 || keyCode == 23 || keyCode == 66){
            return true;
        }else {
            return false;
        }
    }


    public void showSoftInputFromWindow(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    @Override
    public void show() {
        super.show();
        showCount ++;
        if (lifecycleCallbacks != null){
            lifecycleCallbacks.onActivityCreated(this,null);
            lifecycleCallbacks.onActivityStarted(this);
        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (lifecycleCallbacks != null){
            if (hasFocus){
                lifecycleCallbacks.onActivityResumed(this);
            } else {
                lifecycleCallbacks.onActivityPaused(this);
            }
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        showCount --;

        if (lifecycleCallbacks != null){
            lifecycleCallbacks.onActivityStopped(this);
            lifecycleCallbacks.onActivityDestroyed(this);
        }

        mViewModelStore.clear();
    }

    public boolean hasOneOrMoreDialog(){
        return showCount > 0;
    }

    @Override
    public void setLifecycleCallbacks(ReportFragment.LifecycleCallbacks lifecycleCallbacks) {
        this.lifecycleCallbacks = lifecycleCallbacks;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mViewModelStore;
    }
}
