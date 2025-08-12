package com.soeasyeasy.system.service.impl;

import com.soeasyeasy.security.config.CustomAuthenticationToken;
import com.soeasyeasy.security.config.LoginType;
import com.soeasyeasy.system.convertor.UserConverter;
import com.soeasyeasy.system.entity.UserEntity;
import com.soeasyeasy.system.entity.dto.LoginDTO;
import com.soeasyeasy.system.entity.param.LoginReq;
import com.soeasyeasy.system.service.LoginService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 用户名登录服务
 *
 * @author hc
 * @date 2025/04/17
 */
@Service
@RequiredArgsConstructor
public class UserNameLoginService implements LoginService {
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
                loginReq.getUserName(),
                loginReq.getPwd(),
                LoginType.password
        );
        Authentication authenticated = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        UserEntity userByUsername = (UserEntity) authenticated.getPrincipal();
        return UserConverter.INSTANCE.doToLoginDTO(userByUsername);
    }

    //String userName = loginReq.getUserName();
    //String pwd = loginReq.getPwd();
    //if (StringUtils.isAnyBlank(userName, pwd)) {
    //    throw new IllegalArgumentException("用户名或密码不能为空");
    //}
    ////根据username查询
    //LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //lambdaQueryWrapper.eq(UserEntity::getUserName, userName);
    //UserEntity entity = userService.getBaseMapper().selectOne(lambdaQueryWrapper);
    //if (LoginContext.checkPwd(loginReq, entity)) {
    //    LoginDTO loginDTO = UserConverter.INSTANCE.doToLoginDTO(entity);
    //    String token = jwtUtil.generateToken(userName);
    //    loginDTO.setToken(token);
    //    return loginDTO;
    //}
    //throw new IllegalArgumentException("用户名或密码错误");
    //}

    /**
     * init 登录类型
     */
    @Override
    @PostConstruct
    public void initLoginType() {
        LoginContext.registerLoginStrategy(LoginType.password.name(), this);
    }
}
