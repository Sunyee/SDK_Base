package com.wan91.simo.lib.login.model;



import com.custom.framework.livedata.MutableLiveData;
import com.wan91.simo.core.BaseViewModel;
import com.wan91.simo.lib.login.UserResult;

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
        }
    }


}
