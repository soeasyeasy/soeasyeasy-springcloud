package com.soeasyeasy.security.service;

import com.soeasyeasy.common.entity.BaseUser;

/**
 * @author hc
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     */
    BaseUser getUserById(String id);

    /**
     * 根据用户名获取用户信息
     */
    BaseUser getUserByUsername(String username);

    /**
     * 根据手机号获取用户信息
     */
    BaseUser getUserByPhone(String phone);
}
