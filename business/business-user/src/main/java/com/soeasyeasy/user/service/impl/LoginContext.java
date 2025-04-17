package com.soeasyeasy.user.service.impl;

import com.soeasyeasy.common.util.PasswordHashWithSalt;
import com.soeasyeasy.user.entity.UserEntity;
import com.soeasyeasy.user.entity.dto.LoginDTO;
import com.soeasyeasy.user.entity.param.LoginReq;
import com.soeasyeasy.user.service.LoginService;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录上下文
 *
 * @author hc
 * @date 2025/04/17
 */
public class LoginContext {
    /**
     * 登录策略
     */
    private static final Map<String, LoginService> LOGIN_STRATEGIES = new ConcurrentHashMap<>();

    /**
     * 策略类型常量
     */
    public static final String PHONE_LOGIN = "phone";
    public static final String EMAIL_LOGIN = "email";
    public static final String USER_NAME_LOGIN = "userName";

    /**
     * 注册登录策略
     *
     * @param strategyName  策略名称
     * @param loginStrategy 登录策略
     */
    public static void registerLoginStrategy(String strategyName, LoginService loginStrategy) {
        LOGIN_STRATEGIES.put(strategyName, loginStrategy);
    }

    /**
     * 执行登录
     *
     * @param strategyName 策略名称
     * @param loginReq     登录 req
     * @return {@link LoginDTO }
     */
    public static LoginDTO executeLogin(String strategyName, LoginReq loginReq) {
        LoginService strategy = LOGIN_STRATEGIES.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("No login strategy found for: " + strategyName);
        }
        return strategy.login(loginReq);
    }

    /**
     * 密码加密方式
     * 1、SHA-256+salt
     * 2、明文
     */
    public static boolean checkPwd(LoginReq loginReq, UserEntity userEntity) {
        if (Objects.isNull(userEntity)) {
            return false;
        }
        String pwd = loginReq.getPwd();
        Integer pwdType = userEntity.getPwdType();
        String dbPwd = userEntity.getPwd();
        return switch (pwdType) {
            case 1 -> {
                String salt = userEntity.getSalt();
                String frontPwd = PasswordHashWithSalt.hashPasswordWithSalt(pwd, salt.getBytes(StandardCharsets.UTF_8));
                yield frontPwd.equals(dbPwd);
            }
            case 2 -> pwd.equals(dbPwd);
            default -> pwd.equals(dbPwd);
        };
    }


}