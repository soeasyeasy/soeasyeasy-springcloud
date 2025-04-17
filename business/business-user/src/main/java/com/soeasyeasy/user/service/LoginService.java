package com.soeasyeasy.user.service;

import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.param.LoginReq;

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
