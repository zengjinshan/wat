package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/26.
 */
@Entity
@Table(name = "T_W_ATTACHMENT")
public class Attachment implements Serializable {

    @Id
    @Column(name = "ID" ,length = 32)
    private String id;

    @Column(name = "TYPE",length = 10)
    private String type;//文件类型

    @Column(name = "FILE_PATH",length = 300)
    private String filePath;//文件地址

    @Column(name = "SERVICE_ID",length = 32)
    private String serviceId;//业务id

    @Column(name = "SERVICE_TYPE",length = 1)
    private String serviceType;//业务类型  1：消息  2:投诉 9：其他 （待补充）

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
