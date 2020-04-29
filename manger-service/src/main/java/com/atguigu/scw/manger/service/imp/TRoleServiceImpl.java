package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.dao.TRoleMapper;
import com.atguigu.scw.manger.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    TRoleMapper roleMapper;

    /**
     * 根据用户id查询用户拥有的角色
     * 需要从中间表查询
     * @param id
     * @return
     */
    @Override
    public List<TRole> findRoleByUid(Integer id) {

        List<TRole> rolesById = roleMapper.findRoleByUid(id);
        return rolesById;
    }


    /**
     * 查询用户没有的角色
     * @param id
     * @return
     */
    public List<TRole> findOthersRole(Integer id){

        return roleMapper.findOthersRole(id);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<TRole> findAll() {

        List<TRole> tRoles = roleMapper.selectByExample(null);

        return tRoles;
    }
}
