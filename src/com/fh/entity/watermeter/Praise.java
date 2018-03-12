package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/28.
 */
@Entity
@Table(name = "T_W_PRAISE")
public class Praise implements Serializable{

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "INFO_ID",length = 32)
    private String infoId;//被点赞消息id

    @Column(name = "USER_ID",length = 32)
    private String userId;

    @Column(name = "USER_IMG")
    private String userImg;//用户头像地址

    @Column(name = "CREATE_DATE")
    private Date createDate;//点赞时间

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
