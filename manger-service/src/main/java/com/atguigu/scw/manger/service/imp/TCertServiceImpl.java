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

    /**
     * 删除多个条目
     * @param idList
     * @return
     */
    @Override
    public int deleteBatch(List<Integer> idList) {
        TCertExample example = new TCertExample();
        TCertExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);

        return certMapper.deleteByExample(example);
    }

    /**
     * 删除单个条目
     * @param parseInt
     * @return
     */
    @Override
    public int deleteById(int parseInt) {

        TCertExample example = new TCertExample();
        TCertExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(parseInt);

        return certMapper.deleteByExample(example);
    }

    /**
     * 添加条目
     * @param cert
     * @return
     */
    @Override
    public int addRole(TCert cert) {

        return certMapper.insert(cert);
    }

    /**
     * 修改条目
     * @param cert
     * @return
     */
    @Override
    public int updateById(TCert cert) {


        TCertExample example = new TCertExample();
        TCertExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(cert.getId());
        return certMapper.updateByExample(cert,example);
    }


}
