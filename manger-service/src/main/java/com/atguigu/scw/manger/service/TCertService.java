package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TCert;

import java.util.List;

public interface TCertService {
    /**
     * 条件查询 + 分页查询 所有资质
     * @param page
     * @param size
     * @param search
     * @return
     */
    List<TCert> findAllByCondition(Integer page, Integer size, String search);
}
