package com.fh.entity.wx;

import java.io.Serializable;

/**
 * Created by admin on 2017/11/1.
 */
public class ErrorResp implements Serializable {

    private Integer errcode;

    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}

