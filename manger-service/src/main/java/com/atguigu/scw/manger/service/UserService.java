package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TUser;

import java.util.List;

public interface UserService {

    /**
     * 根据id查询用户
     *
     * @return
     */
    public TUser getUserById(Integer id);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    public int updateUserById(TUser user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean register(TUser user);

    /**
     * 用户登录
     * @param user
     */
    public TUser login(TUser user);

    /**
     * 查询所有用户
     * @return
     */
    public List<TUser> findAll(Integer page,Integer size);

    /**
     * 关键字查询用户
     * @param page
     * @param size
     * @param search
     * @return
     */
    public List<TUser> findAllByCondition(Integer page,Integer size,String search);

    /**
     * 删除多个用户
     * @param idList
     * @return
     */
    int deleteBatch(List<Integer> idList);

    /**
     * 根据id删除用户
     * @param ids
     * @return
     */
    int deleteById(Integer ids);
}
