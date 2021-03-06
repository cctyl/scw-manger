package com.atguigu.scw.manger.bean;

import javax.validation.constraints.Pattern;

public class TRole {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "TRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}