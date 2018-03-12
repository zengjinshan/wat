package com.fh.service.watermeter.liked;

import com.fh.entity.vo.PraiseVo;
import com.fh.entity.watermeter.Information;
import com.fh.entity.watermeter.Praise;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service
public class PraiseService extends BaseService {

    private static final String mapper="PraiseMapper.";

    public List<PraiseVo> findPraiseByUserAndInfo(WatUser user, String infoId) throws Exception {
        Map map=new HashMap();
        map.put("infoId",infoId);
        map.put("userId",user.getId());
        List<PraiseVo> list = this.queryForListBySql(mapper + "findPraiseList", map);
        if(list==null){
            list=new ArrayList<>();
        }
        return list;
    }

    public Integer savePraise(WatUser user, String infoId) throws Exception {
        Praise liked=new Praise();
        liked.setId(UuidUtil.get32UUID());
        liked.setUserImg(user.getImg());
        liked.setCreateDate(new Date());
        liked.setInfoId(infoId);
        liked.setUserId(user.getId());
        this.save(liked);
        Information info = (Information) this.find(Information.class, infoId);
        info.setPraiseNum(info.getPraiseNum()+1);
        this.update(info);
        return info.getPraiseNum();
    }
}
