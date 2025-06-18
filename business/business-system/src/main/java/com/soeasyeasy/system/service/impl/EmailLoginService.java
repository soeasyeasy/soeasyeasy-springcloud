package com.soeasyeasy.system.service.impl;

import com.soeasyeasy.system.entity.dto.LoginDTO;
import com.soeasyeasy.system.entity.param.LoginReq;
import com.soeasyeasy.system.service.LoginService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 * 电子邮件登录服务
 *
 * @author hc
 * @date 2025/04/17
 */
@Service
public class EmailLoginService implements LoginService {
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
     * 初始化登录方式
     */
    @Override
    @PostConstruct
    public void initLoginType() {
        LoginContext.registerLoginStrategy(LoginContext.EMAIL_LOGIN, this);
    }
}
