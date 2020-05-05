package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TRolePermission;
import com.atguigu.scw.manger.service.TRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TRolePermissionServiceImpl  implements TRolePermissionService {

    @Override
    public int delPermission(TRolePermission rolePermission) {
        return 0;
    }

    @Override
    public int addPermission(TRolePermission rolePermission) {
        return 0;
    }
}
