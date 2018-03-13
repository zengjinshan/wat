package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/12.
 */
@Entity
@Table(name = "T_W_REWARD_RECORD")
public class RewardRecord implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "INFO_ID",length = 32)
    private String infoId;

    @Column(name = "USER_ID",length = 32)
    private String userId;

    @Column(name = "REWARD_MONEY",length = 10)
    private String rewardMoney;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }
}
