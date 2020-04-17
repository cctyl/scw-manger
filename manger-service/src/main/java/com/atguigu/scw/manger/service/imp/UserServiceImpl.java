package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.dao.TUserMapper;
import com.atguigu.scw.manger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TUserMapper userMapper;

    /**
     * 根据id查询用户
     * @return
     */
    @Override
    public TUser getUserById(Integer id) {
        TUser tUser = userMapper.selectByPrimaryKey(id);
        return tUser;
    }
}
