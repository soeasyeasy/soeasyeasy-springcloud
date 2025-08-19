package com.soeasyeasy.system.entity.param;

import lombok.Data;

/**
 * @author hc
 */
@Data
public class LoginReq {

    /**
     * 登录类型
     */
    private String loginType;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String pwd;
}
