package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Entity
@Table(name = "T_W_ORDER_TYPE")
public class OrderType implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "ORDER_ID",length = 32)
    private String orderId;//订单id

    @Column(name = "SERVICE_ID",length = 32)
    private String serviceId;//生产订单业务来源id

    @Column(name = "TYPE",length = 1)
    private String type;//订单生成业务类型 1：大圈消息

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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
