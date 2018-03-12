package com.fh.service.watermeter.information;

import com.fh.entity.vo.CommentVo;
import com.fh.entity.vo.InfoDetail;
import com.fh.entity.vo.InformationVo;
import com.fh.entity.vo.MyAlbumVo;
import com.fh.entity.watermeter.Attachment;
import com.fh.entity.watermeter.Information;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.service.watermeter.comment.CommentService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service
public class InformationService extends BaseService{

    public final static String mapper="InformationMapper.";

    @Autowired
    private CommentService commentService;

    public List<InformationVo> findInfo(Map<String,Object> map) throws Exception {
        List<InformationVo> vos=new ArrayList<InformationVo>();
        String shields="";
        String infoIds="";
        if(map.get("shields")!=null){
             shields = map.get("shields").toString();
        }
        if(map.get("infoIds")!=null){
             infoIds = map.get("infoIds").toString();
        }
        if(shields.lastIndexOf(",")==(shields.length()-1)&& StringUtils.isNotEmpty(shields)){
            map.put("shields",shields.substring(0,shields.length()-1));
        }
        if(infoIds.lastIndexOf(",")==(infoIds.length()-1)&& StringUtils.isNotEmpty(infoIds)){
            map.put("infoIds",infoIds.substring(0,infoIds.length()-1));
        }
        List<Information> list = this.queryForListBySql(mapper + "findInfo", map);
        InformationVo vo=null;
        for (Information info:list){
             vo=new InformationVo();
            vo.setId(info.getId());
            vo.setInfoType(info.getInfoType());
            vo.setCommentNum(info.getCommentNum());
            vo.setContent(info.getContent());
            vo.setContentType(info.getContentType());
            Date createDate = info.getCreateDate();
            String format = DateFormatUtils.format(createDate, "yyyy-MM-dd HH:mm:ss SSS");
            vo.setCreateDate(format);
            vo.setForwardInfo(info.getForwardInfo());
            vo.setNickName(info.getNickName());
            vo.setInfoType(info.getInfoType());
            vo.setForwardNum(info.getForwardNum());
            vo.setOfferMoney(info.getOfferMoney()!=null?info.getOfferMoney():"");
            vo.setOfferTime(info.getOfferTime()!=null?info.getOfferTime().toString():"");
            vo.setPraiseNum(info.getPraiseNum());
            vo.setShieldNum(info.getShieldNum());
            vo.setTag(info.getTag()!=null?info.getTag():"");
            vo.setUserId(info.getUserId());
            vo.setUserImg(info.getUserImg());
            vo.setForwardInd(info.getForwardInd());
            vo.setPhoneName(info.getPhoneName());
            vo.setAdoptCommentId(info.getAdoptCommentId()!=null?info.getAdoptCommentId():"");
            List<Attachment> attas = this.findListByField(Attachment.class, "serviceId", info.getId());
            ArrayList<String> files=new ArrayList<String>();
            for(Attachment atta:attas){
                files.add(atta.getFilePath());
            }
            vo.setFiles(files);
            vos.add(vo);
        }
        return vos;
    }

    public InfoDetail infoDetail(String id,WatUser user) throws Exception {
        InfoDetail detail=new InfoDetail();
        Information infomation = (Information) this.find(Information.class, id);
        if(infomation!=null){
            if(!infomation.getUserId().equals(user.getId())){
                infomation.setReadNum(infomation.getReadNum()+1);
            }
            this.update(infomation);
            detail.setId(infomation.getId());
            detail.setInfoType(infomation.getInfoType());
            detail.setCommentNum(infomation.getCommentNum());
            detail.setContent(infomation.getContent());
            detail.setContentType(infomation.getContentType());
            detail.setCreateDate(DateFormatUtils.format(infomation.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
            detail.setForwardNum(infomation.getForwardNum());
            detail.setNickName(infomation.getNickName());
            detail.setOfferMoney(infomation.getOfferMoney()!=null?infomation.getOfferMoney():"");
            detail.setTag(infomation.getTag()!=null?infomation.getTag():"");
            detail.setPraiseNum(infomation.getPraiseNum());
            detail.setShieldNum(infomation.getShieldNum());
            detail.setUserImg(infomation.getUserImg());
            detail.setUserId(infomation.getUserId());
            detail.setPhoneName(infomation.getPhoneName());
            detail.setAdoptCommentId(infomation.getAdoptCommentId());
            List<Attachment> attas = this.findListByField(Attachment.class, "serviceId", id);
            ArrayList<String> files=new ArrayList<String>();
            for(Attachment atta:attas){
                files.add(atta.getFilePath());
            }
            detail.setFiles(files);
            List<CommentVo> commentVos = commentService.commentList(id);
            detail.setDetials(commentVos);
            return detail;
        }else {
            return null;
        }
    }

    public List<MyAlbumVo> myAlbumVoList(WatUser user,Integer pageNum,String userId) throws Exception {
        List<MyAlbumVo> vos=new ArrayList<MyAlbumVo>();
        Map<String,Object> map=new HashMap<String,Object>();
        if(!userId.equals(user.getId())){
          map.put("visible","1");
        }
        map.put("userId",userId);
        map.put("pageNum",(pageNum-1)*10);
        List<Information> list = this.queryForListBySql(mapper+"findInfoByUserId",map);
        MyAlbumVo vo=null;
        for (Information info:list){
            vo=new MyAlbumVo();
            vo.setId(info.getId());
            vo.setInfoType(info.getInfoType());
            vo.setCommentNum(info.getCommentNum());
            vo.setContent(info.getContent());
            vo.setContentType(info.getContentType());
            Date createDate = info.getCreateDate();
            String format = DateFormatUtils.format(createDate, "yyyy-MM-dd HH:mm:ss SSS");
            vo.setCreateDate(format);
            vo.setNickName(info.getNickName());
            vo.setInfoType(info.getInfoType());
            vo.setOfferMoney(info.getOfferMoney()!=null?info.getOfferMoney():"");
            vo.setOfferTime(info.getOfferTime()!=null?info.getOfferTime().toString():"");
            vo.setPraiseNum(info.getPraiseNum());
            vo.setShieldNum(info.getShieldNum());
            vo.setUserId(info.getUserId());
            vo.setReadNum(info.getReadNum());
            vo.setForwardNum(info.getForwardNum());
            List<Attachment> attas = this.findListByField(Attachment.class, "serviceId", info.getId());
            ArrayList<String> files=new ArrayList<String>();
            for(Attachment atta:attas){
                files.add(atta.getFilePath());
            }
            vo.setFiles(files);
            vos.add(vo);
        }
        return vos;
    }

    public void deleteInfo(WatUser user,String infoId)throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        map.put("id",infoId);
        this.deleteBySql(mapper+"deleteInfo",map);
    }

    public void setVisible(WatUser user,String infoId) throws Exception{
        Information info = (Information) this.find(Information.class, infoId);
        if(info!=null&&info.getUserId().equals(user.getId())){
            if(info.getVisible().equals("1")){
                info.setVisible("0");
            }else {
                info.setVisible("1");
            }

            this.update(info);
        }
    }

    public Integer findThisMonthInfoCount(WatUser user) throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        return this.queryForIntBySql(mapper+"findThisMonthInfoCount",map);
    }

    public List<InformationVo> findOfferInformation(WatUser user) throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",user.getId());
        List list = this.queryForListBySql(mapper + "findOfferInformation", map);
        return list;
    }

}
