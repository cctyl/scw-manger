package com.atguigu.scw.manger.dao;

import com.atguigu.scw.manger.bean.TMessage;
import com.atguigu.scw.manger.example.TMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMessageMapper {
    long countByExample(TMessageExample example);

    int deleteByExample(TMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByExample(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);
}