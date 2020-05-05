package com.atguigu.scw.manger.bean;

import javax.validation.constraints.Pattern;

public class TRole {
    private Integer id;

    @Pattern(regexp = "(^[a-zA-Z0-9]{2,5}$)|(^[\\u2E80-\\u9FFF]{2,5}$)",message = "角色名格式不正确，必须为2-5的数字和字母组合或者2-5中文")
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