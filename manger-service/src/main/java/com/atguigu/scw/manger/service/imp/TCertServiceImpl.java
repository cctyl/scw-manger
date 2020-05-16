package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TCert;
import com.atguigu.scw.manger.bean.TCertExample;
import com.atguigu.scw.manger.dao.TCertMapper;
import com.atguigu.scw.manger.service.TCertService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TCertServiceImpl implements TCertService {

    @Autowired
    TCertMapper certMapper;

    /**
     * 条件查询 + 分页查询 所有资质
     *
     * @param page
     * @param size
     * @param search
     * @return
     */
    @Override
    public List<TCert> findAllByCondition(Integer page, Integer size, String search) {

        //封装条件
        TCertExample example = new TCertExample();
        TCertExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+search+"%");

        //开启分页
        PageHelper.startPage(page,size);

        //查询
        List<TCert> certList = certMapper.selectByExample(example);
        return certList;
    }




}
