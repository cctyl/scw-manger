package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TTag;

import java.util.List;

public interface TTagService {

    /**
     * 查询所有的tag
     * @return
     */
    List<TTag> getTagList();

    /**
     * 修改标签名称
     * @param tag
     * @return
     */
    int updateTagById(TTag tag);
}
