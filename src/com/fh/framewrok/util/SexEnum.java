package com.fh.framewrok.util;

/**
 * Created by admin on 2018/2/12.
 */
public enum SexEnum {
    MAN("男","1"),WOMEN("女","0");
    private String name;

    private String value;

    private SexEnum(String name,String value){
        this.name=name;
        this.value=value;
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
}
