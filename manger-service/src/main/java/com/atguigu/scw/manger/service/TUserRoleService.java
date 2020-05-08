package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TRole;

public interface TUserRoleService {


    /**
     * 给用户添加权限
     * @param rid
     * @param uid
     * @return
     */
    int addRole(int rid, Integer uid);

    /**
     * 给用户删除权限
     * @param rid
     * @param uid
     * @return
     */
    int delRole(int rid, Integer uid);
}
