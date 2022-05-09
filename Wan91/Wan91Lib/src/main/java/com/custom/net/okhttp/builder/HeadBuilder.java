package com.custom.net.okhttp.builder;


import com.custom.net.okhttp.OkHttpUtils;
import com.custom.net.okhttp.request.OtherRequest;
import com.custom.net.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
