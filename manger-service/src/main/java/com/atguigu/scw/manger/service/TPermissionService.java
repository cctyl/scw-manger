package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TPermission;

import java.util.List;

public interface TPermissionService {

    /**
     * 获取组装好的菜单
     * @return
     */
    public List<TPermission> getAllMenus();

    public List<TPermission> getAllPermission();

    /**
     * 根据角色id查询角色拥有的权限
     * @param rid
     * @return
     */
    public List<Integer> getRolePermissionIdByRoleId(Integer rid);

}
