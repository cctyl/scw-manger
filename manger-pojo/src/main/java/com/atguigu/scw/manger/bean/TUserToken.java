package com.atguigu.scw.manger.bean;

public class TUserToken {
    private Integer id;

    private Integer userid;

    private String autoLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin == null ? null : autoLogin.trim();
    }
}