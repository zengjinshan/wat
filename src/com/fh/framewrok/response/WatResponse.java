package com.fh.framewrok.response;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/9.
 */
public class WatResponse implements Serializable{


    private String msg="";

    private Object obj="";

    private String status="";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
