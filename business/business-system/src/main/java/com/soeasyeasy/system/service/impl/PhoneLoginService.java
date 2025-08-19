package com.soeasyeasy.system.service.impl;

import com.soeasyeasy.common.entity.LoginDTO;
import com.soeasyeasy.security.config.CustomAuthenticationToken;
import com.soeasyeasy.security.config.LoginType;
import com.soeasyeasy.system.convertor.UserConverter;
import com.soeasyeasy.system.entity.UserEntity;
import com.soeasyeasy.system.entity.param.LoginReq;
import com.soeasyeasy.system.service.LoginService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 电话登录服务
 *
 * @author hc
 * @date 2025/04/17
 */
@Service
@RequiredArgsConstructor
public class PhoneLoginService implements LoginService {
    private final AuthenticationManager authenticationManager;

    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public LoginDTO login(LoginReq loginReq) {
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                loginReq.getPhone(),
                loginReq.getCode(),
                LoginType.mobile
        );
        Authentication authenticated = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        UserEntity userByUsername = (UserEntity) authenticated.getPrincipal();
        return UserConverter.INSTANCE.doToLoginDTO(userByUsername);
    }

    /**
     * init 登录类型
     */
    @Override
    @PostConstruct
    public void initLoginType() {
        LoginContext.registerLoginStrategy(LoginType.mobile.name(), this);
    }
}
