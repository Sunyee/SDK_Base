package com.wan91.simo.lib.utils;

import android.content.Context;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;

public class MiitHelper implements IIdentifierListener{

    private AppIdsUpdater _listener;

    public MiitHelper(AppIdsUpdater callback){
        _listener = callback;
    }

    public void getDeviceIds(Context cxt){
        int resCode = CallFromReflect(cxt);
        if (resCode == ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT){
            Log91.e("不支持的设备");
        }else if (resCode == ErrorCode.INIT_ERROR_LOAD_CONFIGFILE){
            Log91.e("加载配置文件出错");
        }else if (resCode == ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT){
            Log91.e("不支持的设备厂商");
        }else if (resCode == ErrorCode.INIT_ERROR_RESULT_DELAY){
            Log91.e("获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
        }else if (resCode == ErrorCode.INIT_HELPER_CALL_ERROR){
            Log91.e("反射调用出错");
        }
        Log91.d("result code : " + String.valueOf(resCode));
    }


    /*
     * 通过反射调用，解决android 9以后的类加载升级，导至找不到so中的方法
     *
     * */
    private int CallFromReflect(Context cxt){
        return MdidSdkHelper.InitSdk(cxt,true,this);
    }

    @Override
    public void OnSupport(boolean isSupport,IdSupplier _supplier){
        if (_supplier == null){
            return;
        }
        String oaid = _supplier.getOAID();
        if (_listener != null){
            _listener.OnIdsAvalid(oaid);
        }
    }

    public interface AppIdsUpdater{
        void OnIdsAvalid(String ids);
    }
}
