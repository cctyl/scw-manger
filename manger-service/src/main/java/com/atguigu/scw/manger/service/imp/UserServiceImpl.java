package com.atguigu.scw.manger.service.imp;


import com.atguigu.project.MD5Util;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.dao.TUserMapper;
import com.atguigu.scw.manger.dao.TUserRoleMapper;
import com.atguigu.scw.manger.example.TUserExample;
import com.atguigu.scw.manger.example.TUserRoleExample;
import com.atguigu.scw.manger.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.scw.manger.utils.MyStringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {



    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TUserMapper userMapper;

    @Autowired
    TUserRoleMapper userRoleMapper;




    /**
     * 修改用户
     *
     *
     * @param user@return
     */
    @Override
    public int updateUserById(TUser user) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(user.getId());
        int i = userMapper.updateByExampleSelective(user, example);

        return i;
    }

    /**
     * 删除多个用户
     * @param idList
     * @return
     */
    @Override
    public int deleteBatch(List<Integer> idList) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = userMapper.deleteByExample(example);

        return i;
    }

    /**
     * 根据id删除用户
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer ids) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(ids);

        int i = userMapper.deleteByExample(example);

        return i;
    }

    /**
     * 根据关键字查询用户
     * @param page
     * @param size
     * @param search
     * @return
     */
    @Override
    public List<TUser> findAllByCondition(Integer page, Integer size, String search) {
        TUserExample example = new TUserExample();
        //创建两个条件，一个是用户名，一个是用户账户
        TUserExample.Criteria criteria1 = example.createCriteria();
        TUserExample.Criteria criteria2 = example.createCriteria();

        criteria1.andLoginacctLike("%"+search+"%");
        criteria2.andUsernameLike("%"+search+"%");
        example.or(criteria2);//只需 or criteria2 ，不需要criteria1
        PageHelper.startPage(page,size);
        List<TUser> tUsers = userMapper.selectByExample(example);
        return tUsers;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<TUser> findAll(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        List<TUser> userList = userMapper.selectByExample(null);
        return userList;
    }

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
        String digest = MD5Util.digest(user.getUserpswd());
        user.setUserpswd(digest);

        //2.设置一些默认数据
        user.setUsername(user.getLoginacct());
        user.setCreatetime(MyStringUtils.getTimeStr());


        try {
          userMapper.insertSelective(user);
        } catch (Exception e) {


            logger.info("用户名重复");

            return false;

        }
        //返回注册结果
        return true;


    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public TUser login(TUser user) {
        //1.拿到用户名和密码

        //2.去数据库查询
        //创建一个查询条件，用example来查询
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();

        //设置查询参数 账户名，密码，相当于 where loginacct=? ,userpswd =?
        criteria.andLoginacctEqualTo(user.getLoginacct());
        criteria.andUserpswdEqualTo(MD5Util.digest(user.getUserpswd()));
        //正常情况下只会查到一个用户，如果查到多个，那就当登录失败
        List<TUser> tUsers = null;
        try {
            tUsers = userMapper.selectByExample(example);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return tUsers.size()==1?tUsers.get(0):null;
    }
}
