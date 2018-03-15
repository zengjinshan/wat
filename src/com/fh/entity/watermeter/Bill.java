package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/15 0015.
 */
@Entity
@Table(name = "T_W_BILL")
public class Bill implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "ORDER_ID",length = 32)
    private String orderId;

    @Column(name = "GENERATE_DATE")
    private Date generateDate;//对应订单日期

    @Column(name = "TYPE",length = 1)
    private String type;//1:支出 2：收入

    @Column(name = "USER_ID",length = 32)
    private String userId;//账单对应用户

    @Column(name = "CHANNEL")
    private String channel;//支付方式  1支付宝 2：微信 3：钱包

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
