package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TPermission;

import java.util.List;

public interface TPermissionService {

    /**
     * 获取组装好的权限
     * @return
     */
    public List<TPermission> getAllMenus();

    /**
     * 获取所有的权限
     * @return
     */
    public List<TPermission> getAllPermission();

    /**
     * 根据角色id查询角色拥有的权限
     * @param rid
     * @return
     */
    public List<Integer> getRolePermissionIdByRoleId(Integer rid);

    /**
     * 删除权限以及它的子权限
     * @param id
     * @return
     */
    public int delPermissionById(Integer id);
}
