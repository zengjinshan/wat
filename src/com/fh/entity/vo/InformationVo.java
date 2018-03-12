package com.fh.entity.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public class InformationVo implements Serializable {

    private String id;

    private String userId;

    private String content;//文字消息

    private String createDate;

    private String nickName;

    private String tag;

    private String infoType;//大圈消息类型 1：普通消息 2：悬赏 3：求赏

    private Integer praiseNum;//点赞数

    private Integer commentNum;//评论数

    private Integer forwardNum;//转发数

    private Integer shieldNum;//屏蔽数

    private String userImg;

    private String forwardInfo;

    private String contentType;//1:文字 2：图片 3：视频 4：音乐

    private String offerMoney;//悬赏金额

    private String offerTime;

    private String forwardInd;

    private String phoneName;

    private String adoptCommentId;

    public String getAdoptCommentId() {
        return adoptCommentId;
    }

    public void setAdoptCommentId(String adoptCommentId) {
        this.adoptCommentId = adoptCommentId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getForwardInd() {
        return forwardInd;
    }

    public void setForwardInd(String forwardInd) {
        this.forwardInd = forwardInd;
    }

    private ArrayList<String> files=new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public Integer getShieldNum() {
        return shieldNum;
    }

    public void setShieldNum(Integer shieldNum) {
        this.shieldNum = shieldNum;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getForwardInfo() {
        return forwardInfo;
    }

    public void setForwardInfo(String forwardInfo) {
        this.forwardInfo = forwardInfo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
