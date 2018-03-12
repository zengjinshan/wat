package com.fh.controller.app.other;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/14.
 */
public  class ResultResponse implements Serializable{

    private boolean success=true;

    private String msg;

    private Object obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
