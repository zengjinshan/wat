package com.fh.entity.wx;

import java.io.Serializable;

/**
 * Created by admin on 2017/11/1.
 */
public class AccessToken implements Serializable {
    private String accessToken;

    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
