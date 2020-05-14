package com.atguigu.scw.manger.service.imp;


import com.atguigu.project.MD5Util;
import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.bean.TUserToken;
import com.atguigu.scw.manger.bean.TUserTokenExample;
import com.atguigu.scw.manger.dao.TUserMapper;
import com.atguigu.scw.manger.dao.TUserRoleMapper;
import com.atguigu.scw.manger.bean.TUserExample;
import com.atguigu.scw.manger.dao.TUserTokenMapper;
import com.atguigu.scw.manger.service.UserService;
import com.atguigu.scw.manger.utils.JedisUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.scw.manger.utils.MyStringUtils;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TUserMapper userMapper;

    @Autowired
    TUserRoleMapper userRoleMapper;

    @Autowired
    TUserTokenMapper tokenMapper;


    /**
     * 根据token查询用户
     *
     * @param logintoken
     * @return
     */
    @Override
    public TUser getUserByToken(String logintoken) {
        //1.首先去redis中拿userid
        //如果拿到了，就往下，拿不到就用token查询数据库拿到userid
        //1.1获取jedis客户端，使用jedisutils
        Jedis jedis = JedisUtil.getJedis();

        //1.2从jedis中用token查询userid
        String userid = jedis.get("logintoken");
        if (userid==null){
            //redis中没有userid，要自己查询userid
            TUserTokenExample example = new TUserTokenExample();
            TUserTokenExample.Criteria criteria = example.createCriteria();
            criteria.andAutoLoginEqualTo(logintoken);
            List<TUserToken> tUserTokens = tokenMapper.selectByExample(example);

            if (tUserTokens.size()>0){
                userid = tUserTokens.get(0).getUserid().toString();
            }else {
                //没有和这个token对应的userid，直接返回空
                return null;
            }


            //将数据存入redis
            jedis.set("logintoken",userid);


        }
        //否则就是在jedis中拿到了token，那就直接往下执行

        //通过userid查询出user
        TUserExample userExample = new TUserExample();
        TUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(Integer.parseInt(userid));
        List<TUser> tUsers = userMapper.selectByExample(userExample);


        return tUsers.get(0);
    }

    /**
     * 重置用户密码
     *
     * @param loginacct
     * @param password
     * @return
     */
    @Override
    public int resetUserPassword(String loginacct, String password) {
        //条件
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);

        //数据封装
        TUser user = new TUser();
        String digest = MD5Util.digest(password);
        user.setUserpswd(digest);


        return userMapper.updateByExampleSelective(user, example);
    }

    /**
     * 通过账户名查询用户
     *
     * @return
     */
    @Override
    public TUser findUserByAccount(String loginacct) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        List<TUser> tUsers = userMapper.selectByExample(example);

        return tUsers.get(0);
    }

    /**
     * 用户激活
     *
     * @param token
     * @return
     */
    @Override
    public int activeUser(String token) {


        int i = userMapper.activeUser(token);

        return i;
    }

    /**
     * 修改用户
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
     *
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
     *
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
     *
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

        criteria1.andLoginacctLike("%" + search + "%");
        criteria2.andUsernameLike("%" + search + "%");
        example.or(criteria2);//只需 or criteria2 ，不需要criteria1
        PageHelper.startPage(page, size);
        List<TUser> tUsers = userMapper.selectByExample(example);
        return tUsers;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<TUser> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
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

            e.printStackTrace();
            logger.info("用户名重复");

            return false;

        }
        //返回注册结果
        return true;


    }

    /**
     * 用户登录
     *
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

        return tUsers.size() == 1 ? tUsers.get(0) : null;
    }
}
