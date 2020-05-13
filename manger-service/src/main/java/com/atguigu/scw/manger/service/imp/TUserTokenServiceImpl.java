package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TUserToken;
import com.atguigu.scw.manger.dao.TUserTokenMapper;
import com.atguigu.scw.manger.service.TUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return  tokenMapper.insertSelective(token);
    }
}
