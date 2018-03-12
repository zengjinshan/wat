package com.fh.entity.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/21.
 */
@Entity
@Table(name = "demo")
public class Demo implements Serializable{

    @Id
    @Column(name = "ID",length = 32,nullable = false)
    private String id;

    @Column(name = "name",length = 255)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
