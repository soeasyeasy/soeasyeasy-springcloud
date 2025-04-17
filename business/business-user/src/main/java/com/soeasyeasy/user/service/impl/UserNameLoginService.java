package com.soeasyeasy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soeasyeasy.common.util.JwtUtil;
import com.soeasyeasy.user.convertor.UserConverter;
import com.soeasyeasy.user.entity.UserEntity;
import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.param.LoginReq;
import com.soeasyeasy.user.service.LoginService;
import com.soeasyeasy.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    private final UserService userService;
    private final JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public LoginDTO login(LoginReq loginReq) {
        String userName = loginReq.getUserName();
        String pwd = loginReq.getPwd();
        if (StringUtils.isAnyBlank(userName, pwd)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }
        //根据username查询
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserEntity::getUserName, userName);
        UserEntity entity = userService.getBaseMapper().selectOne(lambdaQueryWrapper);
        if (LoginContext.checkPwd(loginReq, entity)) {
            LoginDTO loginDTO = UserConverter.INSTANCE.doToLoginDTO(entity);
            String token = jwtUtil.generateToken(userName);
            loginDTO.setToken(token);
            return loginDTO;
        }
        throw new IllegalArgumentException("用户名或密码错误");
    }

    /**
     * init 登录类型
     */
    @Override
    @PostConstruct
    public void initLoginType() {
        LoginContext.registerLoginStrategy(LoginContext.USER_NAME_LOGIN, this);
    }
}
