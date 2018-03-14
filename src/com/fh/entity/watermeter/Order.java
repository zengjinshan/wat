package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Entity
@Table(name = "T_W_ORDER")
public class Order implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "PAY_MONEY",length = 20)
    private String payMoney;

    @Column(name = "PAY_USER_ID",length = 32)
    private String payUserId;

    @Column(name = "PAY_TYPE",length = 1)
    private String payType;//1:充值  2:悬赏 3：打赏

    @Column(name = "CHANNEL",length = 1)
    private String channel;//支付渠道 1：支付宝 2：微信

    @Column(name = "CREATE_DATE")
    private Date createDate;//订单生产时间

    @Column(name = "STATUS",length = 1)
    private String status;//订单状态 -1 ：失败 0：支付中 1：支付成功

    @Column(name = "DESCRIB",length = 300)
    private String describ;//订单描述


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }
}
