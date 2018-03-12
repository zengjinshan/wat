package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/3/11.
 */
@Entity
@Table(name = "T_W_WALLET")
public class Wallet implements Serializable{

    @Id
    @Column(name = "USER_ID",length = 32)
    private String userId;

    @Column(name = "PAY_PASSWORD",length = 6)
    private String payPassword;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
