package com.wan91.simo.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.wan91.simo.lib.utils.Wan91Log;
import com.custom.net.okhttp.callback.Callback;
import com.custom.net.exception.ResultException;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class JsonCallback<T> extends Callback<T> {
	public static final String TAG = "JsonCallback";

	private boolean standardParse = true;

	/**
	 * 返回值非标准格式，强制用非标准解析
	 * @param standardParse
	 */
	public void setStandardParse(boolean standardParse) {
		this.standardParse = standardParse;
	}

	@Override
	public void onBefore(Request request, int id) {
		super.onBefore(request, id);
	}

	@Override
	public T parseNetworkResponse(Response response, int i) throws Exception {
		String content = response.body().string();
		T data = null;
		JSONObject jsonObject = new JSONObject(content);
		Gson gson = new Gson();
		Type objectType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		//协定好的格式。
		if (jsonObject.has("status") && jsonObject.has("code") && standardParse) {
			int status = jsonObject.getInt("status");
			int code = jsonObject.getInt("code");
			String message = null;
			if (jsonObject.has("msg")){
				message = jsonObject.getString("msg");
			}else if (jsonObject.has("message")){
				message = jsonObject.getString("message");
			}

			String dataString = null;
			if (jsonObject.has("data")) {
				dataString = jsonObject.getString("data");
			}

			if (status == 1) {
				try {
					if (!TextUtils.isEmpty(dataString)) {
					    if (objectType == String.class){
							data = (T) dataString;
						}else{
							data = gson.fromJson(dataString, objectType);
						}
					}
				} catch (Exception e) {
					Wan91Log.i(TAG, "formJsonException:" + e.getMessage());
				}
			} else if (status == 0) {
				throw new ResultException(code,message);
			} else {
				throw new Exception("status:" + status + " message:" + message);
			}
		} else {
			try {
				if (!TextUtils.isEmpty(content)) {
					if (objectType == String.class){
						data = (T) content;
					}else{
						data = gson.fromJson(content, objectType);
					}
				}
			} catch (Exception e) {
				Wan91Log.i(TAG, "formJsonException:" + e.getMessage());
			}
		}
		return data;
	}

	@Override
	public void onError(Call call, Exception e, int i) {
		e.printStackTrace();
		if (e instanceof ResultException){
			onError(e);
		}else {
			Exception exception = new Exception("服务正在维护中，请稍后再试");
			onError(exception);
		}
	}

	@Override
	public void onResponse(T t, int i) {
		onResponse(t);
	}

	public abstract void onError(Exception e);

	public abstract void onResponse(T response);

}
