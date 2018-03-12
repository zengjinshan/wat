package com.fh.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by admin on 2017/11/10.
 */
@Entity
@Table(name = "T_SYS_PARAM")
public class SystemParam implements Serializable {

    @Id
    @Column(name = "ID",length = 32,nullable = false)
    private String id;

    @Column(name = "code",length = 20)
    private String code;

    @Column(name = "type",length = 10)
    private String type;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "value",length = 300)
    private String value;

    @Column(name = "remark",length = 100)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
