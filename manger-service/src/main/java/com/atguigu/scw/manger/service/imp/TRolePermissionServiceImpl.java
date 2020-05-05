package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TRolePermission;
import com.atguigu.scw.manger.dao.TRolePermissionMapper;
import com.atguigu.scw.manger.example.TRolePermissionExample;
import com.atguigu.scw.manger.service.TRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TRolePermissionServiceImpl  implements TRolePermissionService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TRolePermissionMapper rolePermissionMapper;

    /**
     * 删除角色的某个权限
     * @param rolePermission
     * @return
     */
    @Override
    public int delPermission(TRolePermission rolePermission) {
        logger.debug("给角色删除权限--"+rolePermission.getRoleid());

        TRolePermissionExample example = new TRolePermissionExample();
        TRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleidEqualTo(rolePermission.getRoleid());
        criteria.andPermissionidEqualTo(rolePermission.getPermissionid());

        return rolePermissionMapper.deleteByExample(example);
    }

    /**
     * 给角色添加权限
     * @param rolePermission
     * @return
     */
    @Override
    public int addPermission(TRolePermission rolePermission) {

        logger.debug("给角色添加权限--"+rolePermission.getRoleid());
        return rolePermissionMapper.insert(rolePermission);
    }
}
