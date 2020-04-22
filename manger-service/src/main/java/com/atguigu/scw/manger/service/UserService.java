package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TUser;

public interface UserService {

    /**
     * 根据id查询用户
     *
     * @return
     */
    public TUser getUserById(Integer id);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean register(TUser user);

    /**
     * 用户登录
     * @param user
     */
    public TUser login(TUser user);
}
