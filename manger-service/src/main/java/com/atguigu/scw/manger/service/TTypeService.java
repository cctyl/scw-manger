package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TType;

import java.util.List;

public interface TTypeService {
    /**
     * 查询所有分类
     * @param page
     * @param size
     * @return
     */
    List<TType> findAll(Integer page, Integer size);

    /**
     * 关键字查询分类
     * @param page
     * @param size
     * @param search
     * @return
     */
    List<TType> findAllByCondition(Integer page, Integer size, String search);


    /**
     * 删除多个分类
     * @param idList
     * @return
     */
    int deleteBatch(List<Integer> idList);

    /**
     * 根据id删除分类
     * @param parseInt
     * @return
     */
    int deleteById(int parseInt);

    /**
     * 添加分类
     * @param type
     * @return
     */
    int addRole(TType type);

    /**
     * 修改分类
     * @param type
     * @return
     */
    int updateById(TType type);
}
