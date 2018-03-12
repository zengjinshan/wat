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
@Table(name = "T_W_USER")
public class WatUser implements Serializable{

    @Id
    @Column(name = "ID",length = 32,nullable = false)
    private String id;

    @Column(name = "USERNAME",length = 100)
    private String username;

    @Column(name = "PASSWORD",length = 100)
    private String password;

    @Column(name = "PHONE",length = 11)
    private String phone;

    @Column(name = "SEX",length = 1)
    private String sex;

    @Column(name = "NICK_NAME",length = 60)
    private String nickName;

    @Column(name = "ADDRESS",length = 300)
    private String address;

    @Column(name = "EMAIL",length = 60)
    private String email;

    @Column(name = "AREA",length = 20)
    private String area;

    @Column(name = "IMG",length = 300)
    private String img;

    @Column(name = "VALID_IND",length = 1)
    private String validInd;

    @Column(name = "WAT_NO",length = 11)
    private String watNo;

    @Column(name = "tag",length = 500)
    private String tag;

    @Column(name = "QR_CODE_IMG",length = 200)
    private String qrCodeImg;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getValidInd() {
        return validInd;
    }

    public void setValidInd(String validInd) {
        this.validInd = validInd;
    }

    public String getWatNo() {
        return watNo;
    }

    public void setWatNo(String watNo) {
        this.watNo = watNo;
    }

    public String getQrCodeImg() {
        return qrCodeImg;
    }

    public void setQrCodeImg(String qrCodeImg) {
        this.qrCodeImg = qrCodeImg;
    }
}
