package com.custom.net.exception;

/**
 * <pre>
 * 业务名:
 * 功能说明:
 * 编写日期: 2020-12-10
 * 作者:	 LiuShanyi
 * 备注:
 *
 * 历史记录
 * 1、修改日期：
 *    修改人：LiuShanyi
 *    修改内容：
 * </pre>
 */

public class ResultException extends Exception {

    private int code;

    public ResultException(String message) {
        super(message);
    }

    public ResultException(int code, String message){
        this(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
