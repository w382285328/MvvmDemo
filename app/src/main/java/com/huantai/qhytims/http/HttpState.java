package com.huantai.qhytims.http;

import java.io.Serializable;

/**
 * 请求状态
 */
public
class HttpState implements Serializable {
    public static final int HTTP_BEFORE = 1;
    public static final int HTTP_ERROR = -1;
    public static final int HTTP_AFTER = 2;

    private int errCode;
    private String errMsg;
    private int state;
    private ReturnValue returnValue;
    public HttpState() {
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }
}
