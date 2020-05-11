package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.dao.TRoleMapper;
import com.atguigu.scw.manger.bean.TRoleExample;
import com.atguigu.scw.manger.service.TRoleService;
import com.github.pagehelper.PageHelper;
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
     * 查询角色
     *
     * @param rid
     * @return
     */
    @Override
    public TRole findRoleByRoleId(Integer rid) {
        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(rid);
        List<TRole> tRoles = roleMapper.selectByExample(example);
        if (tRoles.size()>1||tRoles.size()<1){
            throw new RuntimeException("角色id重复，检查数据库");
        }
        return tRoles.get(0);
    }

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
     * 条件查询
     * @param page
     * @param size
     * @param search
     * @return
     */
    @Override
    public List<TRole> findAllByCondition(Integer page, Integer size, String search) {

        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+search+"%");
        PageHelper.startPage(page,size);
        List<TRole> roleList = roleMapper.selectByExample(example);

        return roleList;
    }

    /**
     * 查询所有角色
     *
     * @return
     * @param page
     * @param size
     */
    @Override
    public List<TRole> findAll(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        List<TRole> tRoles = roleMapper.selectByExample(null);

        return tRoles;
    }


    /**
     * 修改用户
     *
     * @param role
     */
    @Override
    public void updateRole(TRole role) {

        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(role.getId());
        roleMapper.updateByExample(role,example);
    }


    /**
     * 删除多个角色
     *
     * @param idList
     * @return
     */
    @Override
    public int deleteBatch(List<Integer> idList) {
        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);

        int i = roleMapper.deleteByExample(example);

        return i;
    }

    /**
     * 删除单个角色
     *
     * @param parseInt
     * @return
     */
    @Override
    public int deleteById(int parseInt) {
        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(parseInt);

        int i = roleMapper.deleteByExample(example);

        return i;
    }


    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public int addRole(TRole role) {

        return  roleMapper.insert(role);
    }
}
