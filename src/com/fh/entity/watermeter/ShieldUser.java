package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/27.
 */
@Entity
@Table(name = "T_W_SHIELD_USER")
public class ShieldUser implements Serializable{

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "USER_ID",length = 32)
    private String userId;

    @Column(name = "SHIELDER_ID",length = 32)
    private String shielderId;

    @Column(name = "TYPE",length = 1)
    private String type;//屏蔽类型 1：用户 2：消息

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getShielderId() {
        return shielderId;
    }

    public void setShielderId(String shielderId) {
        this.shielderId = shielderId;
    }
}
