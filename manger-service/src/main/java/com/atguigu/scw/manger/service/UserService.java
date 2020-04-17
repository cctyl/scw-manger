package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TUser;

public interface UserService {

    /**
     * 根据id查询用户
     * @return
     */
    public TUser getUserById(Integer id);
}
