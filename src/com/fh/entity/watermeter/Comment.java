package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/26.
 */
@Entity
@Table(name = "T_W_COMMENT")
public class Comment implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "INFO_ID",length = 32)
    private String infoId;

    @Column(name = "COMMENT_CONTENT",length = 2000)
    private String commentContent;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "COMMENT_CONTENT_TYPE",length = 1)
    private String commentContentType;//评论内容类型 1：文字 2：图片 3：视频 4：音乐

    @Column(name = "FILE_PATH",length = 300)
    private String filePath;

    @Column(name = "USER_IMG",length = 300)
    private String userImg;

    @Column(name = "COMMENT_INFO_TYPE",length = 1)
    private String commentInfoType;//评论消息类型 1:普通评论 2：悬赏 3：打赏

    @Column(name = "OFFER_MONEY",length = 20)
    private String offerMoney="";

    @Column(name = "OBSERVER",length = 200)
    private String observer;//评论者

    @Column(name = "OBSERVER_ID",length = 32)
    private String observerId;//评论者id

    @Column(name = "BY_OBSERVER",length = 200)
    private String byObserver;//被评论者

    @Column(name = "BY_OBSERVER_ID",length = 32)
    private String byObserverId;

    @Column(name = "PRAISE_NUM")
    private Integer praiseNum=0;

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

    public String getByObserverId() {
        return byObserverId;
    }

    public void setByObserverId(String byObserverId) {
        this.byObserverId = byObserverId;
    }

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

    public String getCommentInfoType() {
        return commentInfoType;
    }

    public void setCommentInfoType(String commentInfoType) {
        this.commentInfoType = commentInfoType;
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

    public String getByObserver() {
        return byObserver;
    }

    public void setByObserver(String byObserver) {
        this.byObserver = byObserver;
    }
}
