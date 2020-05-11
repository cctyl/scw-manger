package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TType;
import com.atguigu.scw.manger.dao.TTypeMapper;
import com.atguigu.scw.manger.bean.TTypeExample;
import com.atguigu.scw.manger.service.TTypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TTypeServiceImpl implements TTypeService {

    @Autowired
    TTypeMapper typeMapper;



    /**
     * 查询所有分类
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<TType> findAll(Integer page, Integer size) {

        PageHelper.startPage(page,size);

        return typeMapper.selectByExample(null);
    }


    /**
     * 关键字查询分类
     *
     * @param page
     * @param size
     * @param search
     * @return
     */
    @Override
    public List<TType> findAllByCondition(Integer page, Integer size, String search) {

        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+search+"%");
        PageHelper.startPage(page,size);
        return typeMapper.selectByExample(example);
    }

    /**
     * 删除多个分类
     *
     * @param idList
     * @return
     */
    @Override
    public int deleteBatch(List<Integer> idList) {
        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);

        int i = typeMapper.deleteByExample(example);
        return i;
    }

    /**
     * 根据id删除分类
     *
     * @param parseInt
     * @return
     */
    @Override
    public int deleteById(int parseInt) {

        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(parseInt);

        int i = typeMapper.deleteByExample(example);

        return i;
    }

    /**
     * 添加分类
     *
     * @param type
     * @return
     */
    @Override
    public int addRole(TType type) {

        return typeMapper.insert(type);
    }


    /**
     * 修改分类
     *
     * @param type
     * @return
     */
    @Override
    public int updateById(TType type) {
        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(type.getId());

        return typeMapper.updateByExample(type,example);
    }
}
