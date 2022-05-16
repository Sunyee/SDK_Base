package com.simo.wan91.lib.login.model;



import com.custom.framework.livedata.MutableLiveData;
import com.simo.wan91.core.BaseViewModel;
import com.simo.wan91.lib.bean.FunctionBean;
import com.simo.wan91.lib.login.UserResult;
import com.simo.wan91.net.JsonCallback;
import com.simo.wan91.net.NetApi;

public class Wan91LoginModel extends BaseViewModel {
    MutableLiveData<UserResult> loginListData;

    public MutableLiveData<UserResult> getLoginListData(){
        if (loginListData == null){
            loginListData = new MutableLiveData<>();
        }
        return loginListData;
    }

    public void doLogin(String s, String s1){
        if (loginListData !=null){
            loginListData.postValue(null);
            return;
        }

        //登录流程。 如果是3方登录，还需要走这个接口。
        NetApi.loginBy91("lsy54631", "111111", new JsonCallback<FunctionBean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onResponse(FunctionBean response) {
            }
        });
    }


}
