package com.atguigu.scw.manger.bean;

import java.io.Serializable;
import java.util.List;

public class TPermission implements Serializable {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;
    private String flag;


    private List<TPermission> childs;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<TPermission> getChilds() {
        return childs;
    }

    public void setChilds(List<TPermission> childs) {
        this.childs = childs;
    }
}