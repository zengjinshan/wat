package com.fh.framewrok.response;

import com.fh.entity.watermeter.WatUser;

import java.util.Date;

/**
 * Created by admin on 2018/2/10.
 */
public class Token {

    private Date time;

    private WatUser user;

    private String kId;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public WatUser getUser() {
        return user;
    }

    public void setUser(WatUser user) {
        this.user = user;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }
}
