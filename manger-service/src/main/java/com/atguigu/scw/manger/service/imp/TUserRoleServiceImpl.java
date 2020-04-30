package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TUserRole;
import com.atguigu.scw.manger.dao.TUserRoleMapper;
import com.atguigu.scw.manger.example.TUserRoleExample;
import com.atguigu.scw.manger.service.TUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TUserRoleServiceImpl  implements TUserRoleService {

    @Autowired
    TUserRoleMapper userRoleMapper;


    /**
     * 给用户删除权限
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public int delRole(int rid, Integer uid) {
        TUserRoleExample example = new TUserRoleExample();
        TUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleidEqualTo(rid);
        criteria.andUseridEqualTo(uid);


        return userRoleMapper.deleteByExample(example);

    }

    /**
     * 给用户添加权限
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public int addRole(int rid, Integer uid) {
        TUserRole tUserRole = new TUserRole();
        tUserRole.setRoleid(rid);
        tUserRole.setUserid(uid);

        return userRoleMapper.insert(tUserRole);
    }
}
