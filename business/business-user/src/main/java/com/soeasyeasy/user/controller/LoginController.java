package com.soeasyeasy.user.controller;

import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.param.LoginReq;
import com.soeasyeasy.user.service.impl.LoginContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hc
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    /**
     * 登录接口
     */
    @PostMapping
    public LoginDTO login(@RequestBody LoginReq loginReq) {
        String loginType = loginReq.getLoginType();
        if (StringUtils.isBlank(loginType)) {
            throw new IllegalArgumentException("登录类型不能为空");
        }
        return LoginContext.executeLogin(loginType, loginReq);
    }

}
