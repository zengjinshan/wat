package com.fh.entity.watermeter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**大圈消息
 * Created by Administrator on 2018/2/26.
 */
@Entity
@Table(name = "T_W_INFORMATION")
public class Information implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "USER_ID",length = 32)
    private String userId;

    @Column(name = "CONTENT",length = 2000)
    private String content;//文字消息

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "INFO_TYPE",length = 1)
    private String infoType;//大圈消息类型 1：普通消息 2：悬赏 3：求赏

    @Column(name = "PRAISE_NUM")
    private Integer praiseNum=0;//点赞数

    @Column(name = "COMMENT_NUM")
    private Integer commentNum=0;//评论数

    @Column(name = "FORWARD_NUM")
    private Integer forwardNum=0;//转发数

    @Column(name = "SHIELD_NUM")
    private Integer shieldNum=0;//屏蔽数

    @Column(name = "FORWARD_INFO",length = 2000)
    private String forwardInfo;

    @Column(name = "CONTENT_TYPE",length = 1)
    private String contentType;//1:文字 2：图片 3：视频 4：音乐

    @Column(name = "OFFER_MONEY",length = 10)
    private String offerMoney;//悬赏金额

    @Column(name = "OFFER_TIME")
    private Integer offerTime;

    @Column(name = "FORWARD_IND",length = 1)
    private String forwardInd;//0 不是 1：是  是否转发

    @Column(name = "READ_NUM")
    private Integer readNum=0;

    @Column(name = "VISIBLE",length = 1)
    private String visible="1";//1 可见 0 不可见

    @Column(name = "PHONE_NAME",length = 30)
    private String phoneName;

    @Column(name = "ADOPT_COMMENT_ID",length = 32)
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

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getForwardInd() {
        return forwardInd;
    }

    public void setForwardInd(String forwardInd) {
        this.forwardInd = forwardInd;
    }

    public Integer getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(Integer offerTime) {
        this.offerTime = offerTime;
    }

    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}
