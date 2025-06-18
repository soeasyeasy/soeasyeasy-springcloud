package com.soeasyeasy.system.service;

import com.soeasyeasy.system.entity.dto.LoginDTO;
import com.soeasyeasy.system.entity.param.LoginReq;

public interface LoginService {


    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    LoginDTO login(LoginReq loginReq);

    /**
     * init 登录类型
     */
    void initLoginType();

}
