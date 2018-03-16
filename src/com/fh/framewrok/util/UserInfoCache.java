package com.fh.framewrok.util;

import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.response.Token;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/2/10.
 */
public class UserInfoCache {

    private final static Map<String,Token> userMap=new HashMap<String,Token>();



    public static Token getToken(String key){
        Token token =  userMap.get(key);
        return token;
    }

    public static Token putToken(WatUser user){
        Token token=new Token();
        token.setUser(user);
        token.setTime(new Date());
        String uuid = UuidUtil.get32UUID();
        token.setkId(uuid);
        userMap.put(uuid,token);
        return token;
    }

    public static Token updateToken(Token token){
        userMap.put(token.getkId(),token);
        return token;
    }
}
