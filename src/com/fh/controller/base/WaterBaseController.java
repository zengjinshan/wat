package com.fh.controller.base;

import com.fh.framewrok.util.Logger;
import com.fh.framewrok.util.UuidUtil;

/**
 * Created by admin on 2018/2/9.
 */
public class WaterBaseController {

    protected String msg;

    protected Object obj;

    protected boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
