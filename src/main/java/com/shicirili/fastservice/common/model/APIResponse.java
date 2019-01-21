package com.shicirili.fastservice.common.model;

import com.shicirili.fastservice.common.JsonUtil;

public class APIResponse<T> {

    public static final int FAIL = 500;
    private int code;
    private String msg;
    private T data;

    public APIResponse(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public APIResponse() {

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
