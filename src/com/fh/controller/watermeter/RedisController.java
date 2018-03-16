package com.fh.controller.watermeter;

import com.fh.framewrok.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
@Controller
@RequestMapping(value = "redis")
public class RedisController  {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "setKey")
    @ResponseBody
    public String setKey(@RequestParam("key") String key,String value){
        return null;
    }
}
