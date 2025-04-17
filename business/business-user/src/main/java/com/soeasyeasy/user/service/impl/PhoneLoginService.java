package com.soeasyeasy.user.service.impl;

import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.param.LoginReq;
import com.soeasyeasy.user.service.LoginService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 * 电话登录服务
 *
 * @author hc
 * @date 2025/04/17
 */
@Service
public class PhoneLoginService implements LoginService {
    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public LoginDTO login(LoginReq loginReq) {
        return null;
    }

    /**
     * init 登录类型
     */
    @Override
    @PostConstruct
    public void initLoginType() {
        LoginContext.registerLoginStrategy(LoginContext.PHONE_LOGIN, this);
    }
}
