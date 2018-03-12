package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/6.
 */
@Entity
@Table(name = "T_W_GROUP")
public class Group implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "USER_ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
