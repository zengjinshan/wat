package com.fh.service.watermeter.comment;

import com.fh.entity.vo.CommentVo;
import com.fh.entity.vo.RelateToMeVo;
import com.fh.entity.watermeter.Attachment;
import com.fh.entity.watermeter.Comment;
import com.fh.entity.watermeter.Information;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.DateUtil;
import com.fh.framewrok.util.UuidUtil;
import com.fh.service.watermeter.relatetome.RelateToMeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/3/1.
 */
@Service
public class CommentService extends BaseService {

    private static final String mapper="CommentMapper.";

    @Autowired
    private RelateToMeService relateToMeService;


    public void comment(WatUser user,String infoId,String content) throws Exception {
        Information info= (Information) this.find(Information.class,infoId);
        WatUser watUser= (WatUser) this.find(WatUser.class,info.getUserId());
        Comment comment=new Comment();
        comment.setId(UuidUtil.get32UUID());
        comment.setInfoId(infoId);
        comment.setObserver(user.getNickName());
        comment.setObserverId(user.getId());
        comment.setCommentContent(content);
        comment.setCreateDate(new Date());
        comment.setByObserver(watUser.getNickName());
        comment.setByObserverId(info.getUserId());
        comment.setCommentInfoType(info.getInfoType());
        comment.setCommentContentType(info.getContentType());
        comment.setUserImg(user.getImg());
        List<Attachment> attas=this.findListByField(Attachment.class,"serviceId",infoId);
        if(attas!=null){
            comment.setFilePath(attas.get(0).getFilePath());
        }else {
            comment.setFilePath("");
        }
        this.save(comment);
        info.setCommentNum(info.getCommentNum()+1);
        this.update(info);
    }

    public List<CommentVo> commentList(String infoId) throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("infoId",infoId);
        List<CommentVo> list = this.queryForListBySql(mapper + "findCommentList", map);
        if(list==null){
            list=new ArrayList<>();
        }
        return list;
    }

    public List<CommentVo> queryCommentListByUserId(String observerId)throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("observerId",observerId);
        List<CommentVo> list = this.queryForListBySql(mapper + "findCommentList", map);
        if(list==null){
            list=new ArrayList<>();
        }
        return list;
    }

    public Integer countRelateToMe(WatUser user) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        String time = relateToMeService.findRelateToMeNow(user.getId());
        if(StringUtils.isNotEmpty(time)){
            Date startTime=DateUtils.parseDate(time,new String[]{"yyyy-MM-dd HH:mm:ss"});
            map.put("startTime",startTime);
        }
        map.put("endTime",new Date());
        Integer count = (Integer) this.queryForObjectBySql(mapper + "countRelateToMe", map);
        return count;
    }

    public List relateToMe(WatUser user) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        String time = relateToMeService.findRelateToMeNow(user.getId());
        if(StringUtils.isNotEmpty(time)){
            Date startTime=DateUtils.parseDate(time,new String[]{"yyyy-MM-dd HH:mm:ss"});
            map.put("startTime",startTime);
        }
        map.put("endTime",new Date());
        List list = this.queryForListBySql(mapper + "relateToMe", map);
        if(list==null){
            list=new ArrayList();
        }
        return list;
    }

}
