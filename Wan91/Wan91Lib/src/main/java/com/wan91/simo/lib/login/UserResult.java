package com.wan91.simo.lib.login;

/**
 * 平台账号登录信息
 */
public class UserResult {
	// ------------------------------user
//	public static  int mErrCode;// 操作结果
	public final static  int USER_RESULT_LOGIN_FAIL = -1;// 登录失败
	public final static int USER_RESULT_LOGIN_SUCC = 1;// 登录成功
//	public static String sessionid;
//	
	private int mErrCode;// 操作结果
	private String accountNo;
	private String timeStamp;
	private String token;
	private int ageStatus;
	private String birthday;

	public int getAgeStatus() {
		return ageStatus;
	}

	public void setAgeStatus(int ageStatus) {
		this.ageStatus = ageStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getExtra_param() {
		return extra_param;
	}

	public void setExtra_param(String extra_param) {
		this.extra_param = extra_param;
	}

	private String extra_param;
	public int getmErrCode() {
		return mErrCode;
	}
	public void setmErrCode(int mErrCode) {
		this.mErrCode = mErrCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
 }
