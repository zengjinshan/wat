package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/4.
 */
@Entity
@Table(name = "T_W_RELATETOME")
public class RelateToMe implements Serializable{
    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "USER_ID")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
