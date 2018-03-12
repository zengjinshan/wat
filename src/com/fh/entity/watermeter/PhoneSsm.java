package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/2/9.
 */
@Entity
@Table(name = "T_W_PHONE_SSM")
public class PhoneSsm implements Serializable{

    @Id
    @Column(name = "ID",length = 32,nullable = false)
    private String id;

    @Column(name = "PHONE",length = 11)
    private String phone;

    @Column(name = "VALID_MSG",length = 10)
    private String validMsg;

    @Column(name = "VALID_IND",length = 1)
    private String validInd;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(String validMsg) {
        this.validMsg = validMsg;
    }

    public String getValidInd() {
        return validInd;
    }

    public void setValidInd(String validInd) {
        this.validInd = validInd;
    }

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
}
