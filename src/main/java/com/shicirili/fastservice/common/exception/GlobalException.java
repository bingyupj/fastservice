package com.shicirili.fastservice.common.exception;

/**
 * 自定义全局异常类
 */
public class GlobalException extends RuntimeException {

    private int code;

    public GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
