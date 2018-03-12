package com.fh.service.watermeter.relatetome;

import com.fh.framewrok.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/5.
 */
@Service
public class RelateToMeService extends BaseService {

    private static final String mapper="RelateToMeMapper.";

    public String findRelateToMeNow(String userId) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",userId);
        Object obj = this.queryForObjectBySql(mapper + "findRelateToMe", map);
        if(obj==null){
            return null;
        }else {
            return obj.toString();
        }
    }
}
