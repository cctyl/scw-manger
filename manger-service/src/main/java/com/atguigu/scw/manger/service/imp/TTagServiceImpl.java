package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TTag;
import com.atguigu.scw.manger.dao.TTagMapper;
import com.atguigu.scw.manger.bean.TTagExample;
import com.atguigu.scw.manger.service.TTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TTagServiceImpl implements TTagService {

    @Autowired
    TTagMapper tagMapper;

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @Override
    public int delTagById(Integer id) {
        TTagExample example = new TTagExample();
        TTagExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);


        return tagMapper.deleteByExample(example);
    }

    /**
     * 修改标签名称
     *
     * @param tag
     * @return
     */
    @Override
    public int updateTagById(TTag tag) {
        TTagExample example = new TTagExample();
        TTagExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(tag.getId());

        return tagMapper.updateByExample(tag,example);
    }

    /**
     * 查询所有的tag
     *
     * @return
     */
    @Override
    public List<TTag> getTagList() {

        List<TTag> tagList = tagMapper.selectByExample(null);
        return tagList;
    }


    /**
     * 添加子节点
     *
     * @param tag
     * @return
     */
    @Override
    public int addTag(TTag tag) {


        return   tagMapper.insert(tag);
    }
}
