package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TUserToken;
import com.atguigu.scw.manger.bean.TUserTokenExample;
import com.atguigu.scw.manger.dao.TUserTokenMapper;
import com.atguigu.scw.manger.service.TUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TUserTokenServiceImpl implements TUserTokenService {
    @Autowired
    TUserTokenMapper tokenMapper;

    /**
     * 保存token
     *
     * @param token
     * @return
     */
    @Override
    public int save(TUserToken token) {
        //判断是不是已经有了token
        TUserTokenExample example = new TUserTokenExample();
        TUserTokenExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(token.getUserid());
        List<TUserToken> tUserTokens = tokenMapper.selectByExample(example);

        if (tUserTokens.size()>0){
            //已经有了，那直接修改
            TUserTokenExample updateExample = new TUserTokenExample();
            TUserTokenExample.Criteria updateCriteria = updateExample.createCriteria();
            updateCriteria.andUseridEqualTo(token.getUserid());
           return tokenMapper.updateByExampleSelective(token,updateExample);

        }
        //否则就新增


        return  tokenMapper.insertSelective(token);
    }
}
