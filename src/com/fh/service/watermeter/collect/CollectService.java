package com.fh.service.watermeter.collect;

import com.fh.entity.vo.CollectVo;
import com.fh.entity.watermeter.Attachment;
import com.fh.entity.watermeter.Collect;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.UuidUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/3/13.
 */
@Service
public class CollectService extends BaseService {

    private final static String mapper="CollectMapper";

    public void saveCollect(WatUser user,String infoId) throws Exception {
        Collect collect=new Collect();
        collect.setUserId(user.getId());
        collect.setCreateDate(new Date());
        collect.setInfoId(infoId);
        collect.setId(UuidUtil.get32UUID());
        this.save(collect);
    }

    public List<CollectVo> findCollectList(WatUser user)throws Exception{
        List<CollectVo> collectVos=new ArrayList<>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        List<Map<String,Object>> collectList=this.queryForListBySql(mapper+"findCollectList",map);
        if(collectList!=null){
            CollectVo vo;
            for (Map collectMap:collectList){
                 vo=new CollectVo();
                vo.setId(collectMap.get("id").toString());
                vo.setInfoId(collectMap.get("infoId").toString());
                vo.setNickName(collectMap.get("nickName").toString());
                vo.setUserImg(collectMap.get("img").toString());
                vo.setTime(collectMap.get("date").toString());
                vo.setContent(collectMap.get("content")!=null?collectMap.get("content").toString():"");
                List<Attachment> attachmentList = this.findListByField(Attachment.class, "serviceId", collectMap.get("infoId").toString());
                ArrayList<String> files=new ArrayList<String>();
                if(attachmentList!=null){
                    for(Attachment attachment:attachmentList){
                        files.add(attachment.getFilePath());
                    }
                }
                vo.setFiles(files);
                collectVos.add(vo);
            }
        }
        return collectVos;
    }
}
