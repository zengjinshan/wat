package com.fh.entity.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/2.
 */
public class CommentVo implements Serializable {

    private String id;

    private String infoId;

    private String createDate;

    private String commentContent;

    private String observer;

    private String observerId;

    private String userImg;

    private String commentInfoType;

    public String getCommentInfoType() {
        return commentInfoType;
    }

    public void setCommentInfoType(String commentInfoType) {
        this.commentInfoType = commentInfoType;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
