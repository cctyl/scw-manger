package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TRolePermission;

public interface TRolePermissionService {
    /**
     * 给角色添加权限
     *
     * @param rolePermission
     * @return
     */
    public int addPermission(TRolePermission rolePermission);

    /**
     * 删除角色的某个权限
     * @param rolePermission
     * @return
     */
    public int delPermission(TRolePermission rolePermission);
}
