package com.fh.entity.vo;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/5.
 */
public class RelateToMeVo implements Serializable {


    private String id;

    private String infoId;

    private String commentContent;

    private Date createDate;

    private String commentContentType;//评论内容类型 1：文字 2：图片 3：视频 4：音乐

    private String filePath;

    private String userImg;

    private String commentInfoType;//评论消息类型 1:普通评论 2：悬赏 3：打赏

    private String offerMoney;

    private String observer;//评论者

    private String observerId;//评论者id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCommentContentType() {
        return commentContentType;
    }

    public void setCommentContentType(String commentContentType) {
        this.commentContentType = commentContentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCommentInfoType() {
        return commentInfoType;
    }

    public void setCommentInfoType(String commentInfoType) {
        this.commentInfoType = commentInfoType;
    }

    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getObserverId() {
        return observerId;
    }

    public void setObserverId(String observerId) {
        this.observerId = observerId;
    }
}
