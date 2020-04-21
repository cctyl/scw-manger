package com.atguigu.scw.manger.service.imp;


import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.dao.TUserMapper;
import com.atguigu.scw.manger.service.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.scw.manger.utils.MyStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TUserMapper userMapper;

    /**
     * 根据id查询用户
     *
     * @return
     */
    @Override
    public TUser getUserById(Integer id) {
        TUser tUser = userMapper.selectByPrimaryKey(id);
        return tUser;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */

    @Override
    public boolean register(TUser user) {
        //1.md5加密,这个方法是commons包下的. 第一个参数是加密的原文，第二个是 盐值 。
        // 盐值可以用uuid，但是需要把uuid一起保存到数据库，否则以后不知道怎么解密
        // 这里用用户账户来做盐值
        String digest = Md5Crypt.md5Crypt(user.getUserpswd().getBytes(), user.getLoginacct());
        user.setUserpswd(digest);

        //2.设置一些默认数据
        user.setUsername(user.getLoginacct());
        user.setCreatetime(MyStringUtils.getTimeStr());

        //影响的行数
        int i = 0;
        try {
            i = userMapper.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户名重复");

        }
        //返回注册结果
        return i == 1 ? true : false;

    }
}
