package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TRole;

import java.util.List;

public interface TRoleService {
    /**
     * 分页查询所有角色
     * @return
     * @param page
     * @param size
     */
    public List<TRole> findAll(Integer page, Integer size);

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


    /**
     * 条件查询
     * @param page
     * @param size
     * @param search
     * @return
     */
    public List<TRole> findAllByCondition(Integer page, Integer size, String search);

    /**
     * 查询角色
     * @param rid
     * @return
     */
    public TRole findRoleByRoleId(Integer rid);

    /**
     * 修改用户
     * @param role
     */
    public void updateRole(TRole role);
}
