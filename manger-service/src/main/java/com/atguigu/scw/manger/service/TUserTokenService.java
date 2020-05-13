package com.atguigu.scw.manger.service;

import com.atguigu.scw.manger.bean.TUserToken;

public interface TUserTokenService {
    /**
     * 保存token
     * @param token
     * @return
     */
    int save(TUserToken token);
}
