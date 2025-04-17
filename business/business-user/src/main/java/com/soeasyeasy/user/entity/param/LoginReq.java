package com.soeasyeasy.user.entity.param;

import lombok.Data;

@Data
public class LoginReq {

    /**
     * 登录类型
     */
    private String loginType;
    /**
     * 手机号
     */
    private String phone;
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
