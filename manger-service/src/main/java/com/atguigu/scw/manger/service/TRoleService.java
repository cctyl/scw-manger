package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TRole;

import java.util.List;

public interface TRoleService {
    /**
     * 查询所有角色
     * @return
     */
    public List<TRole> findAll();

    /**
     * 根据用户id查询用户拥有的角色
     * @param id
     * @return
     */
    public List<TRole> findRoleByUid(Integer id);


    /**
     * 查询用户没有的角色
     * @param id
     * @return
     */
    public List<TRole> findOthersRole(Integer id);



}
