package com.atguigu.scw.manger.bean;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class TUser implements Serializable {
    private Integer id;

    @Pattern(regexp = "(^[a-zA-Z0-9]{6,10}$)",message = "用户名格式不正确，必须为6-10的数字或字母组合")
    private String loginacct;

    @Pattern(regexp = "(^[a-zA-Z0-9]{6,10}$)",message = "密码格式不正确，必须为6-10的数字或字母组合")
    private String userpswd;

    @Pattern(regexp = "(^[a-zA-Z0-9]{6,10}$)|(^[\\u2E80-\\u9FFF]{2,5}$)",message = "用户名格式不正确，必须为2-5的数字和字母组合或者2-5中文")
    private String username;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}